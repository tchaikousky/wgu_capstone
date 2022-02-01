package com.tchaikousky.grade_tracker.Entities;

import junit.framework.TestCase;

public class ObjectiveAssessmentTest extends TestCase {

    public void testGetPassingScore() {
        ObjectiveAssessment assessment = new ObjectiveAssessment(1, "assessment title", "12" +
                "-12-2012","performance", "optional notes", 1, false, 1,
                1, "12-12-2021", 85);

        int expected = 85;
        assertEquals(assessment.getPassingScore(), expected);
    }

    public void testSetPassingScore() {
        ObjectiveAssessment assessment = new ObjectiveAssessment(1, "assessment title", "12" +
                "-12-2012","performance", "optional notes", 1, false, 1,
                1, "12-12-2021", 85);

        int expected = 100;
        assessment.setPassingScore(expected);
        assertEquals(assessment.getPassingScore(), expected);
    }

}