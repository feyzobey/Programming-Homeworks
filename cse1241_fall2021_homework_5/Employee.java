public class Employee {
    private int id, workhour, speed;
    private String name, surname;
    private Payroll payroll;
    private Item[] items;

    public int getWorkhour() {
        return this.workhour;
    }

    public void setWorkhour(int workhour) {
        this.workhour = workhour;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Payroll getPayroll() {
        return this.payroll;
    }

    public void setPayroll(Payroll payroll) {
        this.payroll = payroll;
    }

    public Item[] getItems() {
        return this.items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee(int id, String name, String surname, int workhour, int speed) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.speed = speed;
        this.workhour = workhour;
        this.payroll = new Payroll(workhour, speed * workhour);
    }

    public Item[] startShift() {
        int itemCount = workhour * speed;
        items = new Item[itemCount];
        for (int i = 0; i < itemCount; i++) {
            items[i] = new Item(1 + (int) Math.random() * 100);
        }
        return items;
    }

    public Payroll endShift() {
        return payroll = new Payroll(workhour, items.length);
    }

    public String toString() {
        return "This is the employee with id " + id + " speed " + speed + ". " + payroll.toString();
    }
}