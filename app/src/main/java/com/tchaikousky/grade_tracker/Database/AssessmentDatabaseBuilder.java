package com.tchaikousky.grade_tracker.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tchaikousky.grade_tracker.DAO.AssessmentDao;
import com.tchaikousky.grade_tracker.Entities.Assessment;

@Database(entities = {Assessment.class}, version = 1, exportSchema = false)
public abstract class AssessmentDatabaseBuilder extends RoomDatabase {
    public abstract AssessmentDao AssessmentDao();

    public static volatile AssessmentDatabaseBuilder INSTANCE;

    static AssessmentDatabaseBuilder getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (AssessmentDatabaseBuilder.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AssessmentDatabaseBuilder.class, "AssessmentDatabase.db").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}