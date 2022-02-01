package com.tchaikousky.grade_tracker.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;
@Entity(tableName = "assessment_table")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;

    private String title;
    private String dueDate;
    private String type;
    private String note;
    private int courseID;
    private boolean notifications;
    private int notificationNumber;
    private int goalNotification;
    private String goalDate;

    public Assessment(int assessmentID, String title, String dueDate, String type,
                      String note, int courseID, boolean notifications, int notificationNumber,
                      int goalNotification, String goalDate) {
        this.assessmentID = assessmentID;
        this.title = title;
        this.dueDate = dueDate;
        this.type = type;
        this.note = note;
        this.courseID = courseID;
        this.notifications = notifications;
        this.notificationNumber = notificationNumber;
        this.goalNotification = goalNotification;
        this.goalDate = goalDate;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }

    public int getCourseID() { return courseID; }

    public void setCourseID(int courseID) { this.courseID = courseID; }

    public boolean getNotifications() { return notifications; }

    public void setNotifications(boolean notifications) { this.notifications = notifications; }

    public int getNotificationNumber() { return notificationNumber; }

    public void setNotificationNumber(int notificationNumber) { this.notificationNumber =
            notificationNumber; }

    public int getGoalNotification() {
        return goalNotification;
    }

    public void setGoalNotification(int goalNotification) {
        this.goalNotification = goalNotification;
    }

    public String getGoalDate() {
        return goalDate;
    }

    public void setGoalDate(String goalDate) {
        this.goalDate = goalDate;
    }

    public String getAssessmentDetails() {
        return "assessmentDetails";
    }

    @NotNull
    @Override
    public String toString() {
        return "Assessment{" +
                "assessmentID=" + assessmentID +
                ", title='" + title + '\'' +
                ", dueDate=" + dueDate +
                ", type='" + type + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
