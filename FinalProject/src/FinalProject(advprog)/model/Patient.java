package FinalProject.advprog.model;

public class Patient {

    private int patientID;
    private String lastName;
    private String previousLastName;
    private String firstName;
    private String homeAddress1;
    private String homeCity;
    private String homeStateProvinceRegion;
    private String homeZipPostalCode;
    private String homePhone;
    private String dateOfBirth;
    private String gender;

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPreviousLastName() {
        return previousLastName;
    }

    public void setPreviousLastName(String previousLastName) {
        this.previousLastName = previousLastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getHomeAddress1() {
        return homeAddress1;
    }

    public void setHomeAddress1(String homeAddress1) {
        this.homeAddress1 = homeAddress1;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    public String getHomeStateProvinceRegion() {
        return homeStateProvinceRegion;
    }

    public void setHomeStateProvinceRegion(String homeStateProvinceRegion) {
        this.homeStateProvinceRegion = homeStateProvinceRegion;
    }

    public String getHomeZipPostalCode() {
        return homeZipPostalCode;
    }

    public void setHomeZipPostalCode(String homeZipPostalCode) {
        this.homeZipPostalCode = homeZipPostalCode;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

