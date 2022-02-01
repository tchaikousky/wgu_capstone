package com.tchaikousky.grade_tracker.Database;

import android.app.Application;

import com.tchaikousky.grade_tracker.DAO.PerformanceAssessmentDao;
import com.tchaikousky.grade_tracker.Entities.PerformanceAssessment;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PerformanceAssessmentRepository {
    private PerformanceAssessmentDao mAssessmentDao;
    private List<PerformanceAssessment> assessments;
    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public PerformanceAssessmentRepository(Application application) {
        PerformanceAssessmentDatabaseBuilder db =
                PerformanceAssessmentDatabaseBuilder.getDatabase(application);
        mAssessmentDao = db.PerformanceAssessmentDao();
    }

    public List<PerformanceAssessment> getAssessments(int courseID) {
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

    public void insert(PerformanceAssessment assessment) {
        databaseExecutor.execute(() -> {
            mAssessmentDao.insert(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(PerformanceAssessment assessment) {
        databaseExecutor.execute(() ->  {
            mAssessmentDao.update(assessment);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(PerformanceAssessment assessment) {
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