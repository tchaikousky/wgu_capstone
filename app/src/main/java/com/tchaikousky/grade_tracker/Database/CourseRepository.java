package com.tchaikousky.grade_tracker.Database;

import android.app.Application;

import com.tchaikousky.grade_tracker.DAO.CourseDao;
import com.tchaikousky.grade_tracker.Entities.Course;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CourseRepository {
    private CourseDao mCourseDao;
    private List<Course> courses;
    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public CourseRepository(Application application) {
        CourseDatabaseBuilder db = CourseDatabaseBuilder.getDatabase(application);
        mCourseDao = db.courseDao();
    }

    public List<Course> getTermCourses(int termID) {
        databaseExecutor.execute(() -> {
            courses = mCourseDao.getTermCourses(termID);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<Course> getSearchedCourses(String courseName) {
        databaseExecutor.execute(() -> {
            courses = mCourseDao.getCourses(courseName + "%");
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<Course> getSingleCourse(int courseID) {
        databaseExecutor.execute(() -> {
            courses = mCourseDao.getSingleCourse(courseID);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public void insert(Course course) {
        databaseExecutor.execute(() -> {
            mCourseDao.insert(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Course course) {
        databaseExecutor.execute(() ->  {
            mCourseDao.update(course);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Course course) {
        databaseExecutor.execute(() ->  {
            mCourseDao.delete(course);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
