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
            System.out.println("No previous users found.");
        }
    }

    // Login using Username or User ID
    public static User checkLogIn(String input, String password) {

        for (User user : userList) {

            boolean validUser =
                    user.getUsername().equalsIgnoreCase(input)
                            || user.getUserId().equalsIgnoreCase(input);

            if (validUser && user.getPassword().equals(password)) {
                loggedInUser = user;
                return user;
            }
        }

        return null;
    }

    // Add User
    public static void addUser(User user) {
        userList.add(user);
        saveUsersToFile();
    }

    // Save user.bin
    private static void saveUsersToFile() {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream("user.bin"))) {

            for (User user : userList) {
                oos.writeObject(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Auto Generate User ID
    public static String generateUserId() {

        int max = 0;

        for (User user : userList) {

            String id = user.getUserId().substring(2);   // U-001 -> 001
            int number = Integer.parseInt(id);

            if (number > max) {
                max = number;
            }
        }

        return String.format("U-%03d", max + 1);
    }

    // Check duplicate username
    public static boolean usernameExists(String username) {

        for (User user : userList) {

            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }

        return false;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public static List<User> getUserList() {
        return userList;
    }
}