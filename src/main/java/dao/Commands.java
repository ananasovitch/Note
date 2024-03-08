package dao;

public enum Commands {
    HELP("выводит на экран список доступных команд с их описанием"),
    NOTE_NEW("создать новую заметку"),
    NOTE_LIST("выводит все заметки на экран"),
    NOTE_REMOVE("удаляет заметку"),
    NOTE_EXPORT("сохраняет все заметки в текстовый файл и выводит имя сохраненного файла"),
    EXIT("выход из приложения");


    private final String description;

    Commands(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static void printCommands() {
        for (Commands command : Commands.values()) {
            System.out.println(command.name() + " - " + command.getDescription());
        }
    }
}

