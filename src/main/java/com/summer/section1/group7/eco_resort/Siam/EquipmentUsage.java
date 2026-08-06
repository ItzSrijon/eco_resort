package com.summer.section1.group7.eco_resort.Siam;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class EquipmentUsage implements Serializable {

    private String memberId;
    private String memberName;
    private String equipmentName;
    private String duration;

    private LocalDate usageDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public EquipmentUsage(String memberId,
                          String memberName,
                          String equipmentName,
                          String duration,
                          LocalDate usageDate,
                          LocalTime startTime,
                          LocalTime endTime) {

        this.memberId = memberId;
        this.memberName = memberName;
        this.equipmentName = equipmentName;
        this.duration = duration;
        this.usageDate = usageDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public LocalDate getUsageDate() {
        return usageDate;
    }

    public void setUsageDate(LocalDate usageDate) {
        this.usageDate = usageDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "EquipmentUsage{" +
                "memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", equipmentName='" + equipmentName + '\'' +
                ", duration='" + duration + '\'' +
                ", usageDate=" + usageDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}