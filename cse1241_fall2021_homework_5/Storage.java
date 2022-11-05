import java.util.Arrays;

public class Storage {
    private int capacity;
    private Item[] items;

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Item[] getItems() {
        return this.items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public Storage(int capacity) {
        this.capacity = capacity;
        this.items = new Item[0];
    }

    public void addItem(Item item) {
        items = Arrays.copyOf(items, items.length + 1);
        items[items.length - 1] = item;
    }
}