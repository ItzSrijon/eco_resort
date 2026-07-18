package Piya.repository;

import Piya.model.Guest;

import java.util.ArrayList;

public class GuestRepository {

    private static ArrayList<Guest> guestList;

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

                return guest;

            }

        }

        return null;

    }

}