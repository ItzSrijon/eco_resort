package com.summer.section1.group7.eco_resort.Nazmun.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StaffManager {
    private static final List<StaffMember> staffList = new ArrayList<>();
    private static String binFileName = "chefStaff.bin";

    static {
        loadFromFile();
    }

    public static List<StaffMember> getStaffList() {
        return staffList;
    }

    private static void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(binFileName))) {
            staffList.clear();
            staffList.addAll((ArrayList<StaffMember>) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load staff data from file");
        }
    }

    public static void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(binFileName))) {
            out.writeObject(new ArrayList<>(staffList));
        } catch (IOException e) {
            System.out.println("Could not save staff data to file");
        }
    }

    public static String generateStaffId() {
        int max = 0;
        for (StaffMember s : staffList) {
            int number = Integer.parseInt(s.getStaffId().substring(2));
            if (number > max) max = number;
        }
        return String.format("S-%03d", max + 1);
    }

    public static boolean isAvailable(StaffMember staff) {
        return staff != null && !"Off Duty".equalsIgnoreCase(staff.getStatus());
    }
}