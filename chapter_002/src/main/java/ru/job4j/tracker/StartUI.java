package ru.job4j.tracker;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class StartUI {

    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";

    /**
     * Константа меню для отображения всех заявок.
     */
    private static final String SHOW = "1";

    /**
     * Константа меню для редактирования заявки.
     */
    private static final String EDIT = "2";

    /**
     * Константа меню для удаления заявки.
     */
    private static final String DELETE = "3";

    /**
     * Константа меню для поиска заявки по Id.
     */
    private static final String FIND_BY_ID = "4";

    /**
     * Константа меню для поиска заявки по name.
     */
    private static final String FIND_BY_NAME = "5";

    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню: ");
            if (ADD.equals(answer)) {
                System.out.println("------------ Добавление новой заявки --------------");
                this.createItem();
            } else if (SHOW.equals(answer)) {
                System.out.println("----------- Заявки в Tracker -------------\n");
                this.showItem();
            } else if (EDIT.equals(answer)) {
                System.out.println("------------ Редактирование заявки --------------\n");
                this.editItem();
            } else if (DELETE.equals(answer)) {
                System.out.println("------------ Удаление заявки --------------\n");
                this.deleteItem();
            } else if (FIND_BY_ID.equals(answer)) {
                System.out.println("------------ Поиск заявки по Id --------------\n");
                this.findById();
            } else if (FIND_BY_NAME.equals(answer)) {
                System.out.println("------------ Поиск заявки по имени --------------\n");
                this.findByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
            System.out.println();
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {

        String name = this.input.ask("Введите имя заявки: ");
        String desc = this.input.ask("Введите описание заявки: ");
        Item item = new Item(name, desc, System.currentTimeMillis());
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId: " + item.getId() + "-----------");
    }

    /**
     * Метод реализует отображение всех заявок.
     */
    private void showItem() {
        for (Item item : tracker.findAll()) {
            System.out.println("Id заявки: " + item.getId());
            System.out.println("Имя заявки: " + item.getName());
            System.out.println("Описание заявки: " + item.getDescription());
            System.out.println("Время создания заявки: " + item.getCreate() + "\n");
        }
    }

    /**
     * Метод реализует редактирования заявки.
     */
    private void editItem() {
        String id = this.input.ask("Введите id редактируемой заявки: ");
        String name = this.input.ask("Введите новое имя заявки: ");
        String desc = this.input.ask("Введите новое описание заявки: ");
        Item item = new Item(name, desc, System.currentTimeMillis());
        tracker.replace(id, item);
    }

    /**
     * Метод реализует удаление заявки.
     */
    private void deleteItem() {
        String id = this.input.ask("Введите id удаляемой заявки: ");
        tracker.delete(id);
    }

    /**
     * Метод реализует поиск заявки по Id.
     */
    private void findById() {
        String id = this.input.ask("Введите id искомой заявки: ");
        Item item = tracker.findById(id);
        System.out.println("Id заявки: " + item.getId());
        System.out.println("Имя заявки: " + item.getName());
        System.out.println("Описание заявки: " + item.getDescription());
        System.out.println("Время создания заявки: " + item.getCreate());
    }

    /**
     * Метод реализует поиск заявки по name.
     */
    private void findByName() {
        String name = this.input.ask("Введите имя искомой заявки: ");
        for (Item item : tracker.findByName(name)) {
            System.out.println("Id заявки: " + item.getId());
            System.out.println("Имя заявки: " + item.getName());
            System.out.println("Описание заявки: " + item.getDescription());
            System.out.println("Время создания заявки: " + item.getCreate() + "\n");
        }
    }

    private void showMenu() {
        System.out.println("Меню:");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program\n");
    }

    /**
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
