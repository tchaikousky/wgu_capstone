package com.tchaikousky.grade_tracker.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.tchaikousky.grade_tracker.Database.CourseRepository;
import com.tchaikousky.grade_tracker.Entities.Course;
import com.tchaikousky.grade_tracker.R;

import java.util.ArrayList;
import java.util.Objects;

public class CourseList extends AppCompatActivity {
    private int termID;
    private String searchItem;
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Courses");
        termID = getIntent().getIntExtra("termID", 0);
        CourseRepository repository = new CourseRepository(getApplication());
        ArrayList<Course> courses = (ArrayList<Course>) repository.getTermCourses(getIntent().getIntExtra("termID", 0));
        RecyclerView recyclerView = findViewById(R.id.courseRecyclerView);
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter.setCourses(courses);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.homeScreen:
                Intent home = new Intent(CourseList.this, MainActivity.class);
                startActivity(home);
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.course_menu, menu);
        menu.removeItem(R.id.share);
        return true;
    }

    public void addCourse(View view) {
        Intent intent = new Intent(CourseList.this, AddEditViewCourse.class);
        intent.putExtra("termID", termID);
        startActivity(intent);
    }

    public void searchCoursesByName(View view) {
        searchEditText = findViewById(R.id.searchEditText);
        searchItem = searchEditText.getText().toString();
        CourseRepository repository = new CourseRepository(getApplication());
        ArrayList<Course> courses =
                (ArrayList<Course>) repository.getSearchedCourses(searchItem);
        RecyclerView recyclerView = findViewById(R.id.courseRecyclerView);
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter.setCourses(courses);
    }
}