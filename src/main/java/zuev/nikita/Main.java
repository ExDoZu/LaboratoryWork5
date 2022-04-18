package zuev.nikita;

import java.util.Scanner;

/**
 * This is where it all starts...
 *
 * @author Nikita Zuev
 */
public class Main {
    public static void main(String[] args) {
        ProgramLauncher programLauncher = new ProgramLauncher();
        while (true) {
            try {
                programLauncher.launch(args[0]);
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Не указан путь к файлу.\n" +
                        "Укажите путь к файлу или введите exit для выхода из программы.");
                args = new String[1];
                args[0] = UserInputManager.input();
                if (args[0].equals("exit")) break;
            }
        }
    }
}
