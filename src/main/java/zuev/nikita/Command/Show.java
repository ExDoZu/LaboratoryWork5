package zuev.nikita.Command;

import zuev.nikita.Structure.Organization;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Выводит все элементы коллекции в строковом представлении.
 */
public class Show implements Command {
    @Override
    public String execute(String arg, Hashtable<String, Organization> hashtable, String savePath, List<String> history, HashMap<String, Command> commandList) {
        StringBuilder response = new StringBuilder();
        response.append("Элементы, хранящиеся в коллекции:\n");
        for (String key : hashtable.keySet()) {
            response.append("Ключ ").append(key).append(".\n");
            response.append(hashtable.get(key)).append("\n");
        }
        return response.toString();
    }

    @Override
    public String getHelp() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
