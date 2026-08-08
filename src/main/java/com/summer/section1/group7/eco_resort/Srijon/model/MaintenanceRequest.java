package com.summer.section1.group7.eco_resort.Srijon.model;

import java.io.Serial;
import java.io.Serializable;

public class MaintenanceRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String requestId;
    private String roomNumber;
    private String issue;
    private String status;

    public MaintenanceRequest(String requestId, String roomNumber, String issue, String status) {
        this.requestId = requestId;
        this.roomNumber = roomNumber;
        this.issue = issue;
        this.status = status;
    }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public String getIssue() { return issue; }
    public void setIssue(String issue) { this.issue = issue; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "MaintenanceRequest{" +
                "requestId='" + requestId + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", issue='" + issue + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
