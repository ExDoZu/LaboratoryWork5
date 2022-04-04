package zuev.nikita.Command;

import zuev.nikita.Structure.Organization;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

/**
 * Выполняет скрипт, написанный в другом файле.
 */
public class ExecuteScript implements Command {
    /**
     * Глубина рекурсии при выполнении команды execute_script. Максимальное значение 10.
     */
    static short recursionDepth = 0;

    /**
     * @param arg       Путь к файлу со скриптом.
     * @param savePath  Файл для сохранения коллекции.
     * @param hashtable Сама коллекция, собственно.
     * @return Отчет об удачном/неудачном выполнении скрипта.
     */
    @Override
    public String execute(String arg, Hashtable<String, Organization> hashtable, String savePath, List<String> history, HashMap<String, Command> commandList) throws IOException {
        if (recursionDepth < 10) {
            recursionDepth++;
            Scanner scanner = new Scanner(new File(arg));
            Invoker.invoke(hashtable, savePath, scanner);
            scanner.close();
            recursionDepth--;
        } else {
            return "Невозможно одновременно выполнять более 10 файлов-скриптов.";
        }
        return "Скрипт выполнен успешно.";
    }

    @Override
    public String getHelp() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
