package zuev.nikita;

import zuev.nikita.command.*;
import zuev.nikita.structure.Organization;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


/**
 * Launch the program and starts command processing.
 *
 * @author Nikita Zuev
 */
public class ProgramLauncher {
    /**
     * Launches program
     *
     * @param path path to file for reading and writing.
     */
    public void launch(String path) {
        boolean flag = true;
        Scanner inputScanner = new Scanner(System.in);
        String response;
        Hashtable<String, Organization> collection = null;
        while (flag) {
            File file = new File(path);
            if (path.equals("exit")) break;
            else if (file.isDirectory()) {
                System.out.println("Укажите имя файла, а не директории.");
                path = inputScanner.nextLine().trim();
            } else if (file.exists()) {
                if (!(file.canRead() && file.canWrite())) {
                    System.out.println("Нехватка прав доступа. Укажите новое имя файла.");
                    path = inputScanner.nextLine().trim();
                } else {
                    try {
                        collection = JsonDataHandler.parseFile(path);
                        flag = false;
                    } catch (WrongDataException e) {
                        System.out.println("В файле содержатся некорректные данные. " + e.getMessage() +
                                "\nВведите путь к другому файлу.\n" +
                                "Для закрытия программы введите команду exit.\n" +
                                "Продолжить с данным файлом без данных из него (Предыдущие будут потеряны)? yes/no. (no = exit)");
                    } catch (Exception e) {
                        System.out.println("Структура файла некорректна или файл поврежден. Введите путь к другому файлу.\n" +
                                "Для закрытия программы введите команду exit.\n" +
                                "Продолжить с данным файлом без данных из него (Предыдущие данные будут потеряны)? yes/no. (no = exit)");
                    }
                    if (flag) {
                        response = inputScanner.nextLine().trim();
                        if (response.equalsIgnoreCase("exit") || response.equalsIgnoreCase("no") || response.equalsIgnoreCase("0"))
                            flag = false;
                        else if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("1")) {
                            collection = new Hashtable<>();
                            flag = false;
                        } else {
                            path = response;
                        }
                    }
                }
            } else {
                System.out.println("Файл не обнаружен. Укажите новое имя файла.");
                path = inputScanner.nextLine().trim();
            }
        }
        if (collection == null) return;
        Invoker invoker = new Invoker();
        invoker.register("exit", new Exit());
        invoker.register("info", new Info(collection));
        invoker.register("clear", new Clear(collection));
        invoker.register("execute_script", new ExecuteScript(collection));
        invoker.register("filter_greater_than_postal_address", new FilterGreaterThanPostalAddress(collection));
        invoker.register("help", new Help());
        invoker.register("history", new History());
        invoker.register("insert", new Insert(collection));
        invoker.register("print_field_ascending_postal_address", new PrintFieldAscendingPostalAddress(collection));
        invoker.register("print_field_descending_postal_address", new PrintFieldDescendingPostalAddress(collection));
        invoker.register("remove_greater_key", new RemoveGreaterKey(collection));
        invoker.register("remove_key", new RemoveKey(collection));
        invoker.register("remove_lower", new RemoveLower(collection));
        invoker.register("save", new Save(collection));
        invoker.register("show", new Show(collection));
        invoker.register("update", new Update(collection));
        String[] fullCommand;
        String bufPath;
        //'while' statement completes after inputting  the 'exit' command
        boolean exitFlag = true;
        String invokerResponse;
        while (exitFlag) {
            fullCommand = inputScanner.nextLine().trim().split("\\s+", 2);
            if (!fullCommand[0].equals(""))
                while (true) {
                    try {
                        invokerResponse = invoker.invoke(fullCommand, path);
                        if (invokerResponse.equals("exit"))
                            exitFlag = false;
                        else
                            System.out.println(invokerResponse);
                        break;
                    } catch (FileNotFoundException e) {
                        if (e.getMessage().equals("Нет доступа к файлу из-за нехватки прав доступа.")) {
                            System.out.println("Нехватка прав доступа. Укажите путь к новому файлу.\n" +
                                    "cancel - отменить команду сохранения.");
                            bufPath = inputScanner.nextLine().trim();
                        } else {
                            System.out.println("Файл не найден.");
                            break;
                        }
                    } catch (IOException e) {
                        System.out.println("Нехватка прав доступа. Укажите путь к новому файлу.\n" +
                                "cancel - отменить команду сохранения.");
                        bufPath = inputScanner.nextLine().trim();
                    } catch (NumberFormatException e) {
                        System.out.println("ID должен быть целым числом.");
                        break;
                    }
                    if (bufPath.equals("cancel")) break;
                    else path = bufPath;
                }
        }
    }
}
