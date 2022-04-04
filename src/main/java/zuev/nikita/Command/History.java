package zuev.nikita.Command;

import zuev.nikita.Structure.Organization;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Выводит последние 10 команд.
 */
public class History implements Command {
    @Override
    public String execute(String arg, Hashtable<String, Organization> hashtable, String savePath, List<String> history, HashMap<String, Command> commandList) throws IOException {
        StringBuilder response = new StringBuilder();
        response.append("История команд:\n");
        for (String command : history)
            if (command != null) response.append("-- ").append(command).append('\n');
        return response.toString();
    }

    @Override
    public String getHelp() {
        return "history : вывести последние 10 команд (без их аргументов).";
    }
}
