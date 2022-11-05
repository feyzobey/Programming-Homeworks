public class Item {
    private int id;
    public static int numberOfItems = 0;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item(int id) {
        this.id = id;
        numberOfItems++;
    }
}