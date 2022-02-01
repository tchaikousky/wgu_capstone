package com.tchaikousky.grade_tracker.Entities;

import androidx.room.Entity;

@Entity(tableName = "objective_assessment_table")
public class ObjectiveAssessment extends Assessment{
    int passingScore;

    public ObjectiveAssessment(int assessmentID, String title, String dueDate, String type,
                               String note, int courseID, boolean notifications,
                               int notificationNumber, int goalNotification, String goalDate,
                               int passingScore) {
        super(assessmentID, title, dueDate, type, note, courseID, notifications,
                notificationNumber, goalNotification, goalDate);

        this.passingScore = passingScore;

    }

    public int getPassingScore() {
        return passingScore;
    }

    public void setPassingScore(int passingScore) {
        this.passingScore = passingScore;
    }

    @Override
    public String getAssessmentDetails() {
        return String.valueOf(passingScore);
    }

}
