package Piya.repository;

import Piya.model.LostFoundItem;

import java.util.ArrayList;

public class LostFoundRepository {

    private ArrayList<LostFoundItem> lostFoundList;


    public LostFoundRepository() {

        lostFoundList = new ArrayList<>();

    }

    public void addItem(LostFoundItem item) {

        lostFoundList.add(item);

    }

    public ArrayList<LostFoundItem> getLostFoundList() {

        return lostFoundList;

    }

}