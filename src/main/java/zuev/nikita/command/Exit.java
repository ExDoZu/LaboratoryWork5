package zuev.nikita.command;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Gives a command to exit the program.
 */
public class Exit extends Command {

    @Override
    public String execute(String arg, String savePath, List<String> history, HashMap<String, Command> commandList, Set<File> scripts) throws IOException {
        if (arg != null) return "Команда не нуждается в аргументе.";
        return "exit";
    }

    @Override
    public String getHelp() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}
