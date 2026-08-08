package com.summer.section1.group7.eco_resort.Nazmun.Model;

import java.io.Serializable;

public abstract class EcoMetric implements Serializable {
    protected String metricId;
    protected String metricType = "Metric";
    protected double usage;
    protected double target;

    public abstract boolean isOverTarget();

    public EcoMetric(String metricId, double usage, double target) {
        this.metricId = metricId;
        this.usage = usage;
        this.target = target;
    }

    public String getMetricId() {
        return metricId;
    }

    public String getMetricType() {
        return metricType;
    }

    public double getUsage() {
        return usage;
    }

    public double getTarget() {
        return target;
    }

    public String getStatus() {
        return isOverTarget() ? "Over Target" : "OK";
    }

    @Override
    public String toString() {
        return "A " + metricType + " metric with usage: " + usage;
    }
}