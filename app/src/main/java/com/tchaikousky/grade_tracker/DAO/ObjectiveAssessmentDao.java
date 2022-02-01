package com.tchaikousky.grade_tracker.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.tchaikousky.grade_tracker.Entities.ObjectiveAssessment;

import java.util.List;

@Dao
public interface ObjectiveAssessmentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ObjectiveAssessment assessment);

    @Update
    void update(ObjectiveAssessment assessment);

    @Delete
    void delete(ObjectiveAssessment assessment);

    @Query("SELECT * FROM OBJECTIVE_ASSESSMENT_TABLE WHERE courseID = :courseID ORDER BY " +
            "assessmentID ASC")
    List<ObjectiveAssessment> getAllAssessments(int courseID);

    @Query("SELECT * FROM OBJECTIVE_ASSESSMENT_TABLE WHERE assessmentID = :id ")
    List<ObjectiveAssessment> getSingleAssessment(int id);
}
