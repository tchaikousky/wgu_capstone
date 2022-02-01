package com.tchaikousky.grade_tracker.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tchaikousky.grade_tracker.DAO.ObjectiveAssessmentDao;
import com.tchaikousky.grade_tracker.Entities.ObjectiveAssessment;

@Database(entities = {ObjectiveAssessment.class}, version = 1, exportSchema = false)
public abstract class ObjectiveAssessmentDatabaseBuilder extends RoomDatabase {
    public abstract ObjectiveAssessmentDao ObjectiveAssessmentDao();

    public static volatile ObjectiveAssessmentDatabaseBuilder INSTANCE;

    static ObjectiveAssessmentDatabaseBuilder getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (ObjectiveAssessmentDatabaseBuilder.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ObjectiveAssessmentDatabaseBuilder.class, "ObjectiveAssessmentDatabase.db").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
