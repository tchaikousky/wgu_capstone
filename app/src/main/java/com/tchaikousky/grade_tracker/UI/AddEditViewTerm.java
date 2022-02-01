package com.tchaikousky.grade_tracker.UI;

import android.app.AlertDialog;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.tchaikousky.grade_tracker.Database.CourseRepository;
import com.tchaikousky.grade_tracker.Database.TermRepository;
import com.tchaikousky.grade_tracker.Entities.Term;
import com.tchaikousky.grade_tracker.R;

import java.util.Objects;

import static android.view.Gravity.CENTER;

public class AddEditViewTerm extends AppCompatActivity {
    private String title;
    private String startToString;
    private String endToString;
    private int id;
    private EditText editText;
    private TermRepository repository;
    private TextView startDate;
    private TextView endDate;
    private View startDP;
    private View endDP;
    private View saveButton;
    private View cancelButton;
    private Button editButton;
    private Button reportButton;
    private DatePicker start;
    private DatePicker end;
    private Button viewCoursesButton;
    private Button deleteButton;
    private LinearLayout mainLinearLayout;
    private LinearLayout courseLinear;
    private Boolean isEditView;
    private Boolean isActivityStart;
    private CourseRepository courseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_view_term);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Add Term");

        startDate = new TextView(this);
        endDate = new TextView(this);
        editButton = new Button(this);
        reportButton = new Button(this);
        viewCoursesButton = new Button(this);
        courseLinear = new LinearLayout(this);

        isActivityStart = true;
        isEditView = false;
        id = getIntent().getIntExtra("termID", 0);
        title = getIntent().getStringExtra("title");
        startToString = getIntent().getStringExtra("startDateTextView");
        endToString = getIntent().getStringExtra("endDateTextView");
        editText = findViewById(R.id.titleTextView);
        start = findViewById(R.id.startDatePicker);
        end = findViewById(R.id.endDatePicker);
        mainLinearLayout = findViewById(R.id.mainTermLinearLayout);

        if(title != null) {
            startDP = findViewById(R.id.startDatePicker);
            endDP = findViewById(R.id.endDatePicker);
            saveButton = findViewById(R.id.saveButton);
            cancelButton = findViewById(R.id.cancelButton);
            setNonEditView();
        }

        repository = new TermRepository(getApplication());
        courseRepository = new CourseRepository(getApplication());
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.homeScreen:
                Intent home = new Intent(AddEditViewTerm.this, MainActivity.class);
                startActivity(home);
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.course_menu, menu);
        menu.removeItem(R.id.share);
        return true;
    }

    public void saveTerm(View view) {
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
        if(title == null) {
            if(editText.getText().toString().trim().equals("")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("The title field is required.");
                builder.setPositiveButton("Ok", (dialog, id) -> {
                });
                builder.show();
            } else {
                Term term = new Term(0, editText.getText().toString(), startDateString,
                        endDateString);
                repository.insert(term);
                Intent intent = new Intent(AddEditViewTerm.this, TermList.class);
                startActivity(intent);
            }
        } else {
            if(editText.getText().toString().trim().equals("")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("The title field is required.");
                builder.setPositiveButton("Ok", (dialog, id) -> {
                });
                builder.show();
            } else {
                int id = getIntent().getIntExtra("termID", -1);
                Term term = new Term(id, editText.getText().toString(),
                        startDateString, endDateString);
                repository.update(term);
                Intent intent = new Intent(AddEditViewTerm.this, TermList.class);
                startActivity(intent);
            }
        }

    }

    public void cancel(View view) {
        Intent intent;
        if(isEditView) {
            setNonEditView();
        } else {
            intent = new Intent(AddEditViewTerm.this, TermList.class);
            startActivity(intent);
        }

    }

    public void delete() {
        if(courseRepository.getTermCourses(id).size() > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Terms must have zero associated courses in order to be deleted.");
            builder.setPositiveButton("Ok", (dialog, id) -> {
            });
            builder.show();
        } else {
            Term thisTerm = new Term(id, title, startToString, endToString);
            repository.delete(thisTerm);
            Intent intent = new Intent(AddEditViewTerm.this, TermList.class);
            intent.putExtra("termID", id);
            startActivity(intent);
        }
    }

    public void setNonEditView() {
        Objects.requireNonNull(getSupportActionBar()).setTitle("Term - " +title);
        isEditView = false;
        editText.setText(title);
        editText.setEnabled(false);
        editButton.setText(R.string.edit);
        reportButton.setText("Generate Report");
        deleteButton = new Button(this);
        deleteButton.setText(R.string.delete);
        deleteButton.setOnClickListener(v -> delete());
        startDate.setText(startToString);
        endDate.setText(endToString);
        editButton.setOnClickListener(v -> setEditView());
        reportButton.setOnClickListener(v -> generateReport());

        if(isActivityStart) {
            mainLinearLayout.addView(courseLinear);
            courseLinear.setOrientation(LinearLayout.HORIZONTAL);
            courseLinear.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
            courseLinear.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            courseLinear.setGravity(CENTER);
            courseLinear.addView(viewCoursesButton);
            courseLinear.addView(reportButton);
            viewCoursesButton.setText(R.string.viewCourses);
            viewCoursesButton.setOnClickListener(v -> {
                Intent intent = new Intent(AddEditViewTerm.this, CourseList.class);
                intent.putExtra("termID", id);
                startActivity(intent);
            });
            isActivityStart = false;
        } else {
            courseLinear.addView(viewCoursesButton);
            courseLinear.addView(reportButton);
        }

        ((ViewGroup) startDP.getParent()).addView(startDate);
        ((ViewGroup) startDP.getParent()).removeView(startDP);
        ((ViewGroup) endDP.getParent()).addView(endDate);
        ((ViewGroup) endDP.getParent()).removeView(endDP);
        ((ViewGroup) saveButton.getParent()).addView(editButton);
        ((ViewGroup) saveButton.getParent()).removeView(saveButton);
        ((ViewGroup) editButton.getParent()).removeView(cancelButton);
        ((ViewGroup) editButton.getParent()).addView(deleteButton);

    }

    public void setEditView() {
        Objects.requireNonNull(getSupportActionBar()).setTitle("Edit Term - " + title);
        isEditView = true;
        String[] termStart = startToString.split("-", 0);
        String[] termEnd = endToString.split("-", 0);

        ((ViewGroup) startDate.getParent()).addView(startDP);
        ((ViewGroup) startDate.getParent()).removeView(startDate);
        ((ViewGroup) endDate.getParent()).addView(endDP);
        ((ViewGroup) endDate.getParent()).removeView(endDate);
        ((ViewGroup) editButton.getParent()).addView(saveButton);
        ((ViewGroup) editButton.getParent()).removeView(editButton);
        ((ViewGroup) saveButton.getParent()).removeView(cancelButton);
        ((ViewGroup) saveButton.getParent()).removeView(deleteButton);
        ((ViewGroup) saveButton.getParent()).addView(cancelButton);
        ((ViewGroup) viewCoursesButton.getParent()).removeView(reportButton);
        ((ViewGroup) viewCoursesButton.getParent()).removeView(viewCoursesButton);

        editText.setEnabled(true);
        start.updateDate(Integer.parseInt(termStart[2]), Integer.parseInt(termStart[0]) - 1,
                Integer.parseInt(termStart[1]));
        end.updateDate(Integer.parseInt(termEnd[2]), Integer.parseInt(termEnd[0]) - 1,
                Integer.parseInt(termEnd[1]));
    }

    public void generateReport() {
        Intent intent = new Intent(AddEditViewTerm.this, ViewReport.class);
        intent.putExtra("termID", id);
        intent.putExtra("termTitle", title);
        startActivity(intent);
    }

}