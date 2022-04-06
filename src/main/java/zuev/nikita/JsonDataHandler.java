package zuev.nikita;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import zuev.nikita.structure.Address;
import zuev.nikita.structure.Coordinates;
import zuev.nikita.structure.Organization;
import zuev.nikita.structure.OrganizationType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Engaged in Json file processing: reading it, parsing it. Also converting the Organization structure to a string in Json format
 */
public class JsonDataHandler {
    /**
     * Gets data from a string in Json format
     *
     * @param text String in Json format
     * @return The collection that the user will work with.
     * @throws ParseException     Parsing error. For example, incorrect Json file.
     * @throws WrongDataException The data is in the wrong format.
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
     * Reading a file and getting data from it.
     *
     * @param path Path to the file.
     * @return Collection that the user will work with.
     * @throws FileNotFoundException Throws when a file not found
     * @throws WrongDataException    Throws when data is wrong
     * @throws ParseException        Throws when a file structure is incorrect
     */
    public static Hashtable<String, Organization> parseFile(String path) throws FileNotFoundException, WrongDataException, ParseException {
        StringBuilder inputString = new StringBuilder();
        File file = new File(path);
        Scanner fileInput = new Scanner(file);
        while (fileInput.hasNextLine())
            inputString.append(fileInput.nextLine().trim());
        if (inputString.length() == 0) return new Hashtable<>();
        else return parseText(inputString.toString());
    }

    /**
     * Converts a Hashtable structure to a Json string.
     *
     * @param hashtable Collection
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
