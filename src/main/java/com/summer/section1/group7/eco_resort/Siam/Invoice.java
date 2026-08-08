package com.summer.section1.group7.eco_resort.Siam;

import java.io.Serializable;

public class Invoice implements Serializable {

    private String guestId;
    private String guestName;
    private double totalBill;
    private double totalPaid;
    private double duePayment;

    public Invoice(String guestId,
                   String guestName,
                   double totalBill,
                   double totalPaid,
                   double duePayment) {

        this.guestId = guestId;
        this.guestName = guestName;
        this.totalBill = totalBill;
        this.totalPaid = totalPaid;
        this.duePayment = duePayment;
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

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public double getDuePayment() {
        return duePayment;
    }

    public void setDuePayment(double duePayment) {
        this.duePayment = duePayment;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "guestId='" + guestId + '\'' +
                ", guestName='" + guestName + '\'' +
                ", totalBill=" + totalBill +
                ", totalPaid=" + totalPaid +
                ", duePayment=" + duePayment +
                '}';
    }
}