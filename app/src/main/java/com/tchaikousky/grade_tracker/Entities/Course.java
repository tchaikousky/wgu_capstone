package com.tchaikousky.grade_tracker.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "course_table")
public class Course {

    @PrimaryKey(autoGenerate = true)
    private int courseID;

    private String title;
    private String startDate;
    private String endDate;
    private String status;
    private String email;
    private String phone;
    private String courseMentor;
    private String note;
    private int termID;
    private boolean notifications;
    private int notificationNumber;
    private int endNotificationNumber;

    public Course(int courseID, String title, String startDate, String endDate, String status,
                  String courseMentor, String phone, String email, String note, int termID,
                  boolean notifications, int notificationNumber, int endNotificationNumber) {
        this.courseID = courseID;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.courseMentor = courseMentor;
        this.phone = phone;
        this.email = email;
        this.note = note;
        this.termID = termID;
        this.notifications = notifications;
        this.notificationNumber = notificationNumber;
        this.endNotificationNumber = endNotificationNumber;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCourseMentor() {
        return courseMentor;
    }

    public void setCourseMentor(String courseMentor) {
        this.courseMentor = courseMentor;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }

    public int getTermID() { return termID; }

    public void setTermID(int termID) { this.termID = termID; }

    public boolean getNotifications() { return notifications; }

    public void setNotifications(boolean notifications) { this.notifications = notifications; }

    public int getNotificationNumber() { return notificationNumber; }

    public void setNotificationNumber(int notificationNumber) { this.notificationNumber = notificationNumber; }

    public int getEndNotificationNumber() { return endNotificationNumber; }

    public void setEndNotificationNumber(int endNotificationNumber) { this.endNotificationNumber = endNotificationNumber; }

    @NotNull
    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                ", courseMentor='" + courseMentor + '\'' +
                '}';
    }
}
