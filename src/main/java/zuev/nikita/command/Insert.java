package zuev.nikita.command;

import zuev.nikita.structure.Organization;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Inserts Organization entered manually by the user by the given key into the collection.
 */
public class Insert implements Command {
    @Override
    public String execute(String arg, Hashtable<String, Organization> hashtable, String savePath, List<String> history, HashMap<String, Command> commandList) throws IOException {
        if(arg==null)return "Не был указан Ключ.";
        hashtable.put(arg, organizationInput(hashtable));
        return "Данные успешно введены.";
    }

    @Override
    public String getHelp() {
        return "insert null {element} : добавить новый элемент с заданным ключом";
    }
}
