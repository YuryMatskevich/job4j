package ru.job4j.tracker;

import java.util.Arrays;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    public Item add(Item item) {
        this.items[this.position++] = item;
        return item;
    }

    public void replace(String id, Item item) {
        int i;
        for (i = 0; i < position; i++) {
            if (id.equals(this.items[i].getId())) {
                break;
            }
        }
        this.items[i].setName(item.getName());
        this.items[i].setDescription(item.getDescription());
        this.items[i].setCreate(item.getCreate());
    }

    public void delete(String id) {
        int i;
        for (i = 0; i < position; i++) {
            if (this.items[i].getId().equals(id)) {
                break;
            }
        }
        if (i != position) {
            for ( ; i < position - 1; i++) {
                this.items[i] = this.items[i + 1];
            }
            position--;
            Item[] cur = new Item[position];
            System.arraycopy(items, 0, cur, 0, position);
            items = cur;
        }
    }

    public Item[] findAll() {
        Item[] cur = new Item[position];
        for (int i = 0; i < position; i++) {
            cur[i] = items[i];
        }
        return cur;
    }

    public Item[] findByName(String key) {
        Item[] cur = new Item[position];
        int elem = 0;
        for (int i = 0; i < position; i++) {
            if (items[i].getName().equals(key)) {
                cur[elem++] = items[i];
            }
        }
        return Arrays.copyOf(cur, elem);
    }

    public Item findById(String id) {
        int i;
        for (i = 0; i < position; i++) {
            if (items[i].getId().equals(id)) {
                break;
            }
        }
        if (i == position) {
            return null;
        } else {
            return items[i];
        }
    }
}
