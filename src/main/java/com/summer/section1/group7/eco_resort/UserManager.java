package com.summer.section1.group7.eco_resort;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private static final List<User> userList = new ArrayList<>();
    private static User loggedInUser = null;

    static {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.bin"))) {
            while (true) {
                try {
                    User user = (User) ois.readObject();
                    userList.add(user);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
    }

    public static User checkLogIn(String username, String password) {
        for (User user : userList) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                return user;
            }
        }
        return null;
    }

    public static void addUser(User user) {
        userList.add(user);
        saveUsersToFile();
        System.out.println("User added: " + user.getName());
    }

    private static void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.bin"))) {
            for (User user : userList) {
                oos.writeObject(user);
            }
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }
}