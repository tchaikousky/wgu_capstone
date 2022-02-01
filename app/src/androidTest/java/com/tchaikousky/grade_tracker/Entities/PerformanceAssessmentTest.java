package com.tchaikousky.grade_tracker.Entities;

import junit.framework.TestCase;

public class PerformanceAssessmentTest extends TestCase {

    public void testGetProjectDetails() {
        PerformanceAssessment assessment = new PerformanceAssessment(1, "assessment title", "12" +
                "-12-2012","performance", "optional notes", 1, false, 1,
                1, "12-12-2021", "project details");
        String expected = "project details";
        assertEquals(assessment.getProjectDetails(), expected);
    }

    public void testSetProjectDetails() {
        PerformanceAssessment assessment = new PerformanceAssessment(1, "assessment title", "12" +
                "-12-2012","performance", "optional notes", 1, false, 1,
                1, "12-12-2021", "project details");
        String expected = "new details";
        assessment.setProjectDetails(expected);
        assertEquals(assessment.getProjectDetails(), expected);
    }

}