package zuev.nikita.command;

import zuev.nikita.structure.Organization;

import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 * Displays all elements of the collection in string representation.
 */
public class Show extends Command {
    public Show(Hashtable<String, Organization> collection) {
        super(collection);
    }

    @Override
    public String execute(String arg, String savePath, List<String> history, HashMap<String, Command> commandList, Set<File> scripts) {
        if (arg != null) return "Команда не нуждается в аргументе.";
        StringBuilder response = new StringBuilder();
        if (collection.isEmpty()) {
            return "Коллекция пуста.";
        } else {
            response.append("Элементы, хранящиеся в коллекции:\n");
            for (String key : collection.keySet()) {
                response.append("Ключ ").append(key).append(".\n");
                response.append(collection.get(key)).append("\n");
            }
            return response.toString();
        }
    }

    @Override
    public String getHelp() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
