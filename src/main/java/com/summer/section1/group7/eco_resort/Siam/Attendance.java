package com.summer.section1.group7.eco_resort.Siam;

import java.io.Serializable;
import java.time.LocalDate;

public class Attendance implements Serializable {

    private final String guestId;
    private String guestName, phoneNumber, checkInTime, attendanceStatus;
    private LocalDate attendanceDate;

    public Attendance(String guestId, String guestName, String phoneNumber, String checkInTime, String attendanceStatus, LocalDate attendanceDate) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.phoneNumber = phoneNumber;
        this.checkInTime = checkInTime;
        this.attendanceStatus = attendanceStatus;
        this.attendanceDate = attendanceDate;
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

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "guestId='" + guestId + '\'' +
                ", guestName='" + guestName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", checkInTime='" + checkInTime + '\'' +
                ", attendanceStatus='" + attendanceStatus + '\'' +
                ", attendanceDate=" + attendanceDate +
                '}';
    }
}

