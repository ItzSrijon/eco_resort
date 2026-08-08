package com.summer.section1.group7.eco_resort.Siam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class GymManager {

    public static GymMember findGymMember(String guestId) {

        ObservableList<GymMember> memberList = loadMembers();

        for (GymMember gm : memberList) {

            if (gm.getGuestId().equalsIgnoreCase(guestId.trim())) {
                return gm;
            }

        }

        return null;
    }
    public static ObservableList<GymMember> loadMembers() {

        ObservableList<GymMember> memberList = FXCollections.observableArrayList();
        File file = new File("gymMember.bin");
        if (!file.exists()) {
            return memberList;
        }

        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

            while (true) {

                try {
                    memberList.add((GymMember) ois.readObject());

                } catch (EOFException e) {

                    break;

                }

            }

            ois.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return memberList;
    }
    public static void saveMembers(ObservableList<GymMember> memberList) {

        try {

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("gymMember.bin"));

            for (GymMember gm : memberList) {

                oos.writeObject(gm);
            }

            oos.close();

        }

        catch (Exception e) {

            e.printStackTrace();

        }
    }
}