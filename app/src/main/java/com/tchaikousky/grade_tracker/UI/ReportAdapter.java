package com.tchaikousky.grade_tracker.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tchaikousky.grade_tracker.Entities.Assessment;
import com.tchaikousky.grade_tracker.Entities.Course;
import com.tchaikousky.grade_tracker.R;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {
    private List<Course> courses;
    private List<Assessment> assessments;
    private final Context context;
    private final LayoutInflater inflator;

    public ReportAdapter(Context context) {
        inflator = LayoutInflater.from(context);
        this.context = context;
    }

    class ReportViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseNameItemView;
        private final TextView assessmentNameItemView;
        private final TextView assessmentDueItemView;
        private final TextView assessmentGoalItemView;


        private ReportViewHolder(View itemView) {
            super(itemView);
            courseNameItemView = itemView.findViewById(R.id.courseName);
            assessmentNameItemView = itemView.findViewById(R.id.assessmentName);
            assessmentDueItemView = itemView.findViewById(R.id.assessmentDue);
            assessmentGoalItemView = itemView.findViewById(R.id.assessmentGoal);
//            itemView.setOnClickListener(v -> {
//                if(courses.size() == 0) {
//
//                } else {
//                    int position = getAdapterPosition();
//                    final Course current = courses.get(position);
//                    Intent intent = new Intent(context, AddEditViewCourse.class);
//                    intent.putExtra("courseID", current.getCourseID());
//                    intent.putExtra("title", current.getTitle());
//                    intent.putExtra("courseStatus", current.getStatus());
//                    intent.putExtra("courseMentor", current.getCourseMentor());
//                    intent.putExtra("mentorPhone", current.getPhone());
//                    intent.putExtra("mentorEmail", current.getEmail());
//                    intent.putExtra("startDateTextView", current.getStartDate());
//                    intent.putExtra("endDateTextView", current.getEndDate());
//                    intent.putExtra("note", current.getNote());
//                    intent.putExtra("termID", current.getTermID());
//                    intent.putExtra("courseNotifications", current.getNotifications());
//                    intent.putExtra("notificationNumber", current.getNotificationNumber());
//                    intent.putExtra("endNotificationNumber",
//                            current.getEndNotificationNumber());
//                    context.startActivity(intent);
//                }
//            });
        }
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ReportAdapter.ReportViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View itemView = inflator.inflate(R.layout.report_list_item, parent, false);
        return new ReportViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ReportAdapter.ReportViewHolder holder, int position) {
        if(assessments.size() == 0) {
            holder.courseNameItemView.setText(R.string.unavailable);
            holder.assessmentNameItemView.setText(R.string.unavailable);
            holder.assessmentGoalItemView.setText(R.string.unavailable);
            holder.assessmentDueItemView.setText(R.string.unavailable);
        } else {
            Assessment current = assessments.get(position);
            holder.assessmentNameItemView.setText(String.valueOf(current.getTitle()));
            holder.assessmentDueItemView.setText(String.valueOf(current.getDueDate()));
            holder.assessmentGoalItemView.setText(String.valueOf(current.getGoalDate()));
        }


    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(assessments.size() == 0) {
            return 1;
        }
        return assessments.size();
    }
}
