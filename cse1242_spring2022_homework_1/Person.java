import java.util.Calendar;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private byte gender;
    private Calendar birthDate;
    private byte maritalStatus;
    private boolean hasDriverLicence;

    public Person(int id, String firstName, String lastName, String gender, Calendar birthDate, String maritalStatus,
            String hasDriverLicence) throws Exception {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        setMaritalStatus(maritalStatus);
        setGender(gender);
        setHasDriverLicence(hasDriverLicence);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        switch (gender) {
            case 1:
                return "Woman";
            case 2:
                return "Man";
            default:
                return null;
        }
    }

    public void setGender(String gender) throws Exception {
        switch (gender) {
            case "Woman":
                this.gender = 1;
                return;
            case "Man":
                this.gender = 2;
                return;
            default:
                throw new Exception("Please enter 'Woman' or 'Man'");
        }
    }

    public Calendar getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public String getMaritalStatus() {
        switch (maritalStatus) {
            case 1:
                return "Single";
            case 2:
                return "Married";
            default:
                return null;
        }
    }

    public void setMaritalStatus(String maritalStatus) throws Exception {
        switch (maritalStatus) {
            case "Single":
                this.maritalStatus = 1;
                return;
            case "Married":
                this.maritalStatus = 2;
                return;
            default:
                throw new Exception("Please enter 'Single' or 'Married'");
        }
    }

    public String getHasDriverLicence() {
        return hasDriverLicence ? "Yes" : "No";
    }

    public void setHasDriverLicence(String hasDriverLicence) throws Exception {
        switch (hasDriverLicence) {
            case "Yes":
                this.hasDriverLicence = true;
                return;
            case "No":
                this.hasDriverLicence = false;
                return;
            default:
                throw new Exception("Please enter 'Yes' or 'No'");
        }
    }

    public String personInfo() {
        return "Person Info [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender="
                + getGender() + "]";
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + getGender()
                + ", birthDate=" + getBirthDate().get(Calendar.DAY_OF_MONTH) + "/"
                + (getBirthDate().get(Calendar.MONTH) + 1)
                + "/" + getBirthDate().get(Calendar.YEAR) + ", maritalStatus=" + getMaritalStatus()
                + ", hasDriverLicence="
                + getHasDriverLicence() + "]";
    }
}