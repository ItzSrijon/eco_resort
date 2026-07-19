package com.summer.section1.group7.eco_resort.Nazmun.Model;

import java.time.LocalDate;

public class Reservation {
    private final javafx.beans.property.SimpleStringProperty guestName;
    private final javafx.beans.property.SimpleStringProperty contact;
    private final javafx.beans.property.SimpleStringProperty roomType;
    private final javafx.beans.property.SimpleObjectProperty<LocalDate> checkIn;
    private final javafx.beans.property.SimpleObjectProperty<LocalDate> checkOut;
    private final javafx.beans.property.SimpleStringProperty status;
    private final javafx.beans.property.SimpleDoubleProperty totalBill;

    public Reservation(String guestName, String contact, String roomType,
                       LocalDate checkIn, LocalDate checkOut, String status, double totalBill) {
        this.guestName = new javafx.beans.property.SimpleStringProperty(guestName);
        this.contact = new javafx.beans.property.SimpleStringProperty(contact);
        this.roomType = new javafx.beans.property.SimpleStringProperty(roomType);
        this.checkIn = new javafx.beans.property.SimpleObjectProperty<>(checkIn);
        this.checkOut = new javafx.beans.property.SimpleObjectProperty<>(checkOut);
        this.status = new javafx.beans.property.SimpleStringProperty(status);
        this.totalBill = new javafx.beans.property.SimpleDoubleProperty(totalBill);
    }

    public String getGuestName() { return guestName.get(); }
    public String getContact() { return contact.get(); }
    public String getRoomType() { return roomType.get(); }
    public LocalDate getCheckIn() { return checkIn.get(); }
    public LocalDate getCheckOut() { return checkOut.get(); }
    public String getStatus() { return status.get(); }
    public double getTotalBill() { return totalBill.get(); }

    public void setStatus(String status) { this.status.set(status); }
    public void setTotalBill(double bill) { this.totalBill.set(bill); }

    public javafx.beans.property.SimpleStringProperty guestNameProperty() { return guestName; }
    public javafx.beans.property.SimpleStringProperty contactProperty() { return contact; }
    public javafx.beans.property.SimpleStringProperty roomTypeProperty() { return roomType; }
    public javafx.beans.property.SimpleObjectProperty<LocalDate> checkInProperty() { return checkIn; }
    public javafx.beans.property.SimpleObjectProperty<LocalDate> checkOutProperty() { return checkOut; }
    public javafx.beans.property.SimpleStringProperty statusProperty() { return status; }
    public javafx.beans.property.SimpleDoubleProperty totalBillProperty() { return totalBill; }
}