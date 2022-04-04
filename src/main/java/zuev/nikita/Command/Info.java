package zuev.nikita.Command;

import zuev.nikita.Structure.Organization;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Выводит информацию о коллекции
 */
public class Info implements Command {


    @Override
    public String execute(String arg, Hashtable<String, Organization> hashtable, String savePath, List<String> history, HashMap<String, Command> commandList) {
        return "Коллекция представляет из себя Hashtable, хранящий объекты типа Organization. В данный момент в коллекции " + hashtable.size() + " элементов.";
    }

    @Override
    public String getHelp() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
