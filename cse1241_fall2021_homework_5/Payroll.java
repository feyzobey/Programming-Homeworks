public class Payroll {
    private int workhour, itemCount;

    public int getWorkhour() {
        return this.workhour;
    }

    public void setWorkhour(int workhour) {
        this.workhour = workhour;
    }

    public int getItemCount() {
        return this.itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public Payroll(int workhour, int itemCount) {
        this.workhour = workhour;
        this.itemCount = itemCount;
    }

    public int calculateSalary() {
        return workhour * 3 + itemCount * 2;
    }

    public String toString() {
        return "The work hour is " + workhour + " and the produced item count is " + itemCount + ".";
    }
}