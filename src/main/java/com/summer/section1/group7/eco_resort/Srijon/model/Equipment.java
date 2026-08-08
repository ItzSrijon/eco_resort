package com.summer.section1.group7.eco_resort.Srijon.model;

import java.io.Serial;
import java.io.Serializable;

public class Equipment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String equipmentId;
    private String name;
    private String condition; // Good, Needs Repair, Out of Service

    public Equipment(String equipmentId, String name, String condition) {
        this.equipmentId = equipmentId;
        this.name = name;
        this.condition = condition;
    }

    public String getEquipmentId() { return equipmentId; }
    public void setEquipmentId(String equipmentId) { this.equipmentId = equipmentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentId='" + equipmentId + '\'' +
                ", name='" + name + '\'' +
                ", condition='" + condition + '\'' +
                '}';
    }
}
