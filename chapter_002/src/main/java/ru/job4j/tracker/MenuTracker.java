package ru.job4j.tracker;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class MenuTracker {

    private int[] ranges = new int[] {0, 1, 2, 3, 4, 5, 6};
    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[7];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public int[] getRanges() {
        return ranges;
    }

    public void fillActions() {
        this.actions[0] = this.new AddItem();
        this.actions[1] = this.new ShowItems();
        this.actions[2] = this.new EditItem();
        this.actions[3] = new MenuTracker.DeleteItems();
        this.actions[4] = new MenuTracker.FindBiID();
        this.actions[5] = new MenuTracker.FindBiName();
        this.actions[6] = new ExitProgram();
    }



    public boolean select(int key) {
        this.actions[key].execute(this.input, this.tracker);
        boolean showMenu = true;
        if (key == 6) {
            showMenu = false;
        }
        return showMenu;
    }

    public void show() {
        for (UserAction action : this.actions) {
            System.out.println(action.info());
        }
    }

    private class AddItem implements UserAction {

        public int key() {
            return 0;
        }

        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите имя заявки: ");
            String desc = input.ask("Введите описание: ");
            tracker.add(new Item(name, desc, System.currentTimeMillis()));
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Добавление новой заявки");
        }
    }

    private class ShowItems implements UserAction {

        public int key() {
            return 1;
        }

        public void execute(Input input, Tracker tracker) {
            for (Item item : tracker.findAll()) {
                System.out.println(String.format("Id заявки: %s%nИмя заявки: %s%nОписание заявки: %s%nВремя создания заявки: %s%n",
                                                    item.getId(), item.getName(), item.getDescription(), item.getCreate()));
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Показать все заявки");
        }
    }

    private class EditItem implements UserAction {

        public int key() {
            return 2;
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите id редактируемой заявки: ");
            String name = input.ask("Введите новое имя заявки: ");
            String desc = input.ask("Введите новое описание заявки: ");
            Item item = new Item(name, desc, System.currentTimeMillis());
            tracker.replace(id, item);
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Редактирование заявки");
        }
    }

    private static class DeleteItems implements UserAction {

        public int key() {
            return 3;
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите id удаляемой заявки: ");
            tracker.delete(id);
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Удаление заявки");
        }
    }

    private static class FindBiID implements UserAction {

        public int key() {
            return 4;
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите id искомой заявки: ");
            Item item = tracker.findById(id);
            System.out.println(String.format("Id заявки: %s%nИмя заявки: %s%nОписание заявки: %s%nВремя создания заявки: %s%n",
                    item.getId(), item.getName(), item.getDescription(), item.getCreate()));
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Поиск заявки по id");
        }
    }

    private static class FindBiName implements UserAction {

        public int key() {
            return 5;
        }

        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите имя искомой заявки: ");
            for (Item item : tracker.findByName(name)) {
                System.out.println(String.format("Id заявки: %s%nИмя заявки: %s%nОписание заявки: %s%nВремя создания заявки: %s%n",
                        item.getId(), item.getName(), item.getDescription(), item.getCreate()));
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Поиск заявки по имени");
        }
    }
}

class ExitProgram implements UserAction {

    public int key() {
        return 6;
    }

    public void execute(Input input, Tracker tracker) {

    }

    public String info() {
        return String.format("%s. %s", this.key(), "Выход их программы");
    }
}
