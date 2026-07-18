package Piya.model;

public class Incident {
    private final int incidentId;
    private String title;
    private String description;
    private String category;
    private String status;

    public Incident(int incidentId, String title, String description, String category, String status) {
        this.incidentId = incidentId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.status = status;
    }

    public int getIncidentId() {
        return incidentId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void reportIncident() {

        System.out.println("Incident reported successfully.");

    }

    public void viewIncidentHistory() {

        System.out.println("Displaying incident history.");

    }
}
