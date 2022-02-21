package zuev.nikita;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Hashtable;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Date;

/**
 * Занимается обработкой команд от пользователя.
 *
 * @author Никита Зуев
 */
public class CommandHandler {
    /**
     * Запуск обработчика команд.
     *
     * @param path путь к файлу для взятия и сохранения данных.
     */
    public void launch(final String path) {
        Hashtable<Integer, Organization> hashtable = parseFile(path);
        if (hashtable == null) return;
        commandExecutor(hashtable);
    }

    /**
     * Исполнитель команд.
     *
     * @param hashtable Hashtable, с которым будет работать пользователь.
     */
    private void commandExecutor(Hashtable<Integer, Organization> hashtable) {
        Scanner input = new Scanner(System.in);
        Scanner commandParser;
        String fullCommand;
        String command;
        String[] commandsHistory = new String[10];
        boolean gotCommandExit = false;
        while (!gotCommandExit) {
            fullCommand = input.nextLine().trim();
            commandParser = new Scanner(fullCommand);
            command = commandParser.next();
            switch (command) {
                case "exit":
                    gotCommandExit = true;
                    break;
                case "help":
                    help();
                    break;
                case "info":
                    info(hashtable);
                    break;
                case "save":
                    save(hashtable);
                    break;
                case "clear":
                    clear(hashtable);
                    break;
                case "history":
                    history(commandsHistory);
                    break;
                //todo сделать остальные команды.
                default:
                    changeHistory(commandsHistory, fullCommand);
            }
        }
    }

    private void help() {
        System.out.println("Все команды должны вводиться в нижнем регистре по одной команде на строке!" +
                "help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "insert null {element} : добавить новый элемент с заданным ключом\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_key null : удалить элемент из коллекции по его ключу\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "history : вывести последние 10 команд (без их аргументов)\n" +
                "remove_greater_key null : удалить из коллекции все элементы, ключ которых превышает заданный\n" +
                "filter_greater_than_postal_address postalAddress : вывести элементы, значение поля postalAddress которых больше заданного\n" +
                "print_field_ascending_postal_address : вывести значения поля postalAddress всех элементов в порядке возрастания\n" +
                "print_field_descending_postal_address : вывести значения поля postalAddress всех элементов в порядке убывания");
    }

    private void info(Hashtable<Integer, Organization> hashtable) {
        System.out.println("Коллекция представляет из себя Hashtable, хранящий объекты типа Organization. В данный момент в коллекции " + hashtable.size() + " элементов.");
    }

    private void save(Hashtable<Integer, Organization> hashtable) {
        //todo сделать сохранение!!!
    }

    private void clear(Hashtable<Integer, Organization> hashtable) {
        hashtable.clear();
    }

    private void changeHistory(String[] commandHistory, String newCommand) {
        for (int i = commandHistory.length - 1; i > 0; i--)
            commandHistory[i] = commandHistory[i - 1];
        commandHistory[0] = newCommand;
    }

    private void history(String[] commandHistory) {
        System.out.println("История команд:");
        for (String command : commandHistory)
            if (command != null) System.out.println("-- " + command);
    }

    private Hashtable<Integer, Organization> parseFile(String path) {
        File file;
        String inputString = "";
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        Hashtable<Integer, Organization> hashtable = null;

        while (flag) {
            file = new File(path);
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
                System.out.println("В файле содержатся некорректные данные. Введите путь к другому файлу. Для закрытия программы введите команду exit.");
            }
            if (flag) {
                path = input.nextLine();
                if (inputString.equals("exit")) break;
            }
        }
        return hashtable;
    }

    private Hashtable<Integer, Organization> parseText(String text) throws ParseException, WrongDataException {
        Hashtable<Integer, Organization> hashtable = new Hashtable<>();
        Organization organization;
        OrganizationType type;
        JSONParser parser = new JSONParser();
        JSONObject root = (JSONObject) parser.parse(text);
        JSONArray organizations = (JSONArray) root.get("Organizations");
        JSONObject org, coord;
        for (int i = 0; i < organizations.size(); i++) {
            org = (JSONObject) organizations.get(i);
            int id = (int) org.get("id");
            String name = (String) org.get("name");
            Date creationDate = new Date((long) org.get("creationDate"));
            Double annualTurnover = (double) org.get("annualTurnover");
            Address postalAddress = new Address((String) org.get("postalAddress"));
            coord = (JSONObject) org.get("coordinates");
            Coordinates coordinates = new Coordinates((long) coord.get("x"), (Double) coord.get("y"));
            String typeInString = (String) org.get("type");
            switch (typeInString) {
                case "COMMERCIAL":
                    type = OrganizationType.COMMERCIAL;
                    break;
                case "PUBLIC":
                    type = OrganizationType.PUBLIC;
                    break;
                case "TRUST":
                    type = OrganizationType.TRUST;
                    break;
                default:
                    throw new WrongDataException("Нет такого типа организаций \"" + typeInString + "\"");
            }
            organization = new Organization(id, name, coordinates, creationDate, annualTurnover, type, postalAddress);
            hashtable.put(i, organization);
        }
        return hashtable;
    }


}
