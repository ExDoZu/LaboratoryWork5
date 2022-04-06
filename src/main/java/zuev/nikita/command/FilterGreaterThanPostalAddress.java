package zuev.nikita.command;

import zuev.nikita.structure.Address;
import zuev.nikita.structure.Organization;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Returns all elements as a string whose postalAddress field value is greater than the given value.
 */
public class FilterGreaterThanPostalAddress implements Command {
    /**
     * @param arg Given address.
     */
    @Override
    public String execute(String arg, Hashtable<String, Organization> hashtable, String savePath, List<String> history, HashMap<String, Command> commandList) throws IOException {
        Address userInputPostalAddress = new Address(arg);
        for (String keyFromHashtable : hashtable.keySet()) {
            if (hashtable.get(keyFromHashtable).getPostalAddress().compareTo(userInputPostalAddress) > 0)
                System.out.println(hashtable.get(keyFromHashtable));

        }
        return "Команда выполнена.";
    }

    @Override
    public String getHelp() {
        return "filter_greater_than_postal_address postalAddress : вывести элементы, значение поля postalAddress которых больше заданного";
    }
}
