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
import com.tchaikousky.grade_tracker.R;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {
    private List<Assessment> assessments;
    private final Context context;
    private final LayoutInflater inflator;

    public AssessmentAdapter(Context context) {
        inflator = LayoutInflater.from(context);
        this.context = context;
    }

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentIDItemView;
        private final TextView assessmentTitleItemView;
        private final TextView assessmentDueDateItemView;
        private final TextView assessmentTypeItemView;

        private AssessmentViewHolder(View itemView) {
            super(itemView);
            assessmentIDItemView = itemView.findViewById(R.id.assessmentIDTextView);
            assessmentTitleItemView = itemView.findViewById(R.id.assessmentTitleTextView);
            assessmentDueDateItemView = itemView.findViewById(R.id.assessmentDueDateTextView);
            assessmentTypeItemView = itemView.findViewById(R.id.assessmentTypeTextView);


            itemView.setOnClickListener(v -> {
                if(assessments.size() == 0) {

                } else {
                    int position = getAdapterPosition();
                    final Assessment current = assessments.get(position);
                    Intent intent = new Intent(context, AddEditViewAssessment.class);
                    intent.putExtra("assessmentID", current.getAssessmentID());
                    intent.putExtra("title", current.getTitle());
                    intent.putExtra("dueDate", current.getDueDate());
                    intent.putExtra("goalDate", current.getGoalDate());
                    intent.putExtra("type", current.getType());
                    intent.putExtra("note", current.getNote());
                    intent.putExtra("courseID", current.getCourseID());
                    intent.putExtra("notifications", current.getNotifications());
                    intent.putExtra("notificationNumber", current.getNotificationNumber());
                    intent.putExtra("goalNotificationNumber", current.getGoalNotification());
                    intent.putExtra("assessmentDetails", current.getAssessmentDetails());
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View itemView = inflator.inflate(R.layout.assessment_list_item, parent, false);
        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if(assessments.size() == 0) {
            holder.assessmentTitleItemView.setText("-");
            holder.assessmentDueDateItemView.setText("-");
            holder.assessmentTypeItemView.setText("-");
            holder.assessmentIDItemView.setText("-");
        } else {
            Assessment current = assessments.get(position);
            holder.assessmentIDItemView.setText(String.valueOf(current.getAssessmentID()));
            holder.assessmentTitleItemView.setText(current.getTitle());
            holder.assessmentDueDateItemView.setText(current.getDueDate());
            holder.assessmentTypeItemView.setText(current.getType());
        }
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