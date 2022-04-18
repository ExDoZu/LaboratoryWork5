package zuev.nikita.command;

import zuev.nikita.structure.Organization;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Removes all elements from the collection that are less than the specified value.
 */
public class RemoveLower extends Command {
    public RemoveLower(Hashtable<String, Organization> collection) {
        super(collection);
    }

    @Override
    public String execute(String arg,String savePath,  List<String> history, HashMap<String, Command> commandList, Set<File> scripts) throws IOException {
        if(arg!=null)return "Команда не нуждается в аргументе.";
        Organization organization = Organization.organizationInput(collection);
        List<String> keys = new ArrayList<>(collection.keySet());
        boolean lowerFound= false;
        for (String key : keys){
            if (collection.get(key).compareTo(organization) < 0) collection.remove(key);
            lowerFound=true;
        }
        if(lowerFound)
        return "Команда выполнена.";
        else{
            return "Нет элементов меньше введенного.";
        }
    }

    @Override
    public String getHelp() {
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }
}
