package com.summer.section1.group7.eco_resort.Nazmun.Model;

public class WasteMetric extends EcoMetric {
    public WasteMetric(String metricId, double usage, double target) {
        super(metricId, usage, target);
        metricType = "Waste";
    }

    public boolean isOverTarget() {
        return usage >= target;
    }
}