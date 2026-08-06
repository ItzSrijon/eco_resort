package com.summer.section1.group7.eco_resort.Nazmun.Model;

import java.io.Serializable;

public class StaffMember implements Serializable {

    private String staffId, name, role, shiftTiming, status, currentTask, taskTimeSlot;

    public StaffMember(String staffId, String name, String role, String shiftTiming, String status) {
        this.staffId = staffId;
        this.name = name;
        this.role = role;
        this.shiftTiming = shiftTiming;
        this.status = status;
        this.currentTask = "";
        this.taskTimeSlot = "";
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getShiftTiming() {
        return shiftTiming;
    }

    public void setShiftTiming(String shiftTiming) {
        this.shiftTiming = shiftTiming;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(String currentTask) {
        this.currentTask = currentTask;
    }

    public String getTaskTimeSlot() {
        return taskTimeSlot;
    }

    public void setTaskTimeSlot(String taskTimeSlot) {
        this.taskTimeSlot = taskTimeSlot;
    }

    @Override
    public String toString() {
        return "StaffMember{" +
                "staffId='" + staffId + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", shiftTiming='" + shiftTiming + '\'' +
                ", status='" + status + '\'' +
                ", currentTask='" + currentTask + '\'' +
                ", taskTimeSlot='" + taskTimeSlot + '\'' +
                '}';
    }
}