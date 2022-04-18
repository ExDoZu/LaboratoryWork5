package zuev.nikita.command;

import zuev.nikita.structure.Organization;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Removes an element from the collection by its key.
 */
public class RemoveKey extends Command {
    public RemoveKey(Hashtable<String, Organization> collection) {
        super(collection);
    }

    @Override
    public String execute(String arg,String savePath,  List<String> history, HashMap<String, Command> commandList, Set<File> scripts) throws IOException {
        if (arg == null) return "Не был указан Ключ.";
        List<String> keys = new ArrayList<>(collection.keySet());
        boolean foundKey = false;
        for (String key2 : keys)
            if (key2.compareTo(arg) == 0) {
                collection.remove(key2);
                foundKey=true;
                break;
            }
        if(foundKey)
            return "Команда выполнена.";
        else
            return "Нет элемента с таким ключом.";
    }

    @Override
    public String getHelp() {
        return "remove_key null : удалить элемент из коллекции по его ключу.";
    }
}
