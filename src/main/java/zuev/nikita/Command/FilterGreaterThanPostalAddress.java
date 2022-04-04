package zuev.nikita.Command;

import zuev.nikita.Structure.Address;
import zuev.nikita.Structure.Organization;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Выводит все элементы, значение поля postalAddress которых превышает заданный.
 */
public class FilterGreaterThanPostalAddress implements Command {
    /**
     * @param arg Заданный адрес.
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
