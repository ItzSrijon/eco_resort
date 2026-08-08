package com.summer.section1.group7.eco_resort.Nazmun.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MenuManager {
    private static final List<MenuItem> menuList = new ArrayList<>();
    private static String binFileName = "chefMenu.bin";

    static {
        loadFromFile();
    }

    public static List<MenuItem> getMenuList() {
        return menuList;
    }

    private static void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(binFileName))) {
            menuList.clear();
            menuList.addAll((ArrayList<MenuItem>) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load menu data from file");
        }
    }

    public static void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(binFileName))) {
            out.writeObject(new ArrayList<>(menuList));
        } catch (IOException e) {
            System.out.println("Could not save menu data to file");
        }
    }

    public static String generateItemId() {
        int max = 0;
        for (MenuItem item : menuList) {
            int number = Integer.parseInt(item.getItemId().substring(2));
            if (number > max) max = number;
        }
        return String.format("M-%03d", max + 1);
    }
}