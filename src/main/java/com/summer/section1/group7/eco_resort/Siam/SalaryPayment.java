package com.summer.section1.group7.eco_resort.Siam;

import java.io.Serializable;
import java.time.LocalDate;

public class SalaryPayment implements Serializable {

    private String userId;
    private String employeeName;
    private double salary;
    private LocalDate paymentDate;

    public SalaryPayment(String userId, String employeeName, double salary, LocalDate paymentDate) {
        this.userId = userId;
        this.employeeName = employeeName;
        this.salary = salary;
        this.paymentDate = paymentDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "SalaryPayment{" +
                "userId='" + userId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", salary=" + salary +
                ", paymentDate=" + paymentDate +
                '}';
    }
}