package com.tchaikousky.grade_tracker.Entities;

import junit.framework.TestCase;

import org.junit.Test;

public class AssessmentTest extends TestCase {

    public void testGetAssessmentID() {
        Assessment assessment = new Assessment(1, "assessment title", "12-12-2012", "performance"
                , "optional notes", 1, false, 1, 1, "12-12-2021");
        int expected = 1;
        assertEquals(assessment.getAssessmentID(), expected);
    }

    public void testSetAssessmentID() {
        Assessment assessment = new Assessment(1, "assessment title", "12-12-2012", "performance"
                , "optional notes", 1, false, 1, 1, "12-12-2021");
        int expected = 5;
        assessment.setAssessmentID(expected);
        assertEquals(assessment.getAssessmentID(), expected);
    }

    public void testGetTitle() {
        Assessment assessment = new Assessment(1, "assessment title", "12-12-2012", "performance"
                , "optional notes", 1, false, 1, 1, "12-12-2021");
        String expected = "assessment title";
        assertEquals(assessment.getTitle(), expected);
    }

    public void testSetTitle() {
        Assessment assessment = new Assessment(1, "assessment title", "12-12-2012", "performance"
                , "optional notes", 1, false, 1, 1, "12-12-2021");
        String expected = "new title";
        assessment.setTitle(expected);
        assertEquals(assessment.getTitle(), expected);
    }

    public void testGetDueDate() {
        Assessment assessment = new Assessment(1, "assessment title", "12-12-2012", "performance"
                , "optional notes", 1, false, 1, 1, "12-12-2021");
        String expected = "12-12-2012";
        assertEquals(assessment.getDueDate(), expected);
    }

    public void testSetDueDate() {
        Assessment assessment = new Assessment(1, "assessment title", "12-12-2012", "performance"
                , "optional notes", 1, false, 1, 1, "12-12-2021");
        String expected = "01-01-2022";
        assessment.setDueDate(expected);
        assertEquals(assessment.getDueDate(), expected);
    }

    public void testGetType() {
        Assessment assessment = new Assessment(1, "assessment title", "12-12-2012", "performance"
                , "optional notes", 1, false, 1, 1, "12-12-2021");
        String expected = "performance";
        assertEquals(assessment.getType(), expected);
    }

    public void testSetType() {
        Assessment assessment = new Assessment(1, "assessment title", "12-12-2012", "performance"
                , "optional notes", 1, false, 1, 1, "12-12-2021");
        String expected = "objective";
        assessment.setType(expected);
        assertEquals(assessment.getType(), expected);
    }

    public void testGetNote() {
        Assessment assessment = new Assessment(1, "assessment title", "12-12-2012", "performance"
                , "optional notes", 1, false, 1, 1, "12-12-2021");
        String expected = "optional notes";
        assertEquals(assessment.getNote(), expected);
    }

    public void testSetNote() {
        Assessment assessment = new Assessment(1, "assessment title", "12-12-2012", "performance"
                , "optional notes", 1, false, 1, 1, "12-12-2021");
        String expected = "new note";
        assessment.setNote(expected);
        assertEquals(assessment.getNote(), expected);
    }

    public void testGetCourseID() {
        Assessment assessment = new Assessment(1, "assessment title", "12-12-2012", "performance"
                , "optional notes", 1, false, 1, 1, "12-12-2021");
        int expected = 1;
        assertEquals(assessment.getCourseID(), expected);
    }

    public void testSetCourseID() {
        Assessment assessment = new Assessment(1, "assessment title", "12-12-2012", "performance"
                , "optional notes", 1, false, 1, 1, "12-12-2021");
        int expected = 10;
        assessment.setCourseID(expected);
        assertEquals(assessment.getCourseID(), expected);
    }

    public void testGetNotifications() {
        Assessment assessment = new Assessment(1, "assessment title", "12-12-2012", "performance"
                , "optional notes", 1, false, 1, 1, "12-12-2021");
        assertEquals(assessment.getNotifications(), false);
    }

    public void testSetNotifications() {
        Assessment assessment = new Assessment(1, "assessment title", "12-12-2012", "performance"
                , "optional notes", 1, false, 1, 1, "12-12-2021");
        assessment.setNotifications(true);
        assertEquals(assessment.getNotifications(), true);
    }

    public void testGetNotificationNumber() {
        Assessment assessment = new Assessment(1, "assessment title", "12-12-2012", "performance"
                , "optional notes", 1, false, 1, 1, "12-12-2021");
        int expected = 1;
        assertEquals(assessment.getNotificationNumber(), expected);
    }

    public void testSetNotificationNumber() {
        Assessment assessment = new Assessment(1, "assessment title", "12-12-2012", "performance"
                , "optional notes", 1, false, 1, 1, "12-12-2021");
        int expected = 5;
        assessment.setNotificationNumber(expected);
        assertEquals(assessment.getNotificationNumber(), expected);
    }

    public void testGetGoalNotification() {
        Assessment assessment = new Assessment(1, "assessment title", "12-12-2012", "performance"
                , "optional notes", 1, false, 1, 1, "12-12-2021");
        int expected = 1;
        assertEquals(assessment.getGoalNotification(), expected);
    }

    public void testSetGoalNotification() {
        Assessment assessment = new Assessment(1, "assessment title", "12-12-2012", "performance"
                , "optional notes", 1, false, 1, 1, "12-12-2021");
        int expected = 5;
        assessment.setGoalNotification(expected);
        assertEquals(assessment.getGoalNotification(), expected);
    }

    public void testGetGoalDate() {
        Assessment assessment = new Assessment(1, "assessment title", "12-12-2012", "performance"
                , "optional notes", 1, false, 1, 1, "12-12-2021");
        String expected = "12-12-2021";
        assertEquals(assessment.getGoalDate(), expected);
    }

    public void testSetGoalDate() {
        Assessment assessment = new Assessment(1, "assessment title", "12-12-2012", "performance"
                , "optional notes", 1, false, 1, 1, "12-12-2021");
        String expected = "01-10-2022";
        assessment.setGoalDate(expected);
        assertEquals(assessment.getGoalDate(), expected);
    }

}