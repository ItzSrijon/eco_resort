package Piya.model;

public class SecurityOfficer extends User {

    private final int employeeId;

    public SecurityOfficer(int userId,
                           String fullName,
                           String phoneNumber,
                           String email,
                           String password,
                           int employeeId) {

        super(userId, fullName, phoneNumber, email, password);
        this.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void viewTodayCheckIns() {

        System.out.println("Displaying today's check-in list.");

    }

    public void recordCheckIn() {

        System.out.println("Guest check-in recorded.");

    }

    public void recordCheckOut() {

        System.out.println("Guest check-out recorded.");

    }

    public void reportIncident() {

        System.out.println("Incident reported successfully.");

    }

    public void viewIncidentHistory() {

        System.out.println("Displaying incident history.");

    }

    public void searchCheckInRecords() {

        System.out.println("Searching check-in records.");

    }

    public void manageLostFound() {

        System.out.println("Managing lost and found items.");

    }

}