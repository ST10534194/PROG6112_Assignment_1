public class Inpatient extends Patient {
    private String wardNumber;
    private String bedNumber;

    public Inpatient(String patientId, String firstName, String lastName, int age, String gender, String medicalCondition) {
        super(patientId, firstName, lastName, age, gender, medicalCondition, PatientCategory.INPATIENT);
        this.wardNumber = "Ward 1";
        this.bedNumber = "Unassigned";
    }

    public String getBedNumber() { return bedNumber; }
    public void setBedNumber(String bedNumber) { this.bedNumber = bedNumber; }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("   -> Ward: " + wardNumber + " | Bed: " + bedNumber);
    }
}
