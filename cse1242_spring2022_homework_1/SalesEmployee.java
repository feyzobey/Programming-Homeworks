import java.util.ArrayList;
import java.util.Calendar;

public class SalesEmployee extends RegularEmployee {
    private ArrayList<Product> sales;
    public static int numberOfSalesEmployees;
    private double totalValuesFromSales;// extra field

    public SalesEmployee(int id, String firstName, String lastName, String gender, Calendar birthDate,
            String maritalStatus, String hasDriverLicence, double salary, Calendar hireDate, Department department,
            double pScore, ArrayList<Product> s) throws Exception {
        super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence, salary, hireDate, department,
                pScore);
        sales = s;
        numberOfSalesEmployees++;
        calculateTotalValuesFromSales(sales);
    }

    public SalesEmployee(RegularEmployee re, ArrayList<Product> s) throws Exception {
        this(re.getId(), re.getFirstName(), re.getLastName(), re.getGender(), re.getBirthDate(), re.getMaritalStatus(),
                re.getHasDriverLicence(), re.getSalary(), re.getHireDate(), re.getDepartment(),
                re.getPerformanceScore(), s);
    }

    public double getTotalValuesFromSales() {
        return totalValuesFromSales;
    }

    public void setTotalValuesFromSales(double totalValuesFromSales) {
        this.totalValuesFromSales = Math.round((100 * totalValuesFromSales) / 100.0);
    }

    public ArrayList<Product> getSales() {
        return sales;
    }

    public void setSales(ArrayList<Product> sales) {
        this.sales = sales;
    }

    public boolean addSale(Product s) throws Exception {
        if (!sales.contains(s)) {
            return sales.add(s);
        }
        throw new Exception("This product exists");
    }

    public boolean removeSale(Product s) throws Exception {
        if (sales.contains(s)) {
            return sales.remove(s);
        }
        throw new Exception("This product does not exist");
    }

    public void calculateTotalValuesFromSales(ArrayList<Product> sales) {
        double total = 0;
        for (Product sale : sales) {
            total += sale.getPrice();
        }
        setTotalValuesFromSales(total);
    }

    @Override
    public String toString() {
        return super.toString() + "\n\t\t\t\t" + sales;
    }
}
