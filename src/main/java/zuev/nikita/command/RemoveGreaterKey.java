package zuev.nikita.command;

import zuev.nikita.structure.Organization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Removes from the collection all elements whose key is greater than the specified value.
 */
public class RemoveGreaterKey implements Command {
    /**
     * @param arg Given key
     */
    @Override
    public String execute(String arg, Hashtable<String, Organization> hashtable, String savePath, List<String> history, HashMap<String, Command> commandList) throws IOException {
        if(arg==null)return "Не был указан Ключ.";
        List<String> keys = new ArrayList<>(hashtable.keySet());
        for (String key2 : keys)
            if (key2.compareTo(arg) > 0) hashtable.remove(key2);
        return "Команда выполнена.";
    }

    @Override
    public String getHelp() {
        return "remove_greater_key null : удалить из коллекции все элементы, ключ которых превышает заданный";
    }
}
