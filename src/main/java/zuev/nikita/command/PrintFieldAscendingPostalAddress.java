package zuev.nikita.command;

import zuev.nikita.structure.Address;
import zuev.nikita.structure.Organization;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Returns the values of the postalAddreess field of all elements in ascending order as a string.
 */
public class PrintFieldAscendingPostalAddress extends Command {
    public PrintFieldAscendingPostalAddress(Hashtable<String, Organization> collection) {
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
        for (Address address : addresses)
            response.append(address).append("\n");
        return response.toString();
    }

    @Override
    public String getHelp() {
        return "print_field_ascending_postal_address : вывести значения поля postalAddress всех элементов в порядке возрастания.";
    }
}
