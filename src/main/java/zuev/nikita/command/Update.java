package zuev.nikita.command;

import zuev.nikita.structure.Organization;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Updates the collection element by its ID.
 */
public class Update implements Command {
    @Override
    public String execute(String arg, Hashtable<String, Organization> hashtable, String savePath, List<String> history, HashMap<String, Command> commandList) throws IOException {
        if (arg == null) return "Не был указан id.";
        int id = Integer.parseInt(arg);
        boolean foundID = false;
        for (String key : hashtable.keySet())
            if (hashtable.get(key).getId() == id) {
                foundID = true;
                Organization organization = organizationInput(hashtable);
                organization.setId(id);
                hashtable.replace(key, organization);
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
