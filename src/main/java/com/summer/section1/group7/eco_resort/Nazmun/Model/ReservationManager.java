package com.summer.section1.group7.eco_resort.Nazmun.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationManager {
    private static final List<Reservation> reservationList = new ArrayList<>();
    private static String binFileName = "reservations.bin";

    static {
        loadFromFile();
    }

    public static List<Reservation> getReservationList() {
        return reservationList;
    }

    private static void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(binFileName))) {
            reservationList.clear();
            reservationList.addAll((ArrayList<Reservation>) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load reservation data from file");
        }
    }

    public static void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(binFileName))) {
            out.writeObject(new ArrayList<>(reservationList));
        } catch (IOException e) {
            System.out.println("Could not save reservation data to file");
        }
    }

    public static String generateReservationId() {
        int max = 0;
        for (Reservation r : reservationList) {
            int number = Integer.parseInt(r.getReservationId().substring(2));
            if (number > max) max = number;
        }
        return String.format("R-%03d", max + 1);
    }
}