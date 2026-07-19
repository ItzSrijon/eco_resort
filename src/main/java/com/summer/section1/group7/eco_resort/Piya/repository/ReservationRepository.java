package com.summer.section1.group7.eco_resort.Piya.repository;

import com.summer.section1.group7.eco_resort.Piya.model.Reservation;

import java.util.ArrayList;

public class ReservationRepository {

    private ArrayList<Reservation> reservationList;

    public ReservationRepository() {

        reservationList = new ArrayList<>();

    }

    public void addReservation(Reservation reservation) {

        reservationList.add(reservation);

    }

    public ArrayList<Reservation> getReservationList() {

        return reservationList;

    }

}