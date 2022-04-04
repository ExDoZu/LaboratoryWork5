package zuev.nikita;

import zuev.nikita.Command.Invoker;
import zuev.nikita.Structure.Organization;

import java.util.*;
import java.io.FileNotFoundException;

/**
 * Запускает программу и вызывает обработку команд.
 *
 * @author Никита Зуев
 */
public class ProgrammLauncher {
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
            try {
                hashtable = JsonDataHandler.parseFile(path);
                flag = false;
            } catch (FileNotFoundException e) {
                System.out.println("Указанный вами файл не обнаружен. Введите новый путь к файлу.\n" +
                        "Для закрытия программы введите команду exit\n" +
                        "Желаете создать пустой файл в указанном месте? Да/Нет. (Нет = exit)");
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
                response = input.nextLine().toLowerCase();
                if (response.equals("exit") || response.equals("нет") || response.equals("0")) flag = false;
                else if (response.equals("да") || response.equals("1")) {
                    hashtable = new Hashtable<>();
                    flag = false;
                } else {
                    path = response;
                }
            }
        }

        if (hashtable == null) return;
        Invoker.invoke(hashtable, path, new Scanner(System.in));
    }
}
