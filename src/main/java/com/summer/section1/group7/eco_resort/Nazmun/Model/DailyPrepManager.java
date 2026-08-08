package com.summer.section1.group7.eco_resort.Nazmun.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DailyPrepManager {
    private static final List<DailyPrepItem> prepList = new ArrayList<>();
    private static String binFileName = "chefDailyPrep.bin";

    static {
        loadFromFile();
    }

    public static List<DailyPrepItem> getPrepList() {
        return prepList;
    }

    private static void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(binFileName))) {
            prepList.clear();
            prepList.addAll((ArrayList<DailyPrepItem>) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load daily prep data from file");
        }
    }

    public static void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(binFileName))) {
            out.writeObject(new ArrayList<>(prepList));
        } catch (IOException e) {
            System.out.println("Could not save daily prep data to file");
        }
    }

    public static String generateDishId() {
        int max = 0;
        for (DailyPrepItem item : prepList) {
            int number = Integer.parseInt(item.getDishId().substring(2));
            if (number > max) max = number;
        }
        return String.format("D-%03d", max + 1);
    }
}