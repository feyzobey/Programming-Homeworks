
/*
 * The purpose of program is implementing a simple company system with the following OOP class hierarchy.
 * Name and Surname: Feyzullah Asıllıoğlu  
 * Student ID: 150121021
 */
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Test {
    private static ArrayList<Person> people = new ArrayList<>();
    private static ArrayList<Department> departments = new ArrayList<>();
    private static ArrayList<Project> projects = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        File file = new File("input.txt");
        FileWriter fileWriter = new FileWriter("output.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");
            switch (line[0]) {
                case "Department": {
                    departments.add(new Department(Integer.parseInt(line[1]), line[2]));
                    break;
                }
                case "Project": {
                    projects.add(new Project(line[1], parseCalender(line[2]), line[3]));
                    break;
                }
                case "Product": {
                    products.add(new Product(line[1], parseCalender(line[2]), Double.parseDouble(line[3])));
                    break;
                }
                case "Person": {
                    people.add(new Person(Integer.parseInt(line[3]), line[1], line[2], line[4], parseCalender(line[5]),
                            line[6], line[7]));
                    break;
                }
                case "Employee": {
                    int personId = Integer.parseInt(line[1]);
                    String departmentName = line[4];
                    Person person = null;
                    Department department = null;
                    for (Person p : people) {
                        if (p.getId() == personId) {
                            person = p;
                            break;
                        }
                    }
                    if (person == null) {
                        throw new Exception("No employee with id = " + personId);
                    }
                    for (Department d : departments) {
                        if (d.getDepartmentName().equals(departmentName)) {
                            department = d;
                            break;
                        }
                    }
                    if (department == null) {
                        throw new Exception("No department with name = " + departmentName);
                    }
                    people.set(people.indexOf(person),
                            new Employee(person, Double.parseDouble(line[2]), parseCalender(line[3]), department));
                    break;
                }
                case "Customer": {
                    Person person = null;
                    int personId = Integer.parseInt(line[1]);
                    for (Person p : people) {
                        if (p.getId() == personId) {
                            person = p;
                            break;
                        }
                    }
                    if (person == null) {
                        throw new Exception("No customer with id = " + line[1]);
                    }
                    ArrayList<Product> listOfProducts = new ArrayList<>();
                    for (int i = 2; i < line.length; i++) {
                        for (Product product : products) {
                            if (line[i].equals(product.getProductName())) {
                                listOfProducts.add(product);
                                break;
                            }
                        }
                    }
                    people.set(people.indexOf(person), new Customer(person, listOfProducts));
                    break;
                }
                case "RegularEmployee": {
                    for (Person person : people) {
                        if (person instanceof Employee && ((Employee) person).getId() == Integer.parseInt(line[1])) {
                            people.set(people.indexOf(((Employee) person)),
                                    new RegularEmployee(((Employee) person), Double.parseDouble(line[2])));
                            break;
                        }
                    }
                    break;
                }
                case "Manager": {
                    for (Person person : people) {
                        if (person instanceof Employee && ((Employee) person).getId() == Integer.parseInt(line[1])) {
                            people.set(people.indexOf((Employee) person),
                                    new Manager(((Employee) person), Double.parseDouble(line[2])));
                            break;
                        }
                    }
                    break;
                }
                case "Developer": {
                    RegularEmployee regularEmployee = null;
                    int regularEmployeeId = Integer.parseInt(line[1]);
                    for (Person person : people) {
                        if (person instanceof RegularEmployee
                                && ((RegularEmployee) person).getId() == regularEmployeeId) {
                            regularEmployee = (RegularEmployee) person;
                            break;
                        }
                    }
                    if (regularEmployee == null) {
                        throw new Exception("No regular developer with id = " + line[1]);
                    }
                    ArrayList<Project> listOfProjects = new ArrayList<>();
                    for (int i = 2; i < line.length; i++) {
                        for (Project project : projects) {
                            if (line[i].equals(project.getProjectName())) {
                                listOfProjects.add(project);
                                break;
                            }
                        }
                    }
                    people.set(people.indexOf(regularEmployee), new Developer(regularEmployee, listOfProjects));
                    break;
                }
                case "SalesEmployee": {
                    RegularEmployee regularEmployee = null;
                    int regularEmployeeId = Integer.parseInt(line[1]);
                    for (Person person : people) {
                        if (person instanceof RegularEmployee
                                && ((RegularEmployee) person).getId() == regularEmployeeId) {
                            regularEmployee = (RegularEmployee) person;
                            break;
                        }
                    }
                    if (regularEmployee == null) {
                        throw new Exception("No sales employee with id = " + line[1]);
                    }
                    ArrayList<Product> listOfProducts = new ArrayList<>();
                    for (int i = 2; i < line.length; i++) {
                        for (Product product : products) {
                            if (line[i].equals(product.getProductName())) {
                                listOfProducts.add(product);
                                break;
                            }
                        }
                    }
                    people.set(people.indexOf(regularEmployee), new SalesEmployee(regularEmployee, listOfProducts));
                    break;
                }
                default:
                    throw new Exception("Object type is not found " + line[0]);
            }
        }
        scanner.close();

        for (Department department : departments) {
            Manager manager = getManagerOfDepartment(department);
            for (Person person : people) {
                if (person instanceof RegularEmployee
                        && ((RegularEmployee) person).getDepartment().equals(department)) {
                    manager.addEmployee(((RegularEmployee) person));
                }
            }
        }
        // instructions
        for (Person person : people) {
            if (person instanceof Manager) {
                ((Manager) person).distributeBonusBudget();
                ((Manager) person).raiseSalary(0.2);
            }
        }
        for (Person person : people) {
            if (person instanceof RegularEmployee
                    && !(person instanceof SalesEmployee || person instanceof Developer)) {
                ((RegularEmployee) person).raiseSalary(0.3);
            }
        }
        for (Person person : people) {
            if (person instanceof Developer) {
                ((Developer) person).raiseSalary(0.23);
            }
        }

        SalesEmployee manOfMonth = null;

        for (Person person : people) {
            if (person instanceof SalesEmployee) {
                ((SalesEmployee) person).raiseSalary(0.18);
                if (manOfMonth == null
                        || ((SalesEmployee) person).getTotalValuesFromSales() > manOfMonth.getTotalValuesFromSales()) {
                    manOfMonth = ((SalesEmployee) person);
                }
            }
        }
        manOfMonth.raiseSalary(1000);

        for (Department department : departments) {
            fileWriter.write("************************************************\n" + department.toString() + "\n");
            for (Person person : people) {
                if (person instanceof Manager) {
                    if (((Manager) person).getDepartment().equals(department)) {
                        fileWriter.write("\t" + ((Manager) person).toString() + "\n");
                        ArrayList<RegularEmployee> regularEmployees = sortEmployees(
                                ((Manager) person).getRegularEmployees());
                        for (int i = 0; i < regularEmployees.size(); i++) {
                            fileWriter.write(
                                    "\t\t\t" + (i + 1) + ". " + regularEmployees.get(i).getClass().getSimpleName()
                                            + "\n\t\t\t\t" + regularEmployees.get(i) + "\n");
                        }
                    }
                }
            }
            fileWriter.write("\n");
        }
        fileWriter.write("\n\n**********************CUSTOMERS************************\n");
        for (Person person : people) {
            if (person instanceof Customer) {
                fileWriter.write(((Customer) person).toString() + "\n");
            }
        }

        fileWriter.write("\n\n**********************PEOPLE************************\n");

        for (Person person : people) {
            if (!(person instanceof Customer || person instanceof Employee)) {
                fileWriter.write(person.toString() + "\n");
            }
        }
        fileWriter.close();
    }
    
    private static Manager getManagerOfDepartment(Department department) {
        for (Person person : people) {
            if (person instanceof Manager && ((Manager) person).getDepartment().equals(department)) {
                return ((Manager) person);
            }
        }
        return null;
    }

    private static Calendar parseCalender(String date) {
        String[] splittedDate = date.split("/");
        int DAY = Integer.parseInt(splittedDate[0]);
        int MONTH = Integer.parseInt(splittedDate[1]) - 1;
        int YEAR = Integer.parseInt(splittedDate[2]);
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR, MONTH, DAY);
        return calendar;
    }

    private static ArrayList<RegularEmployee> sortEmployees(ArrayList<RegularEmployee> regularEmployees) {
        ArrayList<RegularEmployee> reOrganizeRegEmps = new ArrayList<>();
        for (RegularEmployee regEmp : regularEmployees) {
            if (regEmp instanceof Developer) {
                reOrganizeRegEmps.add(regEmp);
            }
        }
        for (RegularEmployee regEmp : regularEmployees) {
            if (regEmp instanceof SalesEmployee) {
                reOrganizeRegEmps.add(regEmp);
            }
        }
        for (RegularEmployee regEmp : regularEmployees) {
            if (!(regEmp instanceof Developer || regEmp instanceof SalesEmployee)) {
                reOrganizeRegEmps.add(regEmp);
            }
        }
        return reOrganizeRegEmps;
    }
}