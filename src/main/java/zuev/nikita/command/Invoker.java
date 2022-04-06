package zuev.nikita.command;

import zuev.nikita.structure.Organization;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Invoke commands.
 */
public class Invoker {
    /**
     * @param hashtable  Hashtable that a user works with.
     * @param pathToSave Path to the file to save the collection.
     * @param input      Scanner with given input stream.
     */
    public static void invoke(Hashtable<String, Organization> hashtable, String pathToSave, Scanner input) {
        String[] fullCommand;
        List<String> commandsHistory = new ArrayList<>();
        for (int i = 0; i < 10; i++) commandsHistory.add(null);
        HashMap<String, Command> registeredCommands = new HashMap<>();
        registeredCommands.put("info", new Info());
        registeredCommands.put("clear", new Clear());
        registeredCommands.put("execute_script", new ExecuteScript());
        registeredCommands.put("exit", new Exit());
        registeredCommands.put("filter_greater_than_postal_address", new FilterGreaterThanPostalAddress());
        registeredCommands.put("help", new Help());
        registeredCommands.put("history", new History());
        registeredCommands.put("insert", new Insert());
        registeredCommands.put("print_field_ascending_postal_address", new PrintFieldAscendingPostalAddress());
        registeredCommands.put("print_field_descending_postal_address", new PrintFieldDescendingPostalAddress());
        registeredCommands.put("remove_greater_key", new RemoveGreaterKey());
        registeredCommands.put("remove_key", new RemoveKey());
        registeredCommands.put("remove_lower", new RemoveLower());
        registeredCommands.put("save", new Save());
        registeredCommands.put("show", new Show());
        registeredCommands.put("update", new Update());
        boolean gotCommandExit = false;
        while (!gotCommandExit) {
            try {
                fullCommand = input.nextLine().split(" ");
            } catch (Exception e) {
                System.out.println("Проблема с чтением команд. Если используется файл-скрипт, проверьте, что в конце" +
                        " написана команда exit.");
                break;
            }
            if (registeredCommands.containsKey(fullCommand[0])) {
                commandsHistory.remove(0);
                commandsHistory.add(fullCommand[0]);
                while (true) {
                    try {
                        if (fullCommand.length > 1)
                            System.out.println(registeredCommands.get(fullCommand[0]).execute(fullCommand[1], hashtable, pathToSave, commandsHistory, registeredCommands));
                        else {
                            String response = registeredCommands.get(fullCommand[0]).execute(null, hashtable, pathToSave, commandsHistory, registeredCommands);
                            if (response.equals("exit")) gotCommandExit = true;
                            else System.out.println(response);
                        }
                        break;
                    } catch (FileNotFoundException e) {
                        if (e.getMessage().equals("Нет доступа к файлу из-за нехватки прав доступа.")) {
                            System.out.println("Файл для сохранения недоступен из-за нехватки прав доступа. Укажите путь к новому файлу.\n" +
                                    "cancel - отменить команду сохранения.");
                            pathToSave = input.nextLine().trim();
                        } else {
                            System.out.println("Файл не найден.");
                            break;
                        }
                    } catch (IOException e) {
                        System.out.println("Файл для сохранения недоступен из-за нехватки прав доступа. Укажите путь к новому файлу.\n" +
                                "cancel - отменить команду сохранения.");
                        pathToSave = input.nextLine().trim();
                    } catch (NumberFormatException e) {
                        System.out.println("ID должен быть целым числом.");
                        break;
                    }
                    if (pathToSave.equals("cancel")) break;
                }
            } else {
                System.out.println("Команда " + fullCommand[0] + " не существует.");
            }
        }
    }
}
