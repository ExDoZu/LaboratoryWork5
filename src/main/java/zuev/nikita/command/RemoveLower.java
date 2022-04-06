package zuev.nikita.command;

import zuev.nikita.structure.Organization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Removes all elements from the collection that are less than the specified value.
 */
public class RemoveLower implements Command {
    @Override
    public String execute(String arg, Hashtable<String, Organization> hashtable, String savePath, List<String> history, HashMap<String, Command> commandList) throws IOException {
        Organization organization = organizationInput(hashtable);
        List<String> keys = new ArrayList<>(hashtable.keySet());
        for (String key : keys)
            if (hashtable.get(key).compareTo(organization) < 0) hashtable.remove(key);
        return "Команда выполнена.";
    }

    @Override
    public String getHelp() {
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }
}
