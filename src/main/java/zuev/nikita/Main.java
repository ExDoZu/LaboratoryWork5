package zuev.nikita;


import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This is where it all starts...
 *
 * @author Nikita Zuev
 */
public class Main {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        ProgramLauncher programLauncher = new ProgramLauncher();
        while (true) {
            try {
                programLauncher.launch(args[0]);
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Не указан путь к файлу.\n" +
                        "Укажите путь к файлу или введите exit для выхода из программы.");
                args = new String[1];
                args[0] = inputScanner.nextLine().trim();
                if (args[0].equals("exit")) break;
            } catch (NoSuchElementException e) {
                break;
            }
        }
    }
}
