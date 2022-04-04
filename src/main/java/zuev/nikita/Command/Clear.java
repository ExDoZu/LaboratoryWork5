package zuev.nikita.Command;

import zuev.nikita.Structure.Organization;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Очищает коллекцию.
 */
public class Clear implements Command {
    @Override
    public String execute(String arg, Hashtable<String, Organization> hashtable, String savePath, List<String> history, HashMap<String, Command> commandList) throws IOException {
        hashtable.clear();
        return "Коллекция очищена.";
    }

    @Override
    public String getHelp() {
        return "clear : очистить коллекцию";
    }
}
