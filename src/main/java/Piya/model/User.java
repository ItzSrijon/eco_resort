package Piya.model;

public abstract class User {
    private final int userId;
    private final String fullName;
    private String phoneNumber;
    private String password;
    private String email;

    public User(int userId, String fullName,  String phoneNumber,String email,String password) {
        this.userId = userId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;

    }

    public int getUserId() {
        return userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public boolean login() {

        return true;

    }

    public void logout() {

        System.out.println("User logged out.");

    }
}
