package com.tchaikousky.grade_tracker.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.tchaikousky.grade_tracker.Entities.PerformanceAssessment;

import java.util.List;

@Dao
public interface PerformanceAssessmentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PerformanceAssessment assessment);

    @Update
    void update(PerformanceAssessment assessment);

    @Delete
    void delete(PerformanceAssessment assessment);

    @Query("SELECT * FROM PERFORMANCE_ASSESSMENT_TABLE WHERE courseID = :courseID ORDER BY " +
            "assessmentID ASC")
    List<PerformanceAssessment> getAllAssessments(int courseID);

    @Query("SELECT * FROM PERFORMANCE_ASSESSMENT_TABLE WHERE assessmentID = :id ")
    List<PerformanceAssessment> getSingleAssessment(int id);
}
