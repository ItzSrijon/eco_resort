package com.summer.section1.group7.eco_resort.Piya.repository;

import com.summer.section1.group7.eco_resort.Piya.model.Guest;

import java.util.ArrayList;

public class GuestRepository {

    private static ArrayList<Guest> guestList;
    private static Guest currentGuest;

    public GuestRepository() {

        if (guestList == null) {
            guestList = new ArrayList<>();
        }

    }

    public void addGuest(Guest guest) {

        guestList.add(guest);

    }

    public ArrayList<Guest> getGuestList() {

        return guestList;

    }

    public Guest login(String email, String password) {

        for (Guest guest : guestList) {

            if (guest.getEmail().equals(email)
                    && guest.getPassword().equals(password)) {

                currentGuest = guest;
                return guest;

            }

        }

        return null;

    }

    public Guest getCurrentGuest() {

        return currentGuest;

    }

    public void setCurrentGuest(Guest guest) {

        currentGuest = guest;

    }

}