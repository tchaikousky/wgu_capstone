package com.tchaikousky.grade_tracker.Database;

import android.app.Application;

import com.tchaikousky.grade_tracker.DAO.ObjectiveAssessmentDao;
import com.tchaikousky.grade_tracker.Entities.ObjectiveAssessment;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObjectiveAssessmentRepository {
    private ObjectiveAssessmentDao mAssessmentDao;
    private List<ObjectiveAssessment> assessments;
    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public ObjectiveAssessmentRepository(Application application) {
        ObjectiveAssessmentDatabaseBuilder db = ObjectiveAssessmentDatabaseBuilder.getDatabase(application);
        mAssessmentDao = db.ObjectiveAssessmentDao();
    }

    public List<ObjectiveAssessment> getAssessments(int courseID) {
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

    public void insert(ObjectiveAssessment assessment) {
        databaseExecutor.execute(() -> {
            mAssessmentDao.insert(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(ObjectiveAssessment assessment) {
        databaseExecutor.execute(() ->  {
            mAssessmentDao.update(assessment);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(ObjectiveAssessment assessment) {
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
