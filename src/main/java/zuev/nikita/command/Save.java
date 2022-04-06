package zuev.nikita.command;

import zuev.nikita.JsonDataHandler;
import zuev.nikita.structure.Organization;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Saves the collection to the file.
 */
public class Save implements Command {
    /**
     * @param savePath Path to the file to save.
     */

    @Override
    public String execute(String arg, Hashtable<String, Organization> hashtable, String savePath, List<String> history, HashMap<String, Command> commandList) throws IOException {
        File file = new File(savePath);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(JsonDataHandler.hashtableToString(hashtable));
            fileWriter.close();
        } catch (Exception e) {
            throw new FileNotFoundException("Нет доступа к файлу из-за нехватки прав доступа.");
        }

        return "Данные сохранены.";
    }

    @Override
    public String getHelp() {
        return "save : сохранить коллекцию в файл";
    }
}
