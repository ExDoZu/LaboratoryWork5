package zuev.nikita.Command;

import zuev.nikita.Structure.Organization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Удаляет из коллекции все элементы, меньшие, чем заданный.
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
