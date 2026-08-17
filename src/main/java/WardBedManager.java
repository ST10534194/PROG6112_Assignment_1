public class WardBedManager {
    private String[][] beds;
    private String[][] occupiedBy;
    private int totalBeds = 20;

    public WardBedManager() {
        beds = new String[4][5];
        occupiedBy = new String[4][5];
        int counter = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                beds[i][j] = String.format("B%02d", counter++);
            }
        }
    }

    public String allocateBed(String patientId) throws Exception {
        if (getAvailableBedCount() == 0) {
            throw new Exception("Allocation failed: No beds are currently available.");
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (occupiedBy[i][j] == null) {
                    occupiedBy[i][j] = patientId;
                    return beds[i][j];
                }
            }
        }
        return null;
    }

    public boolean releaseBed(String bedId) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (beds[i][j].equals(bedId) && occupiedBy[i][j] != null) {
                    occupiedBy[i][j] = null;
                    return true;
                }
            }
        }
        return false;
    }

    public void displayWardLayout() {
        System.out.println("\n--- Ward Layout (4x5) ---");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                String status = (occupiedBy[i][j] == null) ? "[EMPTY]" : "[" + occupiedBy[i][j] + "]";
                System.out.print(beds[i][j] + status + "\t");
            }
            System.out.println();
        }
    }

    public int getAvailableBedCount() {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (occupiedBy[i][j] == null) count++;
            }
        }
        return count;
    }

    public int getOccupiedBedCount() {
        return totalBeds - getAvailableBedCount();
    }
}
