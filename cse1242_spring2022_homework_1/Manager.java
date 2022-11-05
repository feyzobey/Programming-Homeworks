import java.util.ArrayList;
import java.util.Calendar;

public class Manager extends Employee {
    private ArrayList<RegularEmployee> regularEmployees;
    private double bonusBudget;

    public Manager(int id, String firstName, String lastName, String gender, Calendar birthDate, String maritalStatus,
            String hasDriverLicence, double salary, Calendar hireDate, Department department, double bonusBudget)
            throws Exception {
        super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence, salary, hireDate,
                department);

        this.bonusBudget = bonusBudget;
        regularEmployees = new ArrayList<>();
    }

    public Manager(Employee employee, double bonusBudget) throws Exception {
        this(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getGender(),
                employee.getBirthDate(), employee.getMaritalStatus(), employee.getHasDriverLicence(),
                employee.getSalary(), employee.getHireDate(), employee.getDepartment(), bonusBudget);
    }

    public ArrayList<RegularEmployee> getRegularEmployees() {
        return this.regularEmployees;
    }

    public void setRegularEmployees(ArrayList<RegularEmployee> regularEmployees) {
        this.regularEmployees = regularEmployees;
    }

    public double getBonusBudget() {
        return this.bonusBudget;
    }

    public void setBonusBudget(double bonusBudget) {
        this.bonusBudget = Math.round((100 * bonusBudget)) / 100.0;
    }

    public void addEmployee(RegularEmployee e) {
        regularEmployees.add(e);
    }

    public void removeEmployee(RegularEmployee e) {
        regularEmployees.remove(e);
    }

    public void distributeBonusBudget() {
        double total = 0;
        for (RegularEmployee regEmp : regularEmployees) {
            total += regEmp.getSalary() * regEmp.getPerformanceScore();
        }
        double unit = bonusBudget / total;
        for (RegularEmployee regEmp : regularEmployees) {
            regEmp.setBonus(unit * regEmp.getSalary() * regEmp.getPerformanceScore());
        }
    }

    @Override
    public String toString() {
        return "Manager [id: " + super.getId() + ", " +
                super.getFirstName() + " " + super.getLastName() + "\n\t\t# of Employees: " + regularEmployees.size() + "]";
    }
}