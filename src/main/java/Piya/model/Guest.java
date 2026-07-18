package Piya.model;

public class Guest extends User {

    public Guest(int userId,
                 String fullName,
                 String phoneNumber,
                 String email,
                 String password) {

        super(userId, fullName, phoneNumber, email, password);

    }

    public void register() {

        System.out.println("Guest Registered Successfully.");

    }

    public void bookRoom() {

        System.out.println("Room Booked Successfully.");

    }

    public void browseActivities() {

        System.out.println("Displaying Activities.");

    }

    public void viewReservationHistory() {

        System.out.println("Displaying Reservation History.");

    }

    public void updateProfile() {

        System.out.println("Profile Updated Successfully.");

    }

    public void submitFeedback() {

        System.out.println("Feedback Submitted.");

    }

}

