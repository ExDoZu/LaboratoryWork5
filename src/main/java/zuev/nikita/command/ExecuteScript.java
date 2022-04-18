package zuev.nikita.command;

import zuev.nikita.Invoker;
import zuev.nikita.structure.Organization;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Executes a script written in another file.
 */
public class ExecuteScript extends Command {

    public ExecuteScript(Hashtable<String, Organization> collection) {
        super(collection);
    }

    /**
     * @param arg Path to the script file.
     * @return Script success/failure report.
     */
    @Override
    public String execute(String arg, String savePath, List<String> history, HashMap<String, Command> commandList, Set<File>scripts) throws IOException {
        if (arg == null) return "Не указан путь к файлу.";
        File currentScript = new File(arg);
        if (currentScript.exists()) {
            if (currentScript.isFile()) {
                if (currentScript.canRead()) {
                    try (Scanner input = new Scanner(new File(arg))) {
                        if (!scripts.contains(currentScript.getAbsoluteFile())) {
                            scripts.add(currentScript.getAbsoluteFile());
                            Invoker invoker = new Invoker(commandList, scripts);
                            String[] fullCommand;
                            while (input.hasNextLine()) {
                                fullCommand = input.nextLine().split(" ");
                                try {
                                    System.out.println(invoker.invoke(fullCommand, savePath));
                                } catch (FileNotFoundException e) {
                                    if (e.getMessage().equals("Нет доступа к файлу из-за нехватки прав доступа.")) {
                                        System.out.println("Нехватка прав доступа.");
                                    } else {
                                        System.out.println("Файл не найден.");
                                    }
                                } catch (IOException e) {
                                    System.out.println("Нехватка прав доступа.");
                                } catch (NumberFormatException e) {
                                    System.out.println("ID должен быть целым числом.");
                                }
                            }
                            scripts.remove(currentScript.getAbsoluteFile());
                        } else {
                            return "Невозможно выполнить скрипт, который уже выполняется.";
                        }
                        return "Скрипт выполнен успешно.";
                    } catch (FileNotFoundException e) {
                        return "Файл не найден.";
                    }
                } else {
                    return "Нехватка прав доступа к файлу.";
                }
            } else {
                return "Это должен быть файл, а не директория.";
            }
        } else {
            return "Файл не найден.";
        }
    }

    @Override
    public String getHelp() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
