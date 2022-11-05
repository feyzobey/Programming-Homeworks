import java.util.ArrayList;
import java.util.Calendar;

public class Developer extends RegularEmployee {
    private ArrayList<Project> projects;
    public static int numberOfDevelopers;

    public Developer(int id, String firstName, String lastName, String gender, Calendar birthDate, String maritalStatus,
            String hasDriverLicence, double salary, Calendar hireDate, Department department, double pScore,
            ArrayList<Project> p) throws Exception {
        super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence, salary, hireDate, department,
                pScore);

        projects = p;
        numberOfDevelopers++;
    }

    public Developer(RegularEmployee re, ArrayList<Project> p) throws Exception {
        this(re.getId(), re.getFirstName(), re.getLastName(), re.getGender(), re.getBirthDate(), re.getMaritalStatus(),
                re.getHasDriverLicence(), re.getSalary(), re.getHireDate(), re.getDepartment(),
                re.getPerformanceScore(), p);
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public boolean addProject(Project s) throws Exception {
        if (!projects.contains(s)) {
            return projects.add(s);
        }
        throw new Exception("This product exists");
    }

    public boolean removeProject(Project s) throws Exception {
        if (projects.contains(s)) {
            return projects.remove(s);
        }
        throw new Exception("This product does not exist");
    }

    @Override
    public String toString() {
        return super.toString() + "\n\t\t\t\t" + projects;
    }
}