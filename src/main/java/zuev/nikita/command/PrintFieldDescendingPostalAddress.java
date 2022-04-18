package zuev.nikita.command;

import zuev.nikita.structure.Address;
import zuev.nikita.structure.Organization;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Returns the values of the postalAddress field of all elements in descending order as a string.
 */
public class PrintFieldDescendingPostalAddress extends Command {
    public PrintFieldDescendingPostalAddress(Hashtable<String, Organization> collection) {
        super(collection);
    }

    @Override
    public String execute(String arg,String savePath,  List<String> history, HashMap<String, Command> commandList, Set<File> scripts) throws IOException {
        if(arg!=null)return "Команда не нуждается в аргументе.";
        List<Address> addresses = new ArrayList<>();
        for (String key : collection.keySet())
            addresses.add(collection.get(key).getPostalAddress());
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
