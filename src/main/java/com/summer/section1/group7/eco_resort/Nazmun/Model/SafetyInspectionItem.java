package com.summer.section1.group7.eco_resort.Nazmun.Model;

import java.io.Serializable;
import java.time.LocalDate;

public class SafetyInspectionItem implements Serializable {
    private String areaId, areaName, status = "Pending";
    private LocalDate deadline;

    public SafetyInspectionItem(String areaId, String areaName) {
        this.areaId = areaId;
        this.areaName = areaName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "SafetyInspectionItem{" +
                "areaName='" + areaName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}