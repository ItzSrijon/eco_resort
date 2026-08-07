package com.summer.section1.group7.eco_resort.Piya.model;

import com.summer.section1.group7.eco_resort.User;

import java.io.Serializable;
import java.time.LocalDate;

public class SecurityOfficer extends User implements Serializable {

    public SecurityOfficer(String userId,
                           String username,
                           String name,
                           String phoneNumber,
                           String email,
                           String gender,
                           String password,
                           LocalDate dob) {

        super(
                userId,
                username,
                name,
                phoneNumber,
                email,
                gender,
                password,
                "Security Officer",
                dob,
                "Active"
        );

    }

    @Override
    public String toString() {

        return "SecurityOfficer{" +
                "userId='" + getUserId() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", name='" + getName() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", gender='" + getGender() + '\'' +
                ", dob=" + getDob() +
                '}';

    }

}