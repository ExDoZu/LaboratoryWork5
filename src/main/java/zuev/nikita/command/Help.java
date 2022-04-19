package zuev.nikita.command;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Return help for available commands.
 */
public class Help extends Command {
    @Override
    public String execute(String arg,String savePath,  List<String> history, HashMap<String, Command> commandList, Set<File> scripts) {
        if (arg != null) return "Команда не нуждается в аргументе.";
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
