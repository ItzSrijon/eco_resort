package Nazmun;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MenuItem {
    private final StringProperty itemName;
    private final StringProperty category;
    private final StringProperty price;

    public MenuItem(String itemName, String category, String price) {
        this.itemName = new SimpleStringProperty(itemName);
        this.category = new SimpleStringProperty(category);
        this.price = new SimpleStringProperty(price);
    }

    public String getItemName() { return itemName.get(); }
    public void setItemName(String v) { itemName.set(v); }
    public StringProperty itemNameProperty() { return itemName; }

    public String getCategory() { return category.get(); }
    public void setCategory(String v) { category.set(v); }
    public StringProperty categoryProperty() { return category; }

    public String getPrice() { return price.get(); }
    public void setPrice(String v) { price.set(v); }
    public StringProperty priceProperty() { return price; }
}