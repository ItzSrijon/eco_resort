package Piya.model;

import java.time.LocalDate;

public class CheckInRecord {
    private final int checkInId;
    private String arrivalTime;
    private LocalDate date;

    public CheckInRecord(int checkInId, String arrivalTime, LocalDate date) {
        this.checkInId = checkInId;
        this.arrivalTime = arrivalTime;
        this.date = date;
    }

    public int getCheckInId() {
        return checkInId;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void recordCheckIn() {

        System.out.println("Check-in recorded successfully.");

    }
}
