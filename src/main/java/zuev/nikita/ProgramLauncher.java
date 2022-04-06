package zuev.nikita;

import zuev.nikita.command.Invoker;
import zuev.nikita.structure.Organization;

import java.io.File;
import java.util.*;


/**
 * Launch the program and starts command processing.
 *
 * @author Nikita Zuev
 */
public class ProgramLauncher {
    /**
     * Запуск программы и обработчика команд.
     *
     * @param path путь к файлу для взятия и сохранения данных.
     */
    public void launch(String path) {
        boolean flag = true;
        Scanner input = new Scanner(System.in);
        String response;
        Hashtable<String, Organization> hashtable = null;
        while (flag) {
            File file = new File(path);
            if (path.equals("exit")) break;
            else if (path.charAt(path.length() - 1) == '/' || path.charAt(path.length() - 1) == '\\') {
                System.out.println("Укажите имя файла, а не директории.");
                path = input.nextLine().trim();
            } else if (file.exists()) {
                if (!(file.canRead() && file.canWrite())) {
                    System.out.println("Нехватка прав доступа. Укажите новое имя файла.");
                    path = input.nextLine().trim();
                } else {
                    try {
                        hashtable = JsonDataHandler.parseFile(path);
                        flag = false;
                    } catch (WrongDataException e) {
                        System.out.println("В файле содержатся некорректные данные. " + e.getMessage() +
                                "\nВведите путь к другому файлу.\n" +
                                "Для закрытия программы введите команду exit.\n" +
                                "Желаете продолжить с данным файлом без данных из него (Предыдущие будут потеряны)? Да/Нет. (Нет = exit)");
                    } catch (Exception e) {
                        System.out.println("Структура файла некорректна или файл поврежден. Введите путь к другому файлу.\n" +
                                "Для закрытия программы введите команду exit.\n" +
                                "Желаете продолжить с данным файлом без данных из него (Предыдущие данные будут потеряны)? Да/Нет. (Нет = exit)");
                    }
                    if (flag) {
                        response = input.nextLine().trim();
                        if (response.equalsIgnoreCase("exit") || response.equalsIgnoreCase("нет") || response.equalsIgnoreCase("0"))
                            flag = false;
                        else if (response.equalsIgnoreCase("да") || response.equalsIgnoreCase("1")) {
                            hashtable = new Hashtable<>();
                            flag = false;
                        } else {
                            path = response;
                        }
                    }
                }
            } else {
                System.out.println("Файл не обнаружен. Укажите новое имя файла.");
                path = input.nextLine().trim();
            }
        }

        if (hashtable == null) return;
        Invoker.invoke(hashtable, path, new Scanner(System.in));
    }
}
