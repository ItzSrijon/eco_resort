package com.summer.section1.group7.eco_resort.Piya.model;

import java.io.Serializable;
import java.time.LocalDate;

public class LostAndFoundItem implements Serializable {

    private String itemId;
    private String category;
    private String itemName;
    private String description;
    private String location;
    private String status;
    private LocalDate date;

    public LostAndFoundItem(String itemId,
                            String category,
                            String itemName,
                            String description,
                            String location,
                            String status,
                            LocalDate date){

        this.itemId=itemId;
        this.category=category;
        this.itemName=itemName;
        this.description=description;
        this.location=location;
        this.status=status;
        this.date=date;
    }


    public String getItemId(){
        return itemId;
    }

    public String getCategory(){
        return category;
    }

    public String getItemName(){
        return itemName;
    }

    public String getDescription(){
        return description;
    }

    public String getLocation(){
        return location;
    }

    public String getStatus(){
        return status;
    }

    public LocalDate getDate(){
        return date;
    }


    public void setStatus(String status){
        this.status=status;
    }


    @Override
    public String toString(){

        return itemId+" "+itemName+" "+status;
    }
}