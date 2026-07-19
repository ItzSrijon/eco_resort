package com.summer.section1.group7.eco_resort.Piya.repository;

import com.summer.section1.group7.eco_resort.Piya.model.CheckOutRecord;

import java.util.ArrayList;

public class CheckOutRepository {

    private ArrayList<CheckOutRecord> checkOutList;

    public CheckOutRepository() {

        checkOutList = new ArrayList<>();

    }


    public void addCheckOut(CheckOutRecord checkOutRecord) {

        checkOutList.add(checkOutRecord);

    }

    public ArrayList<CheckOutRecord> getCheckOutList() {

        return checkOutList;

    }

}