package com.summer.section1.group7.eco_resort.Siam;

import java.io.Serializable;

public class Supplier implements Serializable {

    private String supplierId;
    private String supplierName;
    private String companyName;
    private String phoneNumber;
    private String email;

    public Supplier(String supplierId,
                    String supplierName,
                    String companyName,
                    String phoneNumber,
                    String email) {

        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId='" + supplierId + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}