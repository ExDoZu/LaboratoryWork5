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
import java.util.Set;

/**
 * Saves the collection to the file.
 */
public class Save extends Command {
    public Save(Hashtable<String, Organization> collection) {
        super(collection);
    }

    @Override
    public String execute(String arg,String savePath,  List<String> history, HashMap<String, Command> commandList, Set<File> scripts) throws IOException {
        if(arg!=null)return "Команда не нуждается в аргументе.";
        File file = new File(savePath);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(JsonDataHandler.hashtableToString(collection));
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
