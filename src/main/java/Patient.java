public class Patient {
    private String patientId;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String medicalCondition;
    private PatientCategory category;

    public Patient(String patientId, String firstName, String lastName, int age, String gender, String medicalCondition, PatientCategory category) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.medicalCondition = medicalCondition;
        this.category = category;
    }

    public String getPatientId() { return patientId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public PatientCategory getCategory() { return category; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setAge(int age) { this.age = age; }
    public void setMedicalCondition(String medicalCondition) { this.medicalCondition = medicalCondition; }

    public void displayDetails() {
        System.out.println("ID: " + patientId + " | Name: " + firstName + " " + lastName + 
                           " | Age: " + age + " | Gender: " + gender + 
                           " | Condition: " + medicalCondition + " | Category: " + category);
    }
}
