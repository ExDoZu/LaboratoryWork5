package zuev.nikita.command;

import zuev.nikita.structure.Organization;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
/**
 * Return help for available commands.
 */
public class Help implements Command {
    @Override
    public String execute(String arg, Hashtable<String, Organization> hashtable, String savePath, List<String> history, HashMap<String, Command> commandList) {
        StringBuilder response = new StringBuilder();
        response.append("Все команды должны вводиться в нижнем регистре по одной команде на строке!\n");
        for (String command : commandList.keySet())
            response.append(commandList.get(command).getHelp()).append('\n');
        return response.toString();
    }

    @Override
    public String getHelp() {
        return "help : вывести справку по доступным командам";
    }
}