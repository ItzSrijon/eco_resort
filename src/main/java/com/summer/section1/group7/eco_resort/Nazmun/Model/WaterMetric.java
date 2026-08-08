package com.summer.section1.group7.eco_resort.Nazmun.Model;

public class WaterMetric extends EcoMetric {

    public WaterMetric(String metricId, double usage, double target) {
        super(metricId, usage, target);
        metricType = "Water";
    }

    public boolean isOverTarget() {
        // water usage fluctuates day to day, allow 10% buffer before flagging
        return usage > target * 1.10;
    }
}