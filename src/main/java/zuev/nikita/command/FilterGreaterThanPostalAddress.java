package zuev.nikita.command;

import zuev.nikita.structure.Address;
import zuev.nikita.structure.Organization;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 * Returns all elements as a string whose postalAddress field value is greater than the given value.
 */
public class FilterGreaterThanPostalAddress extends Command {
    public FilterGreaterThanPostalAddress(Hashtable<String, Organization> collection) {
        super(collection);
    }

    /**
     * @param arg Given address.
     */
    @Override
    public String execute(String arg, String savePath, List<String> history, HashMap<String, Command> commandList, Set<File> scripts) throws IOException {
        if (arg == null) return "Команда нуждается в аргументе.";
        Address userInputPostalAddress = new Address(arg);
        boolean greaterFound = false;
        for (String keyFromHashtable : collection.keySet()) {
            if (collection.get(keyFromHashtable).getPostalAddress().compareTo(userInputPostalAddress) > 0) {
                System.out.println(collection.get(keyFromHashtable));
                greaterFound = true;
            }
        }
        if (greaterFound)
            return "Команда выполнена.";
        else return "Нет элементов с адресом больше заданного.";
    }

    @Override
    public String getHelp() {
        return "filter_greater_than_postal_address postalAddress : вывести элементы, значение поля postalAddress которых больше заданного";
    }
}
