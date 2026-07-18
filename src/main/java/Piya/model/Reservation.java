package Piya.model;

import java.time.LocalDate;

public class Reservation {
    private final int reservationId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String bookingStatus;

    public Reservation(int reservationId, LocalDate checkInDate, LocalDate checkOutDate, String bookingStatus) {
        this.reservationId = reservationId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingStatus = bookingStatus;
    }

    public int getReservationId() {
        return reservationId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
    public void bookRoom() {

        bookingStatus = "Booked";

        System.out.println("Room booked successfully.");

    }

    public void viewReservationHistory() {

        System.out.println("Displaying reservation history.");

    }

    public void cancelReservation() {

        bookingStatus = "Cancelled";

        System.out.println("Reservation cancelled.");

    }
}
