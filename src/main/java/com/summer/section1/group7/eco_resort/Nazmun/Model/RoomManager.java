package com.summer.section1.group7.eco_resort.Nazmun.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RoomManager {
    private static final List<Room> roomList = new ArrayList<>();
    private static String binFileName = "rooms.bin";

    static {
        loadFromFile();
    }

    public static List<Room> getRoomList() {
        return roomList;
    }

    private static void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(binFileName))) {
            roomList.clear();
            roomList.addAll((ArrayList<Room>) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load room data from file");
        }
    }

    public static void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(binFileName))) {
            out.writeObject(new ArrayList<>(roomList));
        } catch (IOException e) {
            System.out.println("Could not save room data to file");
        }
    }

    public static String generateRoomId() {
        int max = 0;
        for (Room r : roomList) {
            int number = Integer.parseInt(r.getRoomId().substring(2));
            if (number > max) max = number;
        }
        return String.format("R-%03d", max + 1);
    }
}