package zuev.nikita.command;

import zuev.nikita.structure.Address;
import zuev.nikita.structure.Coordinates;
import zuev.nikita.structure.Organization;
import zuev.nikita.structure.OrganizationType;

import java.io.IOException;
import java.util.*;

/**
 * Interface for commands (command pattern)
 */
public interface Command {
    /**
     * @param arg         Command argument
     * @param hashtable   Structure
     * @param savePath    File to save the structure
     * @param history     Commands history
     * @param commandList Commands list
     * @throws IOException Throws when there is problem with file reading or writing.
     * @return result/report of command execution
     */
    String execute(String arg, Hashtable<String, Organization> hashtable, String savePath, List<String> history, HashMap<String, Command> commandList) throws IOException;

    /**
     * @return Information about the command.
     */
    String getHelp();

    /**
     * Method for a user to enter a structure through the input stream.
     *
     * @param hashtable Collection
     * @return Organization
     */
    default Organization organizationInput(Hashtable<String, Organization> hashtable) {
        Scanner scanner = new Scanner(System.in);
        Set<Integer> ids = new HashSet<>();
        for (String key : hashtable.keySet())
            ids.add(hashtable.get(key).getId());
        int id = 0;
        for (int i = 1; i <= ids.size() + 1; i++)
            if (!ids.contains(i)) id = i;
        String name = null, zipCode, orgTypeString;
        Date creationDate = new Date();
        double annualTurnover = 0, y = 0;
        OrganizationType type = null;
        long x = 0;
        boolean flag = true;
        while (flag) {
            System.out.print("name: ");
            name = scanner.nextLine().trim();
            if (!name.equals("")) flag = false;
            else System.out.println("name не может быть null и не может быть пустым.");
        }
        System.out.println("Coordinates:");
        flag = true;
        while (flag) {
            System.out.print("X: ");
            try {
                x = Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("X дожен быть целым числом.");
                continue;
            }
            if (x <= 923) flag = false;
            else System.out.println("X дожен быть не больше 923.");
        }
        flag = true;
        while (flag) {
            System.out.print("Y: ");
            try {
                y = Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Y дожен быть дробным числом. Дробное число пишется через точку.");
                continue;
            }
            flag = false;
        }
        flag = true;
        while (flag) {
            System.out.print("annualTurnover: ");
            try {
                annualTurnover = Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("annualTurnover дожен быть дробным числом. Дробное число пишется через точку.");
                continue;
            }
            if (annualTurnover > 0) flag = false;
            else System.out.println("annualTurnover должен быть больше 0.");
        }
        flag = true;
        while (flag) {
            flag = false;
            System.out.print("OrganizationType (COMMERCIAL - 1, PUBLIC - 2, TRUST - 3): ");
            orgTypeString = scanner.nextLine().trim();
            switch (orgTypeString) {
                case "COMMERCIAL":
                case "1":
                    type = OrganizationType.COMMERCIAL;
                    break;
                case "PUBLIC":
                case "2":
                    type = OrganizationType.PUBLIC;
                    break;
                case "TRUST":
                case "3":
                    type = OrganizationType.TRUST;
                    break;
                default:
                    System.out.println("Нет такого типа Организации " + orgTypeString);
                    flag = true;
                    break;
            }
        }
        System.out.print("Address: ");
        zipCode = scanner.nextLine().trim();
        if (zipCode.equals("")) zipCode = null;
        return new Organization(id, name, new Coordinates(x, y), creationDate, annualTurnover, type, new Address(zipCode));
    }
}
