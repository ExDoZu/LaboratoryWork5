package zuev.nikita.Command;

import zuev.nikita.Structure.Address;
import zuev.nikita.Structure.Organization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Выводит значения поля postalAddreess всех элементов в порядке убывания.
 */
public class PrintFieldDescendingPostalAddress implements Command {
    @Override
    public String execute(String arg, Hashtable<String, Organization> hashtable, String savePath, List<String> history, HashMap<String, Command> commandList) throws IOException {
        List<Address> addresses = new ArrayList<>();
        for (String key : hashtable.keySet())
            addresses.add(hashtable.get(key).getPostalAddress());
        addresses.sort(Address::compareTo);
        StringBuilder response = new StringBuilder();
        for (int i = addresses.size() - 1; i >= 0; i--)
            response.append(addresses.get(i)).append("\n");
        return response.toString();
    }

    @Override
    public String getHelp() {
        return "print_field_descending_postal_address : вывести значения поля postalAddress всех элементов в порядке убывания.";
    }
}
