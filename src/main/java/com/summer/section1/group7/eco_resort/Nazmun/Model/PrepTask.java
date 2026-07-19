package com.summer.section1.group7.eco_resort.Nazmun.Model;

public class PrepTask {
    private final javafx.beans.property.SimpleStringProperty dishName;
    private final javafx.beans.property.SimpleIntegerProperty quantity;
    private final javafx.beans.property.SimpleStringProperty notes;

    public PrepTask(String dishName, int quantity, String notes) {
        this.dishName = new javafx.beans.property.SimpleStringProperty(dishName);
        this.quantity = new javafx.beans.property.SimpleIntegerProperty(quantity);
        this.notes = new javafx.beans.property.SimpleStringProperty(notes);
    }

    public String getDishName() { return dishName.get(); }
    public int getQuantity() { return quantity.get(); }
    public String getNotes() { return notes.get(); }

    public javafx.beans.property.SimpleStringProperty dishNameProperty() { return dishName; }
    public javafx.beans.property.SimpleIntegerProperty quantityProperty() { return quantity; }
    public javafx.beans.property.SimpleStringProperty notesProperty() { return notes; }
}