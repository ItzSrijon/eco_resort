package com.summer.section1.group7.eco_resort.Srijon.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class WorkLog implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String logId;
    private String requestId;
    private String description;
    private LocalDate date;

    public WorkLog(String logId, String requestId, String description, LocalDate date) {
        this.logId = logId;
        this.requestId = requestId;
        this.description = description;
        this.date = date;
    }

    public String getLogId() { return logId; }
    public void setLogId(String logId) { this.logId = logId; }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    @Override
    public String toString() {
        return "WorkLog{" +
                "logId='" + logId + '\'' +
                ", requestId='" + requestId + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
