package com.tchaikousky.grade_tracker.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tchaikousky.grade_tracker.Database.AssessmentRepository;
import com.tchaikousky.grade_tracker.Database.CourseRepository;
import com.tchaikousky.grade_tracker.Database.ObjectiveAssessmentRepository;
import com.tchaikousky.grade_tracker.Database.PerformanceAssessmentRepository;
import com.tchaikousky.grade_tracker.Database.TermRepository;
import com.tchaikousky.grade_tracker.Entities.Assessment;
import com.tchaikousky.grade_tracker.Entities.Course;
import com.tchaikousky.grade_tracker.Entities.ObjectiveAssessment;
import com.tchaikousky.grade_tracker.Entities.PerformanceAssessment;
import com.tchaikousky.grade_tracker.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.view.Gravity.CENTER;


public class ViewReport extends AppCompatActivity {
    private int termID;
    private String termTitle;
    private int courseID;
    private LinearLayout reportVerticalLayout;
    private List<Assessment> assessments = new ArrayList<>();
    private TextView date;
    private LocalDateTime now;
    private TextView titleReportHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_report);
        termTitle = getIntent().getStringExtra("termTitle");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Term Report: " + termTitle);
        CourseRepository courseRepository = new CourseRepository(getApplication());
        AssessmentRepository assessmentRepository = new AssessmentRepository(getApplication());
        PerformanceAssessmentRepository performanceAssessmentRepository =
                new PerformanceAssessmentRepository(getApplication());
        ObjectiveAssessmentRepository objectiveAssessmentRepository =
                new ObjectiveAssessmentRepository(getApplication());
        ArrayList<Course> courses =
                (ArrayList<Course>) courseRepository.getTermCourses(getIntent().getIntExtra(
                        "termID", 0));
        for (Course course : courses) {
            List<PerformanceAssessment> coursePerformanceAssessments =
                    performanceAssessmentRepository.getAssessments(course.getCourseID());
            List<ObjectiveAssessment> courseObjectiveAssessments =
                    objectiveAssessmentRepository.getAssessments(course.getCourseID());
            assessments.addAll(coursePerformanceAssessments);
            assessments.addAll(courseObjectiveAssessments);
            System.out.println("SIZE = " + assessments.size());
        }
//        reportVerticalLayout = findViewById(R.id.reportVerticalLayout);
        termID = getIntent().getIntExtra("termID", 0);
        RecyclerView recyclerView = findViewById(R.id.reportRecyclerView);
        final ReportAdapter reportAdapter = new ReportAdapter(this);
        recyclerView.setAdapter(reportAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reportAdapter.setCourses(courses);
        reportAdapter.setAssessments(assessments);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        date = findViewById(R.id.date);
        now = LocalDateTime.now();
        date.setText(String.valueOf(now.format(formatter)));
        titleReportHeader = findViewById(R.id.titleReportHeader);
        titleReportHeader.setText(termTitle + "'s Assessments");

    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.homeScreen:
                Intent home = new Intent(ViewReport.this, MainActivity.class);
                startActivity(home);
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.course_menu, menu);
        menu.removeItem(R.id.share);
        return true;
    }
}
