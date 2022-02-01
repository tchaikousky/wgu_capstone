package com.tchaikousky.grade_tracker.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tchaikousky.grade_tracker.Entities.Course;
import com.tchaikousky.grade_tracker.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private List<Course> courses;
    private final Context context;
    private final LayoutInflater inflator;

    public CourseAdapter(Context context) {
        inflator = LayoutInflater.from(context);
        this.context = context;
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseIDItemView;
        private final TextView courseTitleItemView;
        private final TextView courseStatusItemView;
        private final TextView courseMentorItemView;
        private final TextView courseStartItemView;
        private final TextView courseEndItemView;

        private CourseViewHolder(View itemView) {
            super(itemView);
            courseIDItemView = itemView.findViewById(R.id.idTextView);
            courseTitleItemView = itemView.findViewById(R.id.textView);
            courseStatusItemView = itemView.findViewById(R.id.statusTextView);
            courseMentorItemView = itemView.findViewById(R.id.mentorTextView);
            courseStartItemView = itemView.findViewById(R.id.startDateTextView);
            courseEndItemView = itemView.findViewById(R.id.endDateTextView);
            itemView.setOnClickListener(v -> {
                if(courses.size() == 0) {

                } else {
                    int position = getAdapterPosition();
                    final Course current = courses.get(position);
                    Intent intent = new Intent(context, AddEditViewCourse.class);
                    intent.putExtra("courseID", current.getCourseID());
                    intent.putExtra("title", current.getTitle());
                    intent.putExtra("courseStatus", current.getStatus());
                    intent.putExtra("courseMentor", current.getCourseMentor());
                    intent.putExtra("mentorPhone", current.getPhone());
                    intent.putExtra("mentorEmail", current.getEmail());
                    intent.putExtra("startDateTextView", current.getStartDate());
                    intent.putExtra("endDateTextView", current.getEndDate());
                    intent.putExtra("note", current.getNote());
                    intent.putExtra("termID", current.getTermID());
                    intent.putExtra("courseNotifications", current.getNotifications());
                    intent.putExtra("notificationNumber", current.getNotificationNumber());
                    intent.putExtra("endNotificationNumber",
                            current.getEndNotificationNumber());
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View itemView = inflator.inflate(R.layout.course_list_item, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull CourseAdapter.CourseViewHolder holder, int position) {
        if(courses.size() == 0) {
            holder.courseTitleItemView.setText(R.string.noCourseTitle);
            holder.courseStatusItemView.setText(R.string.noCourseStatus);
            holder.courseMentorItemView.setText(R.string.noCourseMentor);
            holder.courseStartItemView.setText(R.string.noCourseStart);
            holder.courseEndItemView.setText(R.string.noCourseEnd);
        } else {
            Course current = courses.get(position);
            holder.courseIDItemView.setText(String.valueOf(current.getCourseID()));
            holder.courseTitleItemView.setText(current.getTitle());
            holder.courseStatusItemView.setText(current.getStatus());
            holder.courseMentorItemView.setText(current.getCourseMentor());
            holder.courseStartItemView.setText(current.getStartDate());
            holder.courseEndItemView.setText(current.getEndDate());
        }
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(courses.size() == 0) {
            return 1;
        }
        return courses.size();
    }
}

