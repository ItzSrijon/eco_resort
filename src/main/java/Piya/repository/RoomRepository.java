package Piya.repository;

import Piya.model.Room;

import java.util.ArrayList;

public class RoomRepository {

    private ArrayList<Room> roomList;

    public RoomRepository() {

        roomList = new ArrayList<>();

    }

    public void addRoom(Room room) {

        roomList.add(room);

    }

    public ArrayList<Room> getRoomList() {

        return roomList;

    }

}
