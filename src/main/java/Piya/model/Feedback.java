package Piya.model;

public class Feedback {
    private final int feedbackId;
    private int rating;
    private String comment;

    public Feedback(int feedbackId, int rating, String comment) {
        this.feedbackId = feedbackId;
        this.rating = rating;
        this.comment = comment;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public void submitFeedback() {

        System.out.println("Feedback submitted successfully.");

    }
}
