package com.summer.section1.group7.eco_resort.Piya.model;

import com.summer.section1.group7.eco_resort.User;
import java.io.Serializable;
import java.time.LocalDate;

public class Incident implements Serializable {

    private String incidentId;
    private String title;
    private String description;
    private String category;
    private String location;
    private LocalDate date;
    private User reportedBy;
    private String status;


    public Incident(String incidentId,
                    String title,
                    String description,
                    String category,
                    String location,
                    LocalDate date,
                    User reportedBy,
                    String status){

        this.incidentId = incidentId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.location = location;
        this.date = date;
        this.reportedBy = reportedBy;
        this.status = status;
    }


    public String getIncidentId(){
        return incidentId;
    }


    public String getTitle(){
        return title;
    }


    public String getDescription(){
        return description;
    }


    public String getCategory(){
        return category;
    }


    public String getLocation(){
        return location;
    }


    public LocalDate getDate(){
        return date;
    }


    public User getReportedBy(){
        return reportedBy;
    }


    public String getStatus(){
        return status;
    }


    @Override
    public String toString(){

        return incidentId+" "+title+" "+category+" "+status;
    }

}