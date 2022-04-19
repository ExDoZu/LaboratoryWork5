package zuev.nikita.command;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Returns the last 10 commands.
 */
public class History extends Command {

    @Override
    public String execute(String arg, String savePath, List<String> history, HashMap<String, Command> commandList, Set<File> scripts) throws IOException {
        if (arg != null) return "Команда не нуждается в аргументе.";
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
