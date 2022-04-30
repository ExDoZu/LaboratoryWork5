package zuev.nikita;

import zuev.nikita.command.Command;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Invokes commands.
 */
public class Invoker {

    /**
     * Contains current executing scripts.
     */
    private final Set<File> scripts;
    private final List<String> commandsHistory = new ArrayList<>();
    private final HashMap<String, Command> registeredCommands;

    public Invoker() {
        registeredCommands = new HashMap<>();
        scripts = new HashSet<>();
        for (int i = 0; i < 10; i++) commandsHistory.add(null);
    }

    public Invoker(HashMap<String, Command> commands, Set<File> scripts) {
        registeredCommands = commands;
        this.scripts = scripts;
        for (int i = 0; i < 10; i++) commandsHistory.add(null);
    }

    /**
     * Registers a new command
     */
    public void register(String commandName, Command command) {
        registeredCommands.put(commandName, command);
    }

    /**
     * @param fullCommand command with/without argument
     */
    public String invoke(String[] fullCommand, String path) throws IOException {
        if (registeredCommands.containsKey(fullCommand[0])) {
            commandsHistory.remove(0);
            commandsHistory.add(fullCommand[0]);
            if (fullCommand.length == 1)
                return registeredCommands.get(fullCommand[0]).execute(null, path, commandsHistory, registeredCommands, scripts);
            else
                return registeredCommands.get(fullCommand[0]).execute(fullCommand[1], path, commandsHistory, registeredCommands, scripts);
        } else {
            return "Команда " + fullCommand[0] + " не существует.";
        }
    }
}

