import java.util.Calendar;

public class Employee extends Person {
    private double salary;
    private Calendar hireDate;
    private Department department;
    public static int numberOfEmployees;

    public Employee(int id, String firstName, String lastName, String gender, Calendar birthDate, String maritalStatus,
            String hasDriverLicence, double salary, Calendar hireDate, Department department) throws Exception {
        super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence);

        this.salary = salary;
        this.hireDate = hireDate;
        this.department = department;
        numberOfEmployees++;
    }

    public Employee(Person person, double salary, Calendar hireDate, Department department) throws Exception {
        this(person.getId(), person.getFirstName(), person.getLastName(), person.getGender(), person.getBirthDate(),
                person.getMaritalStatus(), person.getHasDriverLicence(), salary, hireDate, department);
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = Math.round((100 * salary)) / 100.0;
    }

    public Calendar getHireDate() {
        return this.hireDate;
    }

    public void setHireDate(Calendar hireDate) {
        this.hireDate = hireDate;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public double raiseSalary(double percent) throws Exception {
        if (percent > 1 || percent < 0) {
            throw new Exception("You must enter value of percentage between 0 and 1");
        }

        return salary += salary * percent;
    }

    public double raiseSalary(int amount) throws Exception {
        if (amount < 0) {
            throw new Exception("You must enter value of amount over 0");
        }

        return salary += amount;
    }

    @Override
    public String toString() {
        return super.personInfo() + "\n\t\t\t\tEmployee Info [salary="
                + salary + ", hireDate=" + hireDate.get(Calendar.DAY_OF_MONTH) + "/"
                + (hireDate.get(Calendar.MONTH) + 1) + "/" + hireDate.get(Calendar.YEAR) + "]";
    }
}
