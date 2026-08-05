package com.summer.section1.group7.eco_resort.Siam;

import java.io.Serializable;
import java.time.LocalDate;

public class TrainingSession implements Serializable {

    private final String guestId;
    private String guestName,phoneNumber,sessionTime,trainer;
    private LocalDate sessionDate;

    public TrainingSession(String guestId, String guestName, String phoneNumber, String sessionTime, String trainer, LocalDate sessionDate) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.phoneNumber = phoneNumber;
        this.sessionTime = sessionTime;
        this.trainer = trainer;
        this.sessionDate = sessionDate;
    }

    public String getGuestId() {
        return guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    @Override
    public String toString() {
        return "TrainingSession{" +
                "guestId='" + guestId + '\'' +
                ", guestName='" + guestName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", sessionTime='" + sessionTime + '\'' +
                ", trainer='" + trainer + '\'' +
                ", sessionDate=" + sessionDate +
                '}';
    }
}