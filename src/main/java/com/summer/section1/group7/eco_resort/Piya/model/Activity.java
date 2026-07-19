package com.summer.section1.group7.eco_resort.Piya.model;

public class Activity {
    private final int activityId;
    private String activityName;
    private String schedule;
    private String description;

    public Activity(int activityId, String activityName, String schedule, String description) {
        this.activityId = activityId;
        this.activityName = activityName;
        this.schedule = schedule;
        this.description = description;
    }

    public int getActivityId() {
        return activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getDescription() {
        return description;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void viewActivities() {

        System.out.println("Displaying available activities.");

    }
}
