import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class HospitalSystem {
    private ArrayList<Patient> patients = new ArrayList<>();
    private WardBedManager bedManager = new WardBedManager();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new HospitalSystem().runMenu();
    }

    public void runMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== MediCare Patient Admission System ===");
            System.out.println("1. Register Patient");
            System.out.println("2. Search Patient");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Ward Bed Management");
            System.out.println("6. Reports");
            System.out.println("7. Sort Patients (by Surname)");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");
            
            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "1": registerPatientUI(); break;
                    case "2": searchPatientUI(); break;
                    case "3": updatePatientUI(); break;
                    case "4": deletePatientUI(); break;
                    case "5": bedManagementUI(); break;
                    case "6": generateReports(); break;
                    case "7": sortPatientsBySurname(); break;
                    case "8": running = false; break;
                    default: System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public void registerPatient(Patient p) throws Exception {
        if (searchPatient(p.getPatientId()) != null) {
            throw new Exception("Duplicate ID. Patient already exists.");
        }
        patients.add(p);
    }

    public Patient searchPatient(String id) {
        for (Patient p : patients) {
            if (p.getPatientId().equalsIgnoreCase(id)) return p;
        }
        return null;
    }

    public boolean deletePatient(String id) {
        Patient p = searchPatient(id);
        if (p != null) {
            if (p instanceof Inpatient) {
                String bed = ((Inpatient) p).getBedNumber();
                if (!bed.equals("Unassigned")) {
                    bedManager.releaseBed(bed);
                }
            }
            patients.remove(p);
            return true;
        }
        return false;
    }

    public void allocateBedToPatient(String patientId) throws Exception {
        Patient p = searchPatient(patientId);
        if (p == null) throw new Exception("Patient not found.");
        if (!(p instanceof Inpatient)) throw new Exception("Only Inpatients require beds.");
        
        Inpatient inpatient = (Inpatient) p;
        if (!inpatient.getBedNumber().equals("Unassigned")) {
            throw new Exception("Patient already has an allocated bed.");
        }

        String assignedBed = bedManager.allocateBed(patientId);
        if (assignedBed != null) {
            inpatient.setBedNumber(assignedBed);
        }
    }

    private void registerPatientUI() throws Exception {
        System.out.print("Enter ID: "); String id = scanner.nextLine();
        System.out.print("First Name: "); String fn = scanner.nextLine();
        System.out.print("Last Name: "); String ln = scanner.nextLine();
        System.out.print("Age: "); int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Gender: "); String gender = scanner.nextLine();
        System.out.print("Condition: "); String condition = scanner.nextLine();
        System.out.print("Category (1-Inpatient, 2-Outpatient, 3-Emergency): ");
        String catOpt = scanner.nextLine();

        Patient newPatient;
        if (catOpt.equals("1")) {
            newPatient = new Inpatient(id, fn, ln, age, gender, condition);
        } else {
            PatientCategory cat = catOpt.equals("2") ? PatientCategory.OUTPATIENT : PatientCategory.EMERGENCY;
            newPatient = new Patient(id, fn, ln, age, gender, condition, cat);
        }
        registerPatient(newPatient);
        System.out.println("Patient registered successfully.");
    }

    private void searchPatientUI() {
        System.out.print("Enter ID to search: ");
        Patient p = searchPatient(scanner.nextLine());
        if (p != null) p.displayDetails();
        else System.out.println("Patient not found.");
    }

    private void updatePatientUI() {
        System.out.print("Enter ID to update: ");
        Patient p = searchPatient(scanner.nextLine());
        if (p != null) {
            System.out.print("New First Name: "); p.setFirstName(scanner.nextLine());
            System.out.print("New Last Name: "); p.setLastName(scanner.nextLine());
            System.out.println("Details updated.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    private void deletePatientUI() {
        System.out.print("Enter ID to delete: ");
        if (deletePatient(scanner.nextLine())) System.out.println("Deleted.");
        else System.out.println("Patient not found.");
    }

    private void bedManagementUI() {
        System.out.println("1. Allocate Bed\n2. Release Bed\n3. View Layout");
        String opt = scanner.nextLine();
        try {
            if (opt.equals("1")) {
                System.out.print("Enter Inpatient ID: ");
                allocateBedToPatient(scanner.nextLine());
                System.out.println("Bed allocated successfully.");
            } else if (opt.equals("2")) {
                System.out.print("Enter Bed ID to release (e.g. B01): ");
                if (bedManager.releaseBed(scanner.nextLine())) System.out.println("Bed released.");
                else System.out.println("Bed not found or already empty.");
            } else if (opt.equals("3")) {
                bedManager.displayWardLayout();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void generateReports() {
        System.out.println("\n--- Ward Reports ---");
        System.out.println("Total Registered Patients: " + patients.size());
        System.out.println("Total Available Beds: " + bedManager.getAvailableBedCount());
        System.out.println("Total Occupied Beds: " + bedManager.getOccupiedBedCount());
        
        double occupancy = ((double) bedManager.getOccupiedBedCount() / 20.0) * 100;
        System.out.println("Ward Occupancy Percentage: " + occupancy + "%");
        
        System.out.println("\nAll Registered Patients:");
        for (Patient p : patients) {
            p.displayDetails();
        }
    }

    public void sortPatientsBySurname() {
        Collections.sort(patients, Comparator.comparing(Patient::getLastName));
        System.out.println("Patients sorted by surname.");
    }

    public ArrayList<Patient> getPatients() { return patients; }
    public WardBedManager getBedManager() { return bedManager; }
}
