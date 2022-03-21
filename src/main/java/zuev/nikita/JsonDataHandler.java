package zuev.nikita;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Занимается обработкой Json файла: его чтение, парсинг. Также преобразование структуры Organization в строку в формате
 * Json
 */
public class JsonDataHandler {
    /**
     * Достает данные из строки в формате Json
     * @param text Строка в формате Json
     * @return Коллекция, с которой будет работать пользователь.
     * @throws ParseException Ошибка парсинга. Например, некорректный Json файл.
     * @throws WrongDataException Данные в неверном формате.
     */
    private static Hashtable<String, Organization> parseText(String text) throws ParseException, WrongDataException {
        Hashtable<String, Organization> hashtable = new Hashtable<>();
        Organization organization;
        OrganizationType type;
        Set<Integer> ids = new HashSet<>();
        JSONParser parser = new JSONParser();
        JSONObject root = (JSONObject) parser.parse(text);
        JSONArray organizations = (JSONArray) root.get("organizations");
        JSONObject org, coord;

        for (int i = 0; i < organizations.size(); i++) {
            org = (JSONObject) organizations.get(i);

            if (org.get("id") == null) throw new WrongDataException("id не может быть null.");
            int id = Integer.parseInt(org.get("id").toString());
            if (id <= 0) throw new WrongDataException("id не может быть отрицательно или равно нулю.");
            if (ids.contains(id)) throw new WrongDataException("id должен быть уникальным.");
            ids.add(id);
            String name = (String) org.get("name");
            if (name == null) throw new WrongDataException("name не может быть null.");
            if (name.equals("")) throw new WrongDataException("name не может быть пустым.");

            if (org.get("creationDate") == null) throw new WrongDataException("creationDate не может быть null.");
            Date creationDate = new Date((long) org.get("creationDate"));

            Double annualTurnover = (Double) org.get("annualTurnover");
            if (annualTurnover != null)
                if (annualTurnover <= 0)
                    throw new WrongDataException("annualTurnover не может быть отрицательно или равно нулю.");

            Address postalAddress = new Address((String) org.get("postalAddress"));

            coord = (JSONObject) org.get("coordinates");
            if (coord == null) throw new WrongDataException("coordinates не может быть null.");
            if (coord.get("x") == null) throw new WrongDataException("coordinates:x не может быть null.");
            if (coord.get("y") == null) throw new WrongDataException("coordinates:y не может быть null.");
            if ((long) coord.get("x") > 923)
                if (coord.get("x") == null) throw new WrongDataException("coordinates:x не может быть больше 923.");
            Coordinates coordinates = new Coordinates((long) coord.get("x"), (Double) coord.get("y"));

            if (org.get("type") == null)
                if (coord.get("x") == null) throw new WrongDataException("type не может быть null.");
            String typeInString = (String) org.get("type");
            if (typeInString.equals("COMMERCIAL") || typeInString.equals("PUBLIC") || typeInString.equals("TRUST")) {
                type = OrganizationType.valueOf(typeInString);
            } else throw new WrongDataException("Нет такого типа организации \"" + typeInString + "\".");
            organization = new Organization(id, name, coordinates, creationDate, annualTurnover, type, postalAddress);
            hashtable.put(Integer.toString(i), organization);
        }
        return hashtable;
    }

    /**
     * Чтение файла и получение данных из него.
     * @param path Путь к файлу.
     * @return Коллекция, с которой будет работать пользователь.
     */
    public static Hashtable<String, Organization> parseFile(String path) {
        File file;
        String inputString = "";
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        Hashtable<String, Organization> hashtable = null;

        while (flag) {
            file = new File(path);
            //todo обработать случаи, когда проблемы с правами доступа.
            try (Scanner fileInput = new Scanner(file)) {
                while (fileInput.hasNextLine())
                    inputString += fileInput.nextLine().trim();
                hashtable = parseText(inputString);
                flag = false;
            } catch (FileNotFoundException e) {
                System.out.println("Указанный вами файл не обнаружен. Введите новый путь к файлу. Для закрытия программы введите команду exit.");
            } catch (ParseException e) {
                System.out.println("Структура файла некорректна или файл поврежден. Введите путь к другому файлу. Для закрытия программы введите команду exit.");
            } catch (WrongDataException e) {
                System.out.println("В файле содержатся некорректные данные. " + e.getMessage() + " Введите путь к другому файлу. Для закрытия программы введите команду exit.");
            }
            if (flag) {
                path = input.nextLine();
                if (path.equals("exit")) break;
            }
        }
        return hashtable;
    }
    /**
     * Преобразовывает структуру Hashtable в строку в формате Json.
     * @param hashtable Сама коллекция, собственно.
     * @return String
     */
    public static String hashtableToString(Hashtable<String, Organization> hashtable) {
        JSONObject root = new JSONObject();
        JSONObject org, coord;
        JSONArray list = new JSONArray();

        for (String key : hashtable.keySet()) {
            org = new JSONObject();
            coord = new JSONObject();
            Organization organization = hashtable.get(key);
            coord.put("x", organization.getCoordinates().getX());
            coord.put("y", organization.getCoordinates().getY());
            org.put("coordinates", coord);
            org.put("id", organization.getId());
            org.put("name", organization.getName());
            org.put("creationDate", organization.getCreationDate().getTime());
            org.put("annualTurnover", organization.getAnnualTurnover());
            org.put("type", organization.getType().name());
            org.put("postalAddress", organization.getPostalAddress().getZipCode());
            list.add(org);
        }
        root.put("organizations", list);
        return root.toJSONString();
    }
}
