package zuev.nikita;

import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

/**
 * Занимается обработкой команд от пользователя.
 *
 * @author Никита Зуев
 */
public class CommandHandler {
    private short recursionDepth = 0;

    /**
     * Запуск обработчика команд.
     *
     * @param path путь к файлу для взятия и сохранения данных.
     */
    public void launch(final String path) {
        Hashtable<String, Organization> hashtable = JsonDataHandler.parseFile(path);
        if (hashtable == null) return;
        commandExecutor(hashtable, path, new Scanner(System.in));
    }

    /**
     * Исполнитель команд.
     *
     * @param hashtable  Hashtable, с которым будет работать пользователь.
     * @param pathToSave Путь к файлу для сохранения коллекции
     * @param input      Scanner с известным потоком ввода.
     */
    private void commandExecutor(Hashtable<String, Organization> hashtable, String pathToSave, Scanner input) {
        Scanner commandParser;
        String fullCommand;
        String command;
        String[] commandsHistory = new String[10];
        boolean gotCommandExit = false, okcommand;
        while (!gotCommandExit) {
            okcommand = true;
            try {
                fullCommand = input.nextLine().trim();
            } catch (Exception e) {
                System.out.println("Проблема с чтением команд. Если используется файл-скрипт, проверьте, что в конце" +
                        " написана команда exit.");
                break;
            }

            commandParser = new Scanner(fullCommand);
            command = commandParser.next();

            switch (command) {
                case "exit":
                    gotCommandExit = true;
                    break;
                case "help":
                    help();
                    break;
                case "info":
                    info(hashtable);
                    break;
                case "save":
                    save(hashtable, pathToSave);
                    break;
                case "clear":
                    clear(hashtable);
                    break;
                case "history":
                    history(commandsHistory);
                    break;
                case "show":
                    show(hashtable);
                    break;
                case "insert":
                    if (commandParser.hasNext())
                        insert(commandParser.nextLine().trim(), hashtable);
                    else {
                        System.out.println("Не передан аргумент для команды.");
                        okcommand = false;
                    }
                    break;
                case "update":
                    try {
                        if (commandParser.hasNext())
                            update(Integer.parseInt(commandParser.nextLine().trim()), hashtable);
                        else {
                            System.out.println("Не передан аргумент для команды.");
                            okcommand = false;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("id должен быть числом.");
                        okcommand = false;
                    }
                    break;
                case "remove_key":
                    if (commandParser.hasNext())
                        hashtable.remove(commandParser.nextLine().trim());
                    else {
                        System.out.println("Не передан аргумент для команды.");
                        okcommand = false;
                    }
                    break;
                case "execute_script":
                    if (commandParser.hasNext())
                        execute_script(commandParser.nextLine().trim(), pathToSave, hashtable);
                    else {
                        System.out.println("Не передан аргумент для команды.");
                        okcommand = false;
                    }
                    break;
                case "remove_lower":
                    remove_lower(hashtable);
                    break;
                case "remove_greater_key":
                    if (commandParser.hasNext())
                        remove_greater_key(commandParser.nextLine().trim(), hashtable);
                    else {
                        System.out.println("Не передан аргумент для команды.");
                        okcommand = false;
                    }
                    break;
                case "filter_greater_than_postal_address":
                    if (commandParser.hasNext())
                        filter_greater_than_postal_address(commandParser.nextLine().trim(), hashtable);
                    else {
                        System.out.println("Не передан аргумент для команды.");
                        okcommand = false;
                    }
                    break;
                case "print_field_ascending_postal_address":
                    print_field_ascending_postal_address(hashtable);
                    break;
                case "print_field_descending_postal_address":
                    print_field_descending_postal_address(hashtable);
                    break;
                default:
                    System.out.println("Нет такой команды.");
                    okcommand = false;
                    break;
            }
            if (okcommand)
                changeHistory(commandsHistory, fullCommand);
        }
    }

    /**
     * Метод для ввода пользователем структуры через терминал.
     *
     * @param hashtable Сама коллекция, собственно.
     * @return Organization
     */
    private Organization organizationInput(Hashtable<String, Organization> hashtable) {
        Scanner scanner = new Scanner(System.in);
        Set<Integer> ids = new HashSet<>();
        for (String key : hashtable.keySet())
            ids.add(hashtable.get(key).getId());
        int id = 0;
        for (int i = 1; i <= ids.size() + 1; i++)
            if (!ids.contains(i)) id = i;
        String name = null, zipCode = null, orgTypeString;
        Date creationDate = new Date();
        double annualTurnover = 0, y = 0;
        OrganizationType type = null;
        long x = 0;
        boolean flag = true;
        while (flag) {
            System.out.print("name: ");
            name = scanner.nextLine();
            if (name != null && !name.equals("")) flag = false;
            else System.out.println("name не может быть null и не может быть пустым.");
        }
        System.out.println("Coordinates:");
        flag = true;
        while (flag) {
            System.out.print("X: ");
            try {
                x = Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("X дожен быть целым числом.");
                continue;
            }
            if (x <= 923) flag = false;
            else System.out.println("X дожен быть не больше 923.");
        }
        flag = true;
        while (flag) {
            System.out.print("Y: ");
            try {
                y = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Y дожен быть дробным числом. Дробное число пишется через точку.");
                continue;
            }
            flag = false;
        }
        flag = true;
        while (flag) {
            System.out.print("annualTurnover: ");
            try {
                annualTurnover = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("annualTurnover дожен быть дробным числом. Дробное число пишется через точку.");
                continue;
            }
            if (annualTurnover > 0) flag = false;
            else System.out.println("annualTurnover должен быть больше 0.");
        }
        flag = true;
        while (flag) {
            flag = false;
            System.out.print("OrganizationType (COMMERCIAL - 1, PUBLIC - 2, TRUST - 3): ");
            orgTypeString = scanner.nextLine();
            switch (orgTypeString) {
                case "COMMERCIAL":
                case "1":
                    type = OrganizationType.COMMERCIAL;
                    break;
                case "PUBLIC":
                case "2":
                    type = OrganizationType.PUBLIC;
                    break;
                case "TRUST":
                case "3":
                    type = OrganizationType.TRUST;
                    break;
                default:
                    System.out.println("Нет такого типа Организации " + orgTypeString);
                    flag = true;
                    break;
            }
        }
        flag = true;
        while (flag) {
            System.out.print("Address: ");
            zipCode = scanner.nextLine();
            if (zipCode != null) flag = false;
        }
        System.out.println("Данные успешно введены.");
        return new Organization(id, name, new Coordinates(x, y), creationDate, annualTurnover, type, new Address(zipCode));
    }

    /**
     * Вставляет в коллекцию Organization, введенный пользователем вручную по заданному ключу.
     *
     * @param key       Ключ для коллекции
     * @param hashtable Сама коллекция, собственно.
     */
    private void insert(String key, Hashtable<String, Organization> hashtable) {
        hashtable.put(key, organizationInput(hashtable));
    }

    /**
     * Обновляет элемент коллекции по его ID.
     *
     * @param id        Сам ID, собственно.
     * @param hashtable Сама коллекция, собственно.
     */
    private void update(int id, Hashtable<String, Organization> hashtable) {
        Organization organization = organizationInput(hashtable);
        organization.setId(id);
        for (String key : hashtable.keySet())
            if (hashtable.get(key).getId() == id) {
                hashtable.replace(key, organization);
                break;
            }
    }

    /**
     * Выполняет скрипт, написанный в другом файле.
     *
     * @param fileName   Путь к файлу со скриптом.
     * @param pathToSave Файл для сохранения коллекции.
     * @param hashtable  Сама коллекция, собственно.
     */
    private void execute_script(String fileName, String pathToSave, Hashtable<String, Organization> hashtable) {
        if (recursionDepth < 10) {
            recursionDepth++;
            try {
                Scanner scanner = new Scanner(new File(fileName));
                commandExecutor(hashtable, pathToSave, scanner);
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("Был указан неверный путь к файлу для исполнения");
            }
            recursionDepth--;
        } else {
            System.out.println("Невозможно одновременно выполнять более 10 файлов-скриптов.");
        }
    }

    /**
     * Удаляет из коллекции все элементы, меньшие, чем заданный.
     *
     * @param hashtable Сама коллекция, собственно.
     */
    private void remove_lower(Hashtable<String, Organization> hashtable) {
        Organization organization = organizationInput(hashtable);
        List<String> keys = new ArrayList<>(hashtable.keySet());
        for (String key : keys)
            if (hashtable.get(key).compareTo(organization) < 0) hashtable.remove(key);
    }

    /**
     * Удаляет из коллекции все элементы, ключ которых превышает заданный.
     *
     * @param key       Заданный ключ
     * @param hashtable Сама коллекция, собственно.
     */
    private void remove_greater_key(String key, Hashtable<String, Organization> hashtable) {
        List<String> keys = new ArrayList<>(hashtable.keySet());
        for (String key2 : keys)
            if (key2.compareTo(key) > 0) hashtable.remove(key2);

    }

    /**
     * Выводит все элементы, значение поля postalAddress которых превышает заданный.
     *
     * @param postalAddress Заданный адрес.
     * @param hashtable     Сама коллекция, собственно.
     */
    private void filter_greater_than_postal_address(String postalAddress, Hashtable<String, Organization> hashtable) {
        Address userInputPostalAddress = new Address(postalAddress);
        for (String keyFromHashtable : hashtable.keySet()) {
            if (hashtable.get(keyFromHashtable).getPostalAddress().compareTo(userInputPostalAddress) > 0)
                System.out.println(hashtable.get(keyFromHashtable));

        }
    }

    /**
     * Выводит значения поля postalAddreess всех элементов в порядке возрастания.
     *
     * @param hashtable Сама коллекция, собственно.
     */
    private void print_field_ascending_postal_address(Hashtable<String, Organization> hashtable) {
        List<Address> addresses = new ArrayList<>();
        for (String key : hashtable.keySet())
            addresses.add(hashtable.get(key).getPostalAddress());

        addresses.sort(Address::compareTo);
        for (Address address : addresses)
            System.out.println(address);
    }

    /**
     * Выводит значения поля postalAddreess всех элементов в порядке убывания.
     *
     * @param hashtable Сама коллекция, собственно.
     */
    private void print_field_descending_postal_address(Hashtable<String, Organization> hashtable) {
        List<Address> addresses = new ArrayList<>();
        for (String key : hashtable.keySet())
            addresses.add(hashtable.get(key).getPostalAddress());

        addresses.sort(Address::compareTo);
        for (int i = addresses.size() - 1; i >= 0; i--)
            System.out.println(addresses.get(i));
    }

    /**
     * Выводит все элементы коллекции в строковом представлении.
     *
     * @param hashtable Сама коллекция, собственно.
     */
    private void show(Hashtable<String, Organization> hashtable) {
        System.out.println("Элементы, хранящиеся в коллекции:");
        for (String key : hashtable.keySet()) {
            System.out.println("Ключ " + key + ".");
            System.out.println(hashtable.get(key));
        }
    }

    /**
     * Выводит справку по доступным командам.
     */
    private void help() {
        System.out.println("Все команды должны вводиться в нижнем регистре по одной команде на строке!\n" +
                "help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "insert null {element} : добавить новый элемент с заданным ключом\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_key null : удалить элемент из коллекции по его ключу\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "history : вывести последние 10 команд (без их аргументов)\n" +
                "remove_greater_key null : удалить из коллекции все элементы, ключ которых превышает заданный\n" +
                "filter_greater_than_postal_address postalAddress : вывести элементы, значение поля postalAddress которых больше заданного\n" +
                "print_field_ascending_postal_address : вывести значения поля postalAddress всех элементов в порядке возрастания\n" +
                "print_field_descending_postal_address : вывести значения поля postalAddress всех элементов в порядке убывания");
    }

    /**
     * Выводит информацию о коллекции
     *
     * @param hashtable Сама коллекция, собственно.
     */
    private void info(Hashtable<String, Organization> hashtable) {
        System.out.println("Коллекция представляет из себя Hashtable, хранящий объекты типа Organization. В данный момент в коллекции " + hashtable.size() + " элементов.");
    }

    /**
     * Сохраняет коллекцию в файл.
     *
     * @param hashtable Сама коллекция, собственно.
     * @param path      Путь к файлу для сохранения.
     */
    private void save(Hashtable<String, Organization> hashtable, String path) {
        File file = new File(path);
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(JsonDataHandler.hashtableToString(hashtable));
                flag = false;
            } catch (IOException e) {
                System.out.println("Что-то пошло не так в процессе сохранения. Укажите новый путь для файла с названием самого файла. Например: ./directory/data.json");
            }
            if (flag) file = new File(input.nextLine());
        }
    }

    /**
     * Очищает коллекцию.
     *
     * @param hashtable Сама коллекция, собственно.
     */
    private void clear(Hashtable<String, Organization> hashtable) {
        hashtable.clear();
    }

    /**
     * Модифицирует историю при появлении новой команды.
     *
     * @param commandHistory Старая история.
     * @param newCommand     Новая команда, добавляемая в историю.
     */
    private void changeHistory(String[] commandHistory, String newCommand) {
        if (commandHistory.length - 1 >= 0)
            System.arraycopy(commandHistory, 1, commandHistory, 0, commandHistory.length - 1);
        commandHistory[commandHistory.length - 1] = newCommand;

    }

    /**
     * Выводит последние 10 команд.
     *
     * @param commandHistory Сама коллекция, собственно.
     */
    private void history(String[] commandHistory) {
        System.out.println("История команд:");
        for (String command : commandHistory)
            if (command != null) System.out.println("-- " + command);
    }
}
