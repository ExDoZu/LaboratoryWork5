package zuev.nikita.command;

import zuev.nikita.structure.Organization;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Gives a command to exit the program.
 */
public class Exit implements Command {
    @Override
    public String execute(String arg, Hashtable<String, Organization> hashtable, String savePath, List<String> history, HashMap<String, Command> commandList) throws IOException {
        return "exit";
    }

    @Override
    public String getHelp() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}
