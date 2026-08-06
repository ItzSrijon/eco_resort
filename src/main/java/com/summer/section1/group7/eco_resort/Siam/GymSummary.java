package com.summer.section1.group7.eco_resort.Siam;

public class GymSummary {

    private String summaryItem;
    private String value;

    public GymSummary(String summaryItem, String value) {
        this.summaryItem = summaryItem;
        this.value = value;
    }

    public String getSummaryItem() {
        return summaryItem;
    }

    public void setSummaryItem(String summaryItem) {
        this.summaryItem = summaryItem;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "GymSummary{" +
                "summaryItem='" + summaryItem + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}