package com.summer.section1.group7.eco_resort.Siam;

import java.io.Serializable;

public class FinancialSummary implements Serializable {

    private double guestPayment;
    private double supplierPayment;
    private double employeeSalary;
    private double totalIncome;
    private double totalExpense;
    private double profit;
    private int totalTransaction;

    public FinancialSummary(double guestPayment,
                            double supplierPayment,
                            double employeeSalary,
                            double totalIncome,
                            double totalExpense,
                            double profit,
                            int totalTransaction) {

        this.guestPayment = guestPayment;
        this.supplierPayment = supplierPayment;
        this.employeeSalary = employeeSalary;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.profit = profit;
        this.totalTransaction = totalTransaction;

    }

    public double getGuestPayment() {
        return guestPayment;
    }

    public double getSupplierPayment() {
        return supplierPayment;
    }

    public double getEmployeeSalary() {
        return employeeSalary;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public double getProfit() {
        return profit;
    }

    public int getTotalTransaction() {
        return totalTransaction;
    }

    @Override
    public String toString() {
        return "FinancialSummary{" +
                "guestPayment=" + guestPayment +
                ", supplierPayment=" + supplierPayment +
                ", employeeSalary=" + employeeSalary +
                ", totalIncome=" + totalIncome +
                ", totalExpense=" + totalExpense +
                ", profit=" + profit +
                ", totalTransaction=" + totalTransaction +
                '}';
    }

}