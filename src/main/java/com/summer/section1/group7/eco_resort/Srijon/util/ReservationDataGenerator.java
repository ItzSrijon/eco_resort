package com.summer.section1.group7.eco_resort.Srijon.util;

import com.summer.section1.group7.eco_resort.Srijon.model.Reservation;
import com.summer.section1.group7.eco_resort.Srijon.util.BinaryFileManager;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDataGenerator {
    public static void main(String[] args) {
        ArrayList<Reservation> list = new ArrayList<>();

        // sample reservations
        list.add(new Reservation("R001", "guest001", "Single",
                LocalDate.of(2026, 8, 10), LocalDate.of(2026, 8, 12), "Active"));

        list.add(new Reservation("R002", "guest002", "Double",
                LocalDate.of(2026, 8, 15), LocalDate.of(2026, 8, 18), "Active"));

        list.add(new Reservation("R003", "guest003", "VIP",
                LocalDate.of(2026, 9, 1), LocalDate.of(2026, 9, 5), "Active"));

        try {
            BinaryFileManager.saveList("reservation.bin", list);
            System.out.println("reservation.bin created with " + list.size() + " entries in data/ folder.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to save reservation.bin: " + e.getMessage());
        }
    }
}
