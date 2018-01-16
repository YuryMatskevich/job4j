package ru.job4j.tracker;



import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class StartUITest {
    /**
     * Добовление заявки
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }
    /**
     * Редактирование заявки
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc", 123L));
        Input input = new StubInput(new String[]{"1", item.getId(), "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }
    /**
     * Удаление заявки
     */
    @Test
    public void whenDeleteThenTrackerHasNotDeletedItem() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name1", "desc1",
                                                 "0", "test name2", "desc2", "6"});
        new StartUI(input, tracker).init();
        Item item = tracker.findAll()[0];
        tracker.delete(item.getId());
        assertThat(tracker.findAll()[0].getName(), is("test name2"));
    }
}
