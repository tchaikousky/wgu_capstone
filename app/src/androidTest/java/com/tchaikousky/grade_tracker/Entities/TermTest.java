package com.tchaikousky.grade_tracker.Entities;

import junit.framework.TestCase;

public class TermTest extends TestCase {

    public void testGetTermID() {
        Term term = new Term(1, "term", "12-01-2021", "12-10-2021");
        int expected = 1;
        assertEquals(term.getTermID(), expected);
    }

    public void testSetTermID() {
        Term term = new Term(1, "term", "12-01-2021", "12-10-2021");
        int expected = 5;
        term.setTermID(expected);
        assertEquals(term.getTermID(), expected);
    }

    public void testGetTitle() {
        Term term = new Term(1, "term", "12-01-2021", "12-10-2021");
        String expected = "term";
        assertEquals(term.getTitle(), expected);
    }

    public void testSetTitle() {
        Term term = new Term(1, "term", "12-01-2021", "12-10-2021");
        String expected = "new title";
        term.setTitle(expected);
        assertEquals(term.getTitle(), expected);
    }

    public void testGetStartDate() {
        Term term = new Term(1, "term", "12-01-2021", "12-10-2021");
        String expected = "12-01-2021";
        assertEquals(term.getStartDate(), expected);
    }

    public void testSetStartDate() {
        Term term = new Term(1, "term", "12-01-2021", "12-10-2021");
        String expected = "12-05-2021";
        term.setStartDate(expected);
        assertEquals(term.getStartDate(), expected);
    }

    public void testGetEndDate() {
        Term term = new Term(1, "term", "12-01-2021", "12-10-2021");
        String expected = "12-10-2021";
        assertEquals(term.getEndDate(), expected);
    }

    public void testSetEndDate() {
        Term term = new Term(1, "term", "12-01-2021", "12-10-2021");
        String expected = "12-15-2021";
        term.setEndDate(expected);
        assertEquals(term.getEndDate(), expected);
    }
}