import java.util.Arrays;

public class Factory {
    private String name;
    private Employee[] employees;
    private Storage storage;
    private Payroll[] payrolls;
    private double itemPrice;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee[] getEmployees() {
        return this.employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Payroll[] getPayrolls() {
        return this.payrolls;
    }

    public void setPayrolls(Payroll[] payrolls) {
        this.payrolls = payrolls;
    }

    public double getItemPrice() {
        return this.itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Factory(String name, int capacity, double itemPrice) {
        this.name = name;
        this.itemPrice = itemPrice;
        this.employees = new Employee[0];
        this.payrolls = new Payroll[0];
        this.storage = new Storage(capacity);
    }

    public double getRevenue() {
        return storage.getItems().length * itemPrice;
    }

    public double getPaidSalaries() {
        var totalPaid = 0;
        for (Payroll payroll : payrolls) {
            totalPaid += payroll.calculateSalary();
        }
        return totalPaid;
    }

    public void addEmployee(Employee employee) {
        employees = Arrays.copyOf(employees, employees.length + 1);
        employees[employees.length - 1] = employee;
        var items = employee.startShift();
        for (Item item : items) {
            storage.addItem(item);
        }
    }

    public Employee removeEmployee(int id) {
        if (employees.length == 0) {
            System.out.println("There are no employees!");
            return null;
        }
        boolean isExist = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getId() == id) {
                isExist = true;
            }
        }
        if (!isExist) {
            System.out.println("Employee does not exist!");
            return null;
        }
        Employee employee = null;
        var employ = Arrays.copyOf(employees, employees.length - 1);
        for (int i = 0, k = 0; i < employees.length; i++) {
            if (employees[i].getId() == id) {
                employee = employees[i];
                continue;
            } else {
                employ[k++] = employees[i];
            }
        }
        employees = employ;
        var payroll = employee.endShift();
        addPayroll(payroll);
        return employee;
    }

    private void addPayroll(Payroll payroll) {
        payrolls = Arrays.copyOf(payrolls, payrolls.length + 1);
        payrolls[payrolls.length - 1] = payroll;
    }
}