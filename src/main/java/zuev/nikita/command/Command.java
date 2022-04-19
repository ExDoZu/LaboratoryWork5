package zuev.nikita.command;

import zuev.nikita.structure.Organization;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Super class for commands
 */
public abstract class Command {
    /**
     * Collection processed by a command
     */
    protected final Hashtable<String, Organization> collection;

    public Command() {
        collection = new Hashtable<>();
    }

    public Command(Hashtable<String, Organization> collection) {
        this.collection = collection;
    }

    /**
     * @param arg         Command argument
     * @param history     Commands history
     * @param commandList Commands list of registered commands
     * @param scripts     Executing scripts
     * @return result/report of command execution
     * @throws IOException Throws when there is problem with file reading or writing.
     */
    public abstract String execute(String arg, String savePath, List<String> history, HashMap<String, Command> commandList, Set<File> scripts) throws IOException;

    /**
     * @return Information about the command.
     */
    public abstract String getHelp();
}
