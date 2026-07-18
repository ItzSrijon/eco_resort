package Piya.model;

import java.time.LocalDate;

public class CheckOutRecord {
    private final int checkOutId;
    private String departureTime;
    private LocalDate date;

    public CheckOutRecord(int checkOutId, String departureTime, LocalDate date) {
        this.checkOutId = checkOutId;
        this.departureTime = departureTime;
        this.date = date;
    }

    public int getCheckOutId() {
        return checkOutId;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void recordCheckOut() {

        System.out.println("Check-out recorded successfully.");

    }
}
