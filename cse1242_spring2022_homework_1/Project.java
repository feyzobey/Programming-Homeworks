import java.util.Calendar;

public class Project {
    private String projectName;
    private Calendar startDate;
    private boolean state;

    public Project(String projectName, Calendar startDate, String state) throws Exception {
        this.projectName = projectName;
        this.startDate = startDate;
        setState(state);
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setState(String state) throws Exception {
        switch (state) {
            case "Open":
                this.state = true;
                return;
            case "Close":
                this.state = false;
                return;
            default:
                throw new Exception("Please enter 'Open' or 'Close'");
        }
    }

    public String getState() {
        return state ? "Open" : "Close";
    }

    public void close() {
        if (state) {
            state = false;
        }
    }

    @Override
    public String toString() {
        return "Project [projectName=" + projectName + ", startDate=" + startDate.get(Calendar.DAY_OF_MONTH) + "/"
                + (startDate.get(Calendar.MONTH) + 1) + "/"
                + startDate.get(Calendar.YEAR) + ", state=" + state + "]";
    }
}
