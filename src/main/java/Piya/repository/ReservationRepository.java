package Piya.repository;

import Piya.model.Reservation;

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