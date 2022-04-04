package zuev.nikita.Command;

import zuev.nikita.Structure.Organization;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Обновляет элемент коллекции по его ID.
 */
public class Update implements Command {
    @Override
    public String execute(String arg, Hashtable<String, Organization> hashtable, String savePath, List<String> history, HashMap<String, Command> commandList) throws IOException {
        if(arg==null)return "Не был указан id.";
        int id = Integer.parseInt(arg);
        Organization organization = organizationInput(hashtable);
        organization.setId(id);
        for (String key : hashtable.keySet())
            if (hashtable.get(key).getId() == id) {
                hashtable.replace(key, organization);
                break;
            }
        return "Данные успешно обновлены.";
    }

    @Override
    public String getHelp() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}
