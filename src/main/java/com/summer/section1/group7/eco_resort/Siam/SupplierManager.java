package com.summer.section1.group7.eco_resort.Siam;

import java.io.*;

public class SupplierManager {

    public static Supplier findSupplier(String supplierId) {

        File file = new File("supplier.bin");

        if (!file.exists()) {
            return null;
        }

        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

            while (true) {

                try {
                    Supplier supplier = (Supplier) ois.readObject();
                    if (supplier.getSupplierId().equalsIgnoreCase(supplierId.trim())) {

                        ois.close();
                        return supplier;

                    }

                }

                catch (EOFException e) {

                    break;

                }

            }

            ois.close();

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }
    public static String generateSupplierId() {

        File file = new File("supplier.bin");

        if (!file.exists()) {
            return "S-001";
        }

        int max = 0;

        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

            while (true) {

                try {
                    Supplier s = (Supplier) ois.readObject();

                    int id = Integer.parseInt(s.getSupplierId().substring(2));

                    if (id > max) {
                        max = id;
                    }

                } catch (EOFException e) {
                    break;
                }

            }
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return String.format("S-%03d", max + 1);
    }

}