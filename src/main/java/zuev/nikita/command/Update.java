package zuev.nikita.command;

import zuev.nikita.structure.Organization;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 * Updates the collection element by its ID.
 */
public class Update extends Command {
    public Update(Hashtable<String, Organization> collection) {
        super(collection);
    }

    @Override
    public String execute(String arg, String savePath, List<String> history, HashMap<String, Command> commandList, Set<File> scripts) throws IOException {
        if (arg == null) return "Не был указан ID.";
        int id = Integer.parseInt(arg);
        boolean foundID = false;
        for (String key : collection.keySet())
            if (collection.get(key).getId() == id) {
                foundID = true;
                Organization organization = Organization.organizationInput(collection);
                organization.setId(id);
                collection.replace(key, organization);
                break;
            }
        if (foundID) return "Данные успешно обновлены.";
        else return "Нет элемента коллекции с таким ID.";
    }

    @Override
    public String getHelp() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}
