package com.tchaikousky.grade_tracker.Database;

import android.app.Application;

import com.tchaikousky.grade_tracker.DAO.TermDao;
import com.tchaikousky.grade_tracker.Entities.Term;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TermRepository {
    private TermDao mTermDao;
    private List<Term> terms;
    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public TermRepository(Application application) {
        TermDatabaseBuilder db = TermDatabaseBuilder.getDatabase(application);
        mTermDao = db.TermDao();
    }

    public List<Term> getTerms() {
        databaseExecutor.execute(() -> {
            terms = mTermDao.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return terms;
    }

    public void insert(Term term) {
        databaseExecutor.execute(() -> {
            mTermDao.insert(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Term term) {
        databaseExecutor.execute(() ->  {
            mTermDao.update(term);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Term term) {
        databaseExecutor.execute(() -> {
            mTermDao.delete(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}