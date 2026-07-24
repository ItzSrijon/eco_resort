package com.summer.section1.group7.eco_resort.Nazmun.Model;

public class Staff {
    private final javafx.beans.property.SimpleStringProperty staffId;
    private final javafx.beans.property.SimpleStringProperty staffName;
    private final javafx.beans.property.SimpleStringProperty role;
    private final javafx.beans.property.SimpleStringProperty shift;
    private final javafx.beans.property.SimpleStringProperty status;

    public Staff(String staffId, String staffName, String role, String shift, String status) {
        this.staffId = new javafx.beans.property.SimpleStringProperty(staffId);
        this.staffName = new javafx.beans.property.SimpleStringProperty(staffName);
        this.role = new javafx.beans.property.SimpleStringProperty(role);
        this.shift = new javafx.beans.property.SimpleStringProperty(shift);
        this.status = new javafx.beans.property.SimpleStringProperty(status);
    }

    public String getStaffId() { return staffId.get(); }
    public String getStaffName() { return staffName.get(); }
    public String getRole() { return role.get(); }
    public String getShift() { return shift.get(); }
    public String getStatus() { return status.get(); }

    public void setStatus(String status) { this.status.set(status); }

    public javafx.beans.property.SimpleStringProperty staffIdProperty() { return staffId; }
    public javafx.beans.property.SimpleStringProperty staffNameProperty() { return staffName; }
    public javafx.beans.property.SimpleStringProperty roleProperty() { return role; }
    public javafx.beans.property.SimpleStringProperty shiftProperty() { return shift; }
    public javafx.beans.property.SimpleStringProperty statusProperty() { return status; }
}