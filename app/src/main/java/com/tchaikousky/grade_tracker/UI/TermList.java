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

import com.tchaikousky.grade_tracker.Database.TermRepository;
import com.tchaikousky.grade_tracker.Entities.Term;
import com.tchaikousky.grade_tracker.R;

import java.util.List;
import java.util.Objects;

public class TermList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Terms");
        TermRepository repository = new TermRepository(getApplication());
        List<Term> terms = repository.getTerms();
        RecyclerView recyclerView = findViewById(R.id.termRecyclerView);
        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(terms);

    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.homeScreen:
                Intent home = new Intent(TermList.this, MainActivity.class);
                startActivity(home);
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.course_menu, menu);
        menu.removeItem(R.id.share);
        return true;
    }

    public void addTerm(View view) {
        Intent intent = new Intent(TermList.this, AddEditViewTerm.class);
        startActivity(intent);
    }

}