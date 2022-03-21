package zuev.nikita;

/**
 * Класс для ошибок в данных, хранящихся в файле или вводимых пользователем.
 */
public class WrongDataException extends Exception {

    public WrongDataException(String message) {
        super(message);
    }

}
