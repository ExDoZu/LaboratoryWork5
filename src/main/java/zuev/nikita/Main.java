package zuev.nikita;


/**
 * Здесь-то все и начинается...
 */
public class Main {
    public static void main(String[] args) {
        CommandHandler commandHandler = new CommandHandler();
        commandHandler.launch(args[0]);
    }
}
