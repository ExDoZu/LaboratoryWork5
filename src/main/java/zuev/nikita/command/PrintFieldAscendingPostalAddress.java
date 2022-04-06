package zuev.nikita.command;

import zuev.nikita.structure.Address;
import zuev.nikita.structure.Organization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Returns the values of the postalAddreess field of all elements in ascending order as a string.
 */
public class PrintFieldAscendingPostalAddress implements Command {
    @Override
    public String execute(String arg, Hashtable<String, Organization> hashtable, String savePath, List<String> history, HashMap<String, Command> commandList) throws IOException {
        List<Address> addresses = new ArrayList<>();
        for (String key : hashtable.keySet())
            addresses.add(hashtable.get(key).getPostalAddress());
        addresses.sort(Address::compareTo);
        StringBuilder response = new StringBuilder();
        for (Address address : addresses)
            response.append(address).append("\n");
        return response.toString();
    }

    @Override
    public String getHelp() {
        return "print_field_ascending_postal_address : вывести значения поля postalAddress всех элементов в порядке возрастания.";
    }
}
