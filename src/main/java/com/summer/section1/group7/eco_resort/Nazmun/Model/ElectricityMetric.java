package com.summer.section1.group7.eco_resort.Nazmun.Model;

public class ElectricityMetric extends EcoMetric {

    public boolean isOverTarget() {
        return usage > target;
    }

    public ElectricityMetric(String metricId, double usage, double target) {
        super(metricId, usage, target);
        metricType = "Electricity";
    }
}