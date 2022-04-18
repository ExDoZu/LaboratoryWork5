package zuev.nikita.command;

import zuev.nikita.structure.Address;
import zuev.nikita.structure.Coordinates;
import zuev.nikita.structure.Organization;
import zuev.nikita.structure.OrganizationType;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Abstract class for commands (command pattern)
 */
public abstract class Command {
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
     * @param commandList Commands list
     * @return result/report of command execution
     * @throws IOException Throws when there is problem with file reading or writing.
     */
    public abstract String execute(String arg, String savePath, List<String> history, HashMap<String, Command> commandList, Set<File>scripts) throws IOException;

    /**
     * @return Information about the command.
     */
    public abstract String getHelp();
}
