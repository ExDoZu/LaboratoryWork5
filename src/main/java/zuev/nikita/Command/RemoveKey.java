package zuev.nikita.Command;

import zuev.nikita.Structure.Organization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
/**
 * Удаляет из коллекции элемент по его ключу.
 *
 */
public class RemoveKey implements Command{
    @Override
    public String execute(String arg, Hashtable<String, Organization> hashtable, String savePath, List<String> history, HashMap<String, Command> commandList) throws IOException {
        if(arg==null)return "Не был указан Ключ.";
        List<String> keys = new ArrayList<>(hashtable.keySet());
        for (String key2 : keys)
            if (key2.compareTo(arg) == 0) hashtable.remove(key2);
        return "Команда выполнена.";
    }

    @Override
    public String getHelp() {
        return "remove_key null : удалить элемент из коллекции по его ключу.";
    }
}
