package com.summer.section1.group7.eco_resort.Nazmun.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SafetyInspectionManager {
    private static final List<SafetyInspectionItem> inspectionList = new ArrayList<>();
    private static String binFileName = "safetyInspection.bin";

    static {
        loadFromFile();
    }

    public static List<SafetyInspectionItem> getInspectionList() {
        return inspectionList;
    }

    private static void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(binFileName))) {
            inspectionList.clear();
            inspectionList.addAll((ArrayList<SafetyInspectionItem>) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load safety inspection data from file");
        }
    }

    public static void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(binFileName))) {
            out.writeObject(new ArrayList<>(inspectionList));
        } catch (IOException e) {
            System.out.println("Could not save safety inspection data to file");
        }
    }

    public static String generateAreaId() {
        int max = 0;
        for (SafetyInspectionItem item : inspectionList) {
            int number = Integer.parseInt(item.getAreaId().substring(2));
            if (number > max) max = number;
        }
        return String.format("A-%03d", max + 1);
    }
}