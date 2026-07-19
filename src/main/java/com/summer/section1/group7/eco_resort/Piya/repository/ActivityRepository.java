package com.summer.section1.group7.eco_resort.Piya.repository;

import com.summer.section1.group7.eco_resort.Piya.model.Activity;

import java.util.ArrayList;

public class ActivityRepository {
    private ArrayList<Activity> activityList;

    public ActivityRepository() {

        activityList = new ArrayList<>();

    }

    public void addActivity(Activity activity) {

        activityList.add(activity);

    }

    public ArrayList<Activity> getActivityList() {

        return activityList;

    }

}