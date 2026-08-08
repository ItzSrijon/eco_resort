package com.summer.section1.group7.eco_resort.Piya.model;

import java.io.Serializable;

public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    private int activityId;
    private String activityName;
    private String category;
    private String schedule;
    private int capacity;
    private String status;
    private double price;
    private String description;


    public Activity(int activityId,
                    String activityName,
                    String category,
                    String schedule,
                    int capacity,
                    String status,
                    double price,
                    String description) {

        this.activityId = activityId;
        this.activityName = activityName;
        this.category = category;
        this.schedule = schedule;
        this.capacity = capacity;
        this.status = status;
        this.price = price;
        this.description = description;
    }



    public Activity(String activityName,
                    String category,
                    String schedule,
                    int capacity,
                    String status,
                    double price,
                    String description) {

        this(0,
                activityName,
                category,
                schedule,
                capacity,
                status,
                price,
                description);
    }



    public int getActivityId(){
        return activityId;
    }


    public void setActivityId(int activityId){
        this.activityId = activityId;
    }


    public String getActivityName(){
        return activityName;
    }


    public String getCategory(){
        return category;
    }


    public String getSchedule(){
        return schedule;
    }


    public int getCapacity(){
        return capacity;
    }


    public String getStatus(){
        return status;
    }


    public double getPrice(){
        return price;
    }


    public String getDescription(){
        return description;
    }



    public void setActivityName(String activityName){
        this.activityName = activityName;
    }


    public void setCategory(String category){
        this.category = category;
    }


    public void setSchedule(String schedule){
        this.schedule = schedule;
    }


    public void setCapacity(int capacity){
        this.capacity = capacity;
    }


    public void setStatus(String status){
        this.status = status;
    }


    public void setPrice(double price){
        this.price = price;
    }


    public void setDescription(String description){
        this.description = description;
    }



    public void viewActivities(){

        System.out.println("Displaying available activities.");

    }

}