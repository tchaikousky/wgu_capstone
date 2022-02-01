package com.tchaikousky.grade_tracker.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.tchaikousky.grade_tracker.Database.AssessmentRepository;
import com.tchaikousky.grade_tracker.Database.CourseRepository;
import com.tchaikousky.grade_tracker.Database.ObjectiveAssessmentRepository;
import com.tchaikousky.grade_tracker.Database.PerformanceAssessmentRepository;
import com.tchaikousky.grade_tracker.Entities.Assessment;
import com.tchaikousky.grade_tracker.Entities.Course;
import com.tchaikousky.grade_tracker.Entities.ObjectiveAssessment;
import com.tchaikousky.grade_tracker.Entities.PerformanceAssessment;
import com.tchaikousky.grade_tracker.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class AddEditViewCourse extends AppCompatActivity {
    private int termID;
    private int courseID;
    private String title;
    private String status;
    private String courseMentor;
    private String startDateAsString;
    private String endDateAsString;
    private String mentorPhone;
    private String mentorEmail;
    private String note;
    private DatePicker start;
    private DatePicker end;
    private EditText titleEditText;
    private EditText courseMentorEditText;
    private EditText mentorEmailEditText;
    private EditText mentorPhoneEditText;
    private EditText noteEditText;
    private EditText courseStatusEditText;
    private TextView startDate;
    private TextView endDate;
    private CourseRepository courseRepository;
    private Button saveButton;
    private Button editButton;
    private Button cancelButton;
    private Button deleteButton;
    private PerformanceAssessmentRepository performanceAssessmentRepository;
    private ObjectiveAssessmentRepository objectiveAssessmentRepository;
    private LinearLayout assessmentSection;
    private ToggleButton notificationToggle;
    private boolean notifications;
    private int notificationNumber;
    private int endNotificationNumber;
    private boolean viewingDetails = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_view_course);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Add Course");

        viewingDetails = false;
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);
        notificationToggle = findViewById(R.id.notificationToggle);
        startDate = new TextView(this);
        endDate = new TextView(this);
        editButton = new Button(this);
        editButton.setText(R.string.edit);
        deleteButton = new Button(this);
        deleteButton.setText(R.string.delete);
        deleteButton.setOnClickListener(v -> delete());
        courseRepository = new CourseRepository(getApplication());
        termID = getIntent().getIntExtra("termID", 0);
        courseID = getIntent().getIntExtra("courseID", 0);
        title = getIntent().getStringExtra("title");
        status = getIntent().getStringExtra("courseStatus");
        courseMentor = getIntent().getStringExtra("courseMentor");
        mentorPhone = getIntent().getStringExtra("mentorPhone");
        mentorEmail = getIntent().getStringExtra("mentorEmail");
        startDateAsString = getIntent().getStringExtra("startDateTextView");
        endDateAsString = getIntent().getStringExtra("endDateTextView");
        notificationNumber = getIntent().getIntExtra("notificationNumber", 0);
        endNotificationNumber = getIntent().getIntExtra("endNotificationNumber", 0);
        note = getIntent().getStringExtra("note");
        start = findViewById(R.id.courseStartDatePicker);
        end = findViewById(R.id.courseEndDatePicker);
        titleEditText = findViewById(R.id.titleEditText);
        assessmentSection = findViewById(R.id.assessmentSection);
        courseStatusEditText = findViewById(R.id.courseStatusEditText);
        courseMentorEditText = findViewById(R.id.courseMentorTextView);
        mentorEmailEditText = findViewById(R.id.mentorEmailEditText);
        mentorPhoneEditText = findViewById(R.id.mentorPhoneEditText);
        noteEditText = findViewById(R.id.courseNoteEditText);
        notifications = getIntent().getBooleanExtra("courseNotifications", false);
        if(notifications) {
            notificationToggle.setText(R.string.on);
        } else {
            notificationToggle.setText(R.string.off);
        }
        if(courseID != 0) {
            setNonEditView();
        } else {
            ((ViewGroup) assessmentSection.getParent()).removeView(assessmentSection);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.share:
                if(!viewingDetails) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Notes are not sharable while editing a course.");
                    builder.setPositiveButton("Ok", (dialog, id) -> {
                    });
                    builder.show();
                } else {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TITLE, title);
                    intent.putExtra(Intent.EXTRA_TEXT,
                            "Check out my notes for " + title + ":\n\n" + "-" + note);
                    intent.setType("text/plain");

                    Intent shareIntent = Intent.createChooser(intent, null);
                    startActivity(shareIntent);
                }
                return true;
            case R.id.homeScreen:
                Intent home = new Intent(AddEditViewCourse.this, MainActivity.class);
                startActivity(home);
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.course_menu, menu);
        return true;
    }



    public void saveCourse(View view) {
        String startDay = String.valueOf(start.getDayOfMonth());
        String startMonth = String.valueOf(start.getMonth() + 1);
        String startYear = String.valueOf(start.getYear());
        String endDay = String.valueOf(end.getDayOfMonth());
        String endMonth = String.valueOf(end.getMonth() + 1);
        String endYear = String.valueOf(end.getYear());
        String startDateString =
                startMonth.concat("-").concat(startDay).concat("-").concat(startYear);
        String endDateString =
                endMonth.concat("-").concat(endDay).concat("-").concat(endYear);
        if(titleEditText.getText().toString().trim().equals("") || courseStatusEditText.getText().toString().trim().equals("") ||
                courseMentorEditText.getText().toString().trim().equals("") || mentorPhoneEditText.getText().toString().trim().equals("") ||
                mentorEmailEditText.getText().toString().equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("All fields except 'Notes' are required");
            builder.setPositiveButton("Ok", (dialog, id) -> {
            });
            builder.show();
        } else {
            notifications = notificationToggle.getText().toString().equals("ON");

            if ((notificationNumber != 0 && notifications)) {
                startNotification("Course begins today!", startDateString);
            } else if ((notificationNumber == 0) && notifications) {
                notificationNumber = MainActivity.notificationNumber;
                startNotification("Course begins today!", startDateString);
                MainActivity.notificationNumber++;
            }

            if ((endNotificationNumber != 0 && notifications)) {
                endNotification("Course ends today!", endDateString);
            } else if ((endNotificationNumber == 0) && notifications) {
                endNotificationNumber = MainActivity.notificationNumber;
                endNotification("Course ends today!", endDateString);
                MainActivity.notificationNumber++;
            }

            Course course = new Course(courseID, titleEditText.getText().toString(), startDateString,
                    endDateString, courseStatusEditText.getText().toString(),
                    courseMentorEditText.getText().toString(),
                    mentorPhoneEditText.getText().toString(),
                    mentorEmailEditText.getText().toString(), noteEditText.getText().toString(),
                    termID, notifications, notificationNumber, endNotificationNumber);
            if (courseID == 0) {
                courseRepository.insert(course);
            } else {
                courseRepository.update(course);
            }


            Intent intent = new Intent(AddEditViewCourse.this, CourseList.class);
            intent.putExtra("termID", termID);
            startActivity(intent);
        }
    }

    public void cancel(View view) {
        Intent intent = new Intent(AddEditViewCourse.this, CourseList.class);
        intent.putExtra("termID", termID);
        startActivity(intent);
    }

    public void delete() {

        if(performanceAssessmentRepository.getAssessments(courseID).size() > 0 && objectiveAssessmentRepository.getAssessments(courseID).size() > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Courses must have zero associated assessments in order to be deleted.");
            builder.setPositiveButton("Ok", (dialog, id) -> {
            });
            builder.show();
        } else {
            Course thisCourse = new Course(courseID, title, startDateAsString,
                    endDateAsString, status, courseMentor, mentorPhone, mentorEmail, note, termID
                    , notifications, notificationNumber, endNotificationNumber);
            courseRepository.delete(thisCourse);
            Intent intent = new Intent(AddEditViewCourse.this, TermList.class);
            intent.putExtra("termID", termID);
            startActivity(intent);
        }
    }

    public void setNonEditView() {
        Objects.requireNonNull(getSupportActionBar()).setTitle("Course - " + title);
        viewingDetails = true;
        titleEditText.setText(title);
        titleEditText.setEnabled(false);
        courseStatusEditText.setText(status);
        courseStatusEditText.setEnabled(false);
        courseMentorEditText.setText(courseMentor);
        courseMentorEditText.setEnabled(false);
        mentorEmailEditText.setEnabled(false);
        mentorPhoneEditText.setEnabled(false);
        noteEditText.setEnabled(false);
        mentorPhoneEditText.setText(mentorPhone);
        mentorEmailEditText.setText(mentorEmail);
        noteEditText.setText(note);
        startDate.setText(startDateAsString);
        startDate.setEnabled(false);
        endDate.setText(endDateAsString);
        endDate.setEnabled(false);
        notificationToggle.setEnabled(false);
        editButton.setOnClickListener(v -> setEditView());
        ((ViewGroup) start.getParent()).addView(startDate);
        ((ViewGroup) end.getParent()).addView(endDate);
        ((ViewGroup) startDate.getParent()).removeView(start);
        ((ViewGroup) endDate.getParent()).removeView(end);
        ((ViewGroup) saveButton.getParent()).addView(editButton);
        ((ViewGroup) editButton.getParent()).removeView(saveButton);
        ((ViewGroup) editButton.getParent()).removeView(cancelButton);
        ((ViewGroup) editButton.getParent()).addView(deleteButton);

        performanceAssessmentRepository = new PerformanceAssessmentRepository(getApplication());
        objectiveAssessmentRepository = new ObjectiveAssessmentRepository(getApplication());
        ArrayList<PerformanceAssessment> performanceAssessments =
                (ArrayList<PerformanceAssessment>) performanceAssessmentRepository.getAssessments(courseID);
        ArrayList<ObjectiveAssessment> objectiveAssessments =
                (ArrayList<ObjectiveAssessment>) objectiveAssessmentRepository.getAssessments(courseID);

        ArrayList<Assessment> assessments = new ArrayList<>();
        for (Assessment assessment: performanceAssessments
             ) {
            assessments.add(assessment);
        }
        for (Assessment assessment : objectiveAssessments) {
            assessments.add(assessment);
        }

        RecyclerView assessmentRecyclerView = findViewById(R.id.assessmentRecyclerView);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        assessmentRecyclerView.setAdapter(assessmentAdapter);
        assessmentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdapter.setAssessments(assessments);
    }

    public void setEditView() {
        Objects.requireNonNull(getSupportActionBar()).setTitle("Edit Course - " + title);
        viewingDetails = false;
        String[] courseStart = startDateAsString.split("-", 0);
        String[] courseEnd = endDateAsString.split("-", 0);

        start.updateDate(Integer.parseInt(courseStart[2]), Integer.parseInt(courseStart[0]) - 1,
                Integer.parseInt(courseStart[1]));
        end.updateDate(Integer.parseInt(courseEnd[2]), Integer.parseInt(courseEnd[0]) - 1,
                Integer.parseInt(courseEnd[1]));

        ((ViewGroup) startDate.getParent()).addView(start);
        ((ViewGroup) start.getParent()).removeView(startDate);
        ((ViewGroup) endDate.getParent()).addView(end);
        ((ViewGroup) end.getParent()).removeView(endDate);
        ((ViewGroup) editButton.getParent()).removeView(deleteButton);
        ((ViewGroup) editButton.getParent()).addView(saveButton);
        ((ViewGroup) saveButton.getParent()).removeView(editButton);
        ((ViewGroup) saveButton.getParent()).removeView(cancelButton);
        ((ViewGroup) saveButton.getParent()).addView(cancelButton);
        ((ViewGroup) assessmentSection.getParent()).removeView(assessmentSection);
        titleEditText.setEnabled(true);
        courseStatusEditText.setEnabled(true);
        courseMentorEditText.setEnabled(true);
        mentorPhoneEditText.setEnabled(true);
        mentorEmailEditText.setEnabled(true);
        noteEditText.setEnabled(true);
        notificationToggle.setEnabled(true);

    }

    public void addAssessment(View view) {
        Intent intent = new Intent(AddEditViewCourse.this, AddEditViewAssessment.class);
        intent.putExtra("courseID", courseID);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.addAssessment);
        startActivity(intent);
    }

    public void toggleSwitch(View view) {
        if(notificationToggle.getText().equals("ON")) {
            notificationToggle.setText(R.string.off);
            notifications = false;
        } else {
            notificationToggle.setText(R.string.on);
            notifications = true;
        }
    }

    public void startNotification(String courseNotification, String startDateAsString) {
        Date testDate = null;
        String format = "MM-dd-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        try {
            testDate = sdf.parse(startDateAsString);
        } catch (ParseException e){
            e.printStackTrace();
        }
        Long trigger = testDate.getTime();

        Intent intent = new Intent(AddEditViewCourse.this, MyReceiver.class);

        if(notificationToggle.getText().toString().equals("ON")) {
            intent.putExtra("key", titleEditText.getText().toString() + " begins today!");
            intent.putExtra("contentTitle", courseNotification);
            intent.putExtra("notificationNumber", notificationNumber);
            intent.putExtra("notifications", notifications);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(AddEditViewCourse.this,
                    ++MainActivity.notificationNumber, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, pendingIntent);
        }
    }

    public void endNotification(String courseNotification, String endDateAsString) {
        Date testDate = null;
        String format = "MM-dd-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        try {
            testDate = sdf.parse(endDateAsString);
        } catch (ParseException e){
            e.printStackTrace();
        }
        Long trigger = testDate.getTime();

        Intent intent = new Intent(AddEditViewCourse.this, MyReceiver.class);

        if(notificationToggle.getText().toString().equals("ON")) {
            intent.putExtra("key", titleEditText.getText().toString() + " ends today!");
            intent.putExtra("contentTitle", courseNotification);
            intent.putExtra("notificationNumber", endNotificationNumber);
            intent.putExtra("notifications", notifications);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(AddEditViewCourse.this,
                    ++MainActivity.notificationNumber, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, pendingIntent);
        }
    }
}