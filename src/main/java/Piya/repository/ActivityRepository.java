package Piya.repository;

import Piya.model.Activity;

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