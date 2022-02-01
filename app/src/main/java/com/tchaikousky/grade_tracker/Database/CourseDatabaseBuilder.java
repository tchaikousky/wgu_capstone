package com.tchaikousky.grade_tracker.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tchaikousky.grade_tracker.DAO.CourseDao;
import com.tchaikousky.grade_tracker.Entities.Course;

@Database(entities = {Course.class}, version = 1, exportSchema = false)
public abstract class CourseDatabaseBuilder extends RoomDatabase {
    public abstract CourseDao courseDao();

    public static volatile CourseDatabaseBuilder INSTANCE;

    static CourseDatabaseBuilder getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (CourseDatabaseBuilder.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CourseDatabaseBuilder.class, "CourseDatabase.db").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
