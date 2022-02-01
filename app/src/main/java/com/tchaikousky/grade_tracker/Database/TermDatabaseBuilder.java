package com.tchaikousky.grade_tracker.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tchaikousky.grade_tracker.DAO.TermDao;
import com.tchaikousky.grade_tracker.Entities.Term;

@Database(entities = {Term.class}, version = 1, exportSchema = false)
public abstract class TermDatabaseBuilder extends RoomDatabase {
    public abstract TermDao TermDao();

    public static volatile TermDatabaseBuilder INSTANCE;

    static TermDatabaseBuilder getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (TermDatabaseBuilder.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TermDatabaseBuilder.class, "TermDatabase.db").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
