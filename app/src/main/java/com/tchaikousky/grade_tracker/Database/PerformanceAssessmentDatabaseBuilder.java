package com.tchaikousky.grade_tracker.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tchaikousky.grade_tracker.DAO.PerformanceAssessmentDao;
import com.tchaikousky.grade_tracker.Entities.PerformanceAssessment;

@Database(entities = {PerformanceAssessment.class}, version = 1, exportSchema = false)
public abstract class PerformanceAssessmentDatabaseBuilder extends RoomDatabase {
    public abstract PerformanceAssessmentDao PerformanceAssessmentDao();

    public static volatile PerformanceAssessmentDatabaseBuilder INSTANCE;

    static PerformanceAssessmentDatabaseBuilder getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (PerformanceAssessmentDatabaseBuilder.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PerformanceAssessmentDatabaseBuilder.class,
                            "PerformanceAssessmentDatabase.db").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
