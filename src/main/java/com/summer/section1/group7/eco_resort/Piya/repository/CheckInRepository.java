package com.summer.section1.group7.eco_resort.Piya.repository;

import com.summer.section1.group7.eco_resort.Piya.model.CheckInRecord;

import java.util.ArrayList;

public class CheckInRepository {

    private ArrayList<CheckInRecord> checkInList;

    public CheckInRepository() {

        checkInList = new ArrayList<>();

    }

    public void addCheckIn(CheckInRecord checkInRecord) {

        checkInList.add(checkInRecord);

    }

    public ArrayList<CheckInRecord> getCheckInList() {

        return checkInList;

    }

}
