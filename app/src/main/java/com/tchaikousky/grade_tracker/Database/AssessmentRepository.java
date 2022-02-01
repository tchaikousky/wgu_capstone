package com.tchaikousky.grade_tracker.Database;

import android.app.Application;

import com.tchaikousky.grade_tracker.DAO.AssessmentDao;
import com.tchaikousky.grade_tracker.Entities.Assessment;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AssessmentRepository {
    private AssessmentDao mAssessmentDao;
    private List<Assessment> assessments;
    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public AssessmentRepository(Application application) {
        AssessmentDatabaseBuilder db = AssessmentDatabaseBuilder.getDatabase(application);
        mAssessmentDao = db.AssessmentDao();
    }

    public List<Assessment> getAssessments(int courseID) {
        databaseExecutor.execute(() -> {
            assessments = mAssessmentDao.getAllAssessments(courseID);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return assessments;
    }

    public void insert(Assessment assessment) {
        databaseExecutor.execute(() -> {
            mAssessmentDao.insert(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Assessment assessment) {
        databaseExecutor.execute(() ->  {
            mAssessmentDao.update(assessment);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Assessment assessment) {
        databaseExecutor.execute(() -> {
            mAssessmentDao.delete(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
