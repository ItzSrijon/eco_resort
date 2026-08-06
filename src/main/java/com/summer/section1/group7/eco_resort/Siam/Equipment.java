package com.summer.section1.group7.eco_resort.Siam;
import java.io.Serializable;

public class Equipment implements Serializable {

    private String equipmentName;
    private int quantity;

    public Equipment(String equipmentName, int quantity) {
        this.equipmentName = equipmentName;
        this.quantity = quantity;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return equipmentName;
    }
}