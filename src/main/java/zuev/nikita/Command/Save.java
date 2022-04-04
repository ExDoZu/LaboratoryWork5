package zuev.nikita.Command;

import zuev.nikita.JsonDataHandler;
import zuev.nikita.Structure.Organization;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

/**
 * Сохраняет коллекцию в файл.
 */
public class Save implements Command {
    /**
     * @param savePath Путь к файлу для сохранения.
     */

    @Override
    public String execute(String arg, Hashtable<String, Organization> hashtable, String savePath, List<String> history, HashMap<String, Command> commandList) throws IOException {
        File file = new File(savePath);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(JsonDataHandler.hashtableToString(hashtable));
        return "Данные сохранены.";
    }

    @Override
    public String getHelp() {
        return "save : сохранить коллекцию в файл";
    }
}