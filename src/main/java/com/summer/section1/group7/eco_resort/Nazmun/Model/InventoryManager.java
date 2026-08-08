package com.summer.section1.group7.eco_resort.Nazmun.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private static final List<InventoryItem> inventoryList = new ArrayList<>();
    private static String binFileName = "chefInventory.bin";

    static {
        loadFromFile();
    }

    public static List<InventoryItem> getInventoryList() {
        return inventoryList;
    }

    private static void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(binFileName))) {
            inventoryList.clear();
            inventoryList.addAll((ArrayList<InventoryItem>) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load inventory data from file");
        }
    }

    public static void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(binFileName))) {
            out.writeObject(new ArrayList<>(inventoryList));
        } catch (IOException e) {
            System.out.println("Could not save inventory data to file");
        }
    }

    public static String generateItemId() {
        int max = 0;
        for (InventoryItem item : inventoryList) {
            int number = Integer.parseInt(item.getItemId().substring(2));
            if (number > max) max = number;
        }
        return String.format("I-%03d", max + 1);
    }
}