package com.summer.section1.group7.eco_resort.Srijon.model;

import java.io.Serial;
import java.io.Serializable;

public class MaintenanceSummary implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int totalRequests;
    private int completedRequests;
    private int pendingRequests;

    public MaintenanceSummary(int totalRequests, int completedRequests, int pendingRequests) {
        this.totalRequests = totalRequests;
        this.completedRequests = completedRequests;
        this.pendingRequests = pendingRequests;
    }

    public int getTotalRequests() { return totalRequests; }
    public void setTotalRequests(int totalRequests) { this.totalRequests = totalRequests; }

    public int getCompletedRequests() { return completedRequests; }
    public void setCompletedRequests(int completedRequests) { this.completedRequests = completedRequests; }

    public int getPendingRequests() { return pendingRequests; }
    public void setPendingRequests(int pendingRequests) { this.pendingRequests = pendingRequests; }

    @Override
    public String toString() {
        return "MaintenanceSummary{" +
                "totalRequests=" + totalRequests +
                ", completedRequests=" + completedRequests +
                ", pendingRequests=" + pendingRequests +
                '}';
    }
}
