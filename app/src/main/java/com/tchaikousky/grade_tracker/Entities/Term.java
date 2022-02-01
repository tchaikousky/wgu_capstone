package com.tchaikousky.grade_tracker.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "term_table")
public class Term {
    @PrimaryKey(autoGenerate = true)
    private int termID;

    private String title;
    private String startDate;
    private String endDate;

    public Term(int termID, String title, String startDate, String endDate) {
        this.termID = termID;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
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

    @NotNull
    @Override
    public String toString() {
        return "Term{" +
                "termID=" + termID +
                ", title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
