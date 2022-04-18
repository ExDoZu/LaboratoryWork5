package zuev.nikita;

import java.util.Scanner;

public class UserInputManager {
    private static  final Scanner inputScanner = new Scanner(System.in);

    public static String input() {
        if (inputScanner.hasNextLine())
            return inputScanner.nextLine().trim();
        else {
            System.out.println("Команда Ctrl+D завершила выполнение программы.");
            System.exit(0);
            return "";

        }
    }
}
