package com.summer.section1.group7.eco_resort.Siam;
import java.io.Serializable;
import java.time.LocalDate;

public class GymMember implements Serializable {

    private String guestId;
    private String guestName;
    private String phoneNumber;
    private String email;

    private String packageName;
    private int duration;
    private double totalFee;

    private String status;
    private LocalDate registrationDate;

    public GymMember(String guestId,
                     String guestName,
                     String phoneNumber,
                     String email,
                     String packageName,
                     int duration,
                     String status,
                     LocalDate registrationDate) {

        this.guestId = guestId;
        this.guestName = guestName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.packageName = packageName;
        this.duration = duration;
        this.status = status;
        this.registrationDate = registrationDate;

        calculateTotalFee();
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
        calculateTotalFee();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
        calculateTotalFee();
    }

    public double getTotalFee() {
        return totalFee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "GymMember{" +
                "guestId='" + guestId + '\'' +
                ", guestName='" + guestName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", packageName='" + packageName + '\'' +
                ", duration=" + duration +
                ", totalFee=" + totalFee +
                ", status='" + status + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
    private void calculateTotalFee() {

        double feePerDay = 0;

        switch (packageName) {

            case "Basic":
                feePerDay = 300;
                break;

            case "Premium":
                feePerDay = 500;
                break;

            case "VIP":
                feePerDay = 700;
                break;
        }

        totalFee = feePerDay * duration;
    }
}