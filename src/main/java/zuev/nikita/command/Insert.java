package zuev.nikita.command;

import com.sun.org.apache.xpath.internal.operations.Or;
import zuev.nikita.structure.Organization;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 * Inserts Organization entered manually by the user by the given key into the collection.
 */
public class Insert extends Command {
    public Insert(Hashtable<String, Organization> collection) {
        super(collection);
    }

    @Override
    public String execute(String arg,String savePath,  List<String> history, HashMap<String, Command> commandList, Set<File> scripts) throws IOException {
        if (arg == null) return "Не был указан Ключ.";
        collection.put(arg, Organization.organizationInput(collection));
        return "Данные успешно введены.";
    }

    @Override
    public String getHelp() {
        return "insert null {element} : добавить новый элемент с заданным ключом";
    }
}
