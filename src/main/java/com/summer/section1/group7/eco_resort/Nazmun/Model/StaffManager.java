package com.summer.section1.group7.eco_resort.Nazmun.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StaffManager {

    private static final List<StaffMember> staffList = new ArrayList<>();

    static {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("chefStaff.bin"))) {

            while (true) {
                try {
                    StaffMember staff = (StaffMember) ois.readObject();
                    staffList.add(staff);
                } catch (EOFException e) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous kitchen staff found.");
        }
    }

    // Add a new staff member and persist
    public static void addStaff(StaffMember staff) {
        staffList.add(staff);
        saveStaffToFile();
    }

    // Save all staff to chefStaff.bin
    public static void saveStaffToFile() {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream("chefStaff.bin"))) {

            for (StaffMember staff : staffList) {
                oos.writeObject(staff);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Auto Generate Staff ID, e.g. S-001
    public static String generateStaffId() {

        int max = 0;

        for (StaffMember staff : staffList) {

            String id = staff.getStaffId().substring(2);   // S-001 -> 001
            int number = Integer.parseInt(id);

            if (number > max) {
                max = number;
            }
        }

        return String.format("S-%03d", max + 1);
    }

    // event-6: verify selected staff member is on shift and available
    public static boolean isAvailable(StaffMember staff) {
        return staff != null && !"Off Duty".equalsIgnoreCase(staff.getStatus());
    }

    public static List<StaffMember> getStaffList() {
        return staffList;
    }
}