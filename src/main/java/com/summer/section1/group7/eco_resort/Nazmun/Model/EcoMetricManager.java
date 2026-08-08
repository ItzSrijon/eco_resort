package com.summer.section1.group7.eco_resort.Nazmun.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EcoMetricManager {
    private static final List<EcoMetric> metricList = new ArrayList<>();
    private static String binFileName = "ecoMetrics.bin";

    static {
        loadFromFile();
    }

    public static List<EcoMetric> getMetricList() {
        return metricList;
    }

    private static void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(binFileName))) {
            metricList.clear();
            metricList.addAll((ArrayList<EcoMetric>) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load eco metric data from file");
        }
    }

    public static void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(binFileName))) {
            out.writeObject(new ArrayList<>(metricList));
        } catch (IOException e) {
            System.out.println("Could not save eco metric data to file");
        }
    }

    public static String generateMetricId() {
        int max = 0;
        for (EcoMetric m : metricList) {
            int number = Integer.parseInt(m.getMetricId().substring(2));
            if (number > max) max = number;
        }
        return String.format("E-%03d", max + 1);
    }
}