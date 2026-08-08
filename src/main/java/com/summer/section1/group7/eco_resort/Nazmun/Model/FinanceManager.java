package com.summer.section1.group7.eco_resort.Nazmun.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FinanceManager {
    private static final List<FinanceEntry> entryList = new ArrayList<>();
    private static String binFileName = "financeEntries.bin";

    static {
        loadFromFile();
    }

    public static List<FinanceEntry> getEntryList() {
        return entryList;
    }

    private static void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(binFileName))) {
            entryList.clear();
            entryList.addAll((ArrayList<FinanceEntry>) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load finance data from file");
        }
    }

    public static void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(binFileName))) {
            out.writeObject(new ArrayList<>(entryList));
        } catch (IOException e) {
            System.out.println("Could not save finance data to file");
        }
    }
}