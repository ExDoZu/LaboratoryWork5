package zuev.nikita.command;

import zuev.nikita.structure.Organization;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Executes a script written in another file.
 */
public class ExecuteScript implements Command {
    /**
     * Contains current executable scripts.
     */
    private final static Set<File> scripts= new HashSet<>();

    /**
     * @param arg Path to the script file.
     * @param savePath  Path to the file to save the collection.
     * @param hashtable Collection
     * @return Script success/failure report.
     */
    @Override
    public String execute(String arg, Hashtable<String, Organization> hashtable, String savePath, List<String> history, HashMap<String, Command> commandList) throws IOException {
        if(arg==null)return "Не указан путь к файлу.";
        File currentScript = new File(arg);
        if (!scripts.contains(currentScript)) {
            scripts.add(currentScript);
            Scanner scanner = new Scanner(new File(arg));
            Invoker.invoke(hashtable, savePath, scanner);
            scanner.close();
            scripts.remove(currentScript);
        } else {
            return "Невозможно выполнить скрипт, который уже выполняется.";
        }
        return "Скрипт выполнен успешно.";
    }

    @Override
    public String getHelp() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
