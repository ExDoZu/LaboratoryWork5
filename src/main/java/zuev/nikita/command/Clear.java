package zuev.nikita.command;

import zuev.nikita.structure.Organization;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 * Clears the collection.
 */
public class Clear extends Command {


    public Clear(Hashtable<String, Organization> collection) {
        super(collection);
    }

    @Override
    public String execute(String arg, String savePath, List<String> history, HashMap<String, Command> commandList, Set<File> scripts) throws IOException {
        if (arg != null) return "Команда не нуждается в аргументе.";
        if (!collection.isEmpty()){
            collection.clear();
            return "Коллекция очищена.";
        }else{
            return "Коллекция уже пуста.";
        }

    }

    @Override
    public String getHelp() {
        return "clear : очистить коллекцию";
    }
}
