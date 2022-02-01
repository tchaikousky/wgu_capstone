package com.tchaikousky.grade_tracker.Entities;

import junit.framework.TestCase;

public class CourseTest extends TestCase {

    public void testGetCourseID() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        int expected = 1;
        assertEquals(course.getCourseID(), expected);
    }

    public void testSetCourseID() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        int expected = 10;
        course.setCourseID(expected);
        assertEquals(course.getCourseID(), expected);
    }

    public void testGetTitle() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        String expected = "my course";
        assertEquals(course.getTitle(), expected);
    }

    public void testSetTitle() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        String expected = "new title";
        course.setTitle(expected);
        assertEquals(course.getTitle(), expected);
    }

    public void testGetStartDate() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        String expected = "12-01-2021";
        assertEquals(course.getStartDate(), expected);
    }

    public void testSetStartDate() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        String expected = "01-01-2022";
        course.setStartDate(expected);
        assertEquals(course.getStartDate(), expected);
    }

    public void testGetEndDate() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        String expected = "12-10-2021";
        assertEquals(course.getEndDate(), expected);
    }

    public void testSetEndDate() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        String expected = "01-21-2022";
        course.setEndDate(expected);
        assertEquals(course.getEndDate(), expected);
    }

    public void testGetStatus() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        String expected = "in progress";
        assertEquals(course.getStatus(), expected);
    }

    public void testSetStatus() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        String expected = "new status";
        course.setStatus(expected);
        assertEquals(course.getStatus(), expected);
    }

    public void testGetCourseMentor() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        String expected = "jim";
        assertEquals(course.getCourseMentor(), expected);
    }

    public void testSetCourseMentor() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        String expected = "new mentor";
        course.setCourseMentor(expected);
        assertEquals(course.getCourseMentor(), expected);
    }

    public void testGetEmail() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        String expected = "jim@email.com";
        assertEquals(course.getEmail(), expected);
    }

    public void testSetEmail() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        String expected = "new@mail.com";
        course.setEmail(expected);
        assertEquals(course.getEmail(), expected);
    }

    public void testGetPhone() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        String expected = "123-456-7890";
        assertEquals(course.getPhone(), expected);
    }

    public void testSetPhone() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        String expected = "555-555-5555";
        course.setPhone(expected);
        assertEquals(course.getPhone(), expected);
    }

    public void testGetNote() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        String expected = "notes here";
        assertEquals(course.getNote(), expected);
    }

    public void testSetNote() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        String expected = "more notes here";
        course.setNote(expected);
        assertEquals(course.getNote(), expected);
    }

    public void testGetTermID() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        int expected = 1;
        assertEquals(course.getTermID(), expected);
    }

    public void testSetTermID() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        int expected = 4;
        course.setTermID(expected);
        assertEquals(course.getTermID(), expected);
    }

    public void testGetNotifications() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        assertEquals(course.getNotifications(), false);
    }

    public void testSetNotifications() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        course.setNotifications(true);
        assertEquals(course.getNotifications(), true);
    }

    public void testGetNotificationNumber() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        int expected = 1;
        assertEquals(course.getNotificationNumber(), expected);
    }

    public void testSetNotificationNumber() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        int expected = 3;
        course.setNotificationNumber(expected);
        assertEquals(course.getNotificationNumber(), expected);
    }

    public void testGetEndNotificationNumber() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        int expected = 1;
        assertEquals(course.getEndNotificationNumber(), expected);
    }

    public void testSetEndNotificationNumber() {
        Course course = new Course(1, "my course", "12-01-2021", "12-10-2021", "in progress",
                "jim", "123-456-7890", "jim@email.com", "notes here", 1, false, 1, 1);
        int expected = 3;
        course.setEndNotificationNumber(expected);
        assertEquals(course.getEndNotificationNumber(), expected);
    }
}