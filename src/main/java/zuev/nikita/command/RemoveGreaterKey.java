package zuev.nikita.command;

import zuev.nikita.structure.Organization;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Removes from the collection all elements whose key is greater than the specified value.
 */
public class RemoveGreaterKey extends Command {
    public RemoveGreaterKey(Hashtable<String, Organization> collection) {
        super(collection);
    }

    /**
     * @param arg Given key
     */
    @Override
    public String execute(String arg,String savePath,  List<String> history, HashMap<String, Command> commandList, Set<File> scripts) throws IOException {
        if(arg==null)return "Не был указан Ключ.";
        List<String> keys = new ArrayList<>(collection.keySet());
        boolean greaterFound =false;
        for (String key2 : keys){
            if (key2.compareTo(arg) > 0) collection.remove(key2);
            greaterFound = true;
        }
        if (greaterFound)
        return "Команда выполнена.";
        else{
            return "Нет элементов с ключом больше заданного.";
        }
    }

    @Override
    public String getHelp() {
        return "remove_greater_key null : удалить из коллекции все элементы, ключ которых превышает заданный";
    }
}
