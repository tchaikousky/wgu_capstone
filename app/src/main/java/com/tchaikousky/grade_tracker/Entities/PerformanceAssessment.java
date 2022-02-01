package com.tchaikousky.grade_tracker.Entities;

import androidx.room.Entity;

@Entity(tableName = "performance_assessment_table")
public class PerformanceAssessment extends Assessment{

    private String projectDetails;

    public PerformanceAssessment(int assessmentID, String title, String dueDate, String type,
                                 String note, int courseID, boolean notifications,
                                 int notificationNumber, int goalNotification, String goalDate,
                                 String projectDetails) {
        super(assessmentID, title, dueDate, type, note, courseID, notifications,
                notificationNumber, goalNotification, goalDate);

        this.projectDetails = projectDetails;
    }

    public String getProjectDetails() {
        return projectDetails;
    }

    public void setProjectDetails(String projectDetails) {
        this.projectDetails = projectDetails;
    }

    @Override
    public String getAssessmentDetails() {
        return this.projectDetails;
    }
}
