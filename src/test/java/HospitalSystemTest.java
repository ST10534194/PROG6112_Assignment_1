import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HospitalSystemTest {
    private HospitalSystem system;

    @BeforeEach
    public void setUp() {
        system = new HospitalSystem();
    }

    @Test
    public void testRegisterAndSearchPatient() throws Exception {
        Patient p = new Patient("P01", "John", "Doe", 30, "M", "Flu", PatientCategory.OUTPATIENT);
        system.registerPatient(p);
        assertNotNull(system.searchPatient("P01"));
        assertEquals("John", system.searchPatient("P01").getFirstName());
    }

    @Test
    public void testPreventDuplicatePatientID() throws Exception {
        Patient p1 = new Patient("P01", "John", "Doe", 30, "M", "Flu", PatientCategory.OUTPATIENT);
        Patient p2 = new Patient("P01", "Jane", "Smith", 25, "F", "Cold", PatientCategory.EMERGENCY);
        system.registerPatient(p1);
        Exception exception = assertThrows(Exception.class, () -> system.registerPatient(p2));
        assertEquals("Duplicate ID. Patient already exists.", exception.getMessage());
    }

    @Test
    public void testUpdatePatientDetails() throws Exception {
        Patient p = new Patient("P01", "John", "Doe", 30, "M", "Flu", PatientCategory.OUTPATIENT);
        system.registerPatient(p);
        system.searchPatient("P01").setLastName("Wick");
        assertEquals("Wick", system.searchPatient("P01").getLastName());
    }

    @Test
    public void testDeletePatient() throws Exception {
        Patient p = new Patient("P01", "John", "Doe", 30, "M", "Flu", PatientCategory.OUTPATIENT);
        system.registerPatient(p);
        assertTrue(system.deletePatient("P01"));
        assertNull(system.searchPatient("P01"));
    }

    @Test
    public void testAllocateAndReleaseBed() throws Exception {
        Inpatient p = new Inpatient("I01", "Bruce", "Wayne", 40, "M", "Fracture");
        system.registerPatient(p);
        system.allocateBedToPatient("I01");
        
        assertEquals("B01", p.getBedNumber());
        assertEquals(19, system.getBedManager().getAvailableBedCount());

        assertTrue(system.getBedManager().releaseBed("B01"));
        assertEquals(20, system.getBedManager().getAvailableBedCount());
    }

    @Test
    public void testPreventAllocatingOccupiedBedAndFullWard() throws Exception {
        for (int i = 1; i <= 20; i++) {
            String id = "I" + i;
            system.registerPatient(new Inpatient(id, "Name", "Surname", 30, "M", "Sick"));
            system.allocateBedToPatient(id);
        }
        assertEquals(0, system.getBedManager().getAvailableBedCount());

        system.registerPatient(new Inpatient("I21", "Extra", "Patient", 30, "M", "Sick"));
        Exception exception = assertThrows(Exception.class, () -> system.allocateBedToPatient("I21"));
        assertEquals("Allocation failed: No beds are currently available.", exception.getMessage());
    }

    @Test
    public void testSortPatientsBySurname() throws Exception {
        system.registerPatient(new Patient("P1", "A", "Zebra", 20, "M", "Flu", PatientCategory.OUTPATIENT));
        system.registerPatient(new Patient("P2", "B", "Apple", 20, "F", "Flu", PatientCategory.OUTPATIENT));
        system.sortPatientsBySurname();
        
        assertEquals("Apple", system.getPatients().get(0).getLastName());
        assertEquals("Zebra", system.getPatients().get(1).getLastName());
    }
}
