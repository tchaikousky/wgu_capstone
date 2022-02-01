package com.tchaikousky.grade_tracker.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tchaikousky.grade_tracker.Entities.Term;
import com.tchaikousky.grade_tracker.R;

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {
    private List<Term> terms;
    private final Context context;
    private final LayoutInflater inflator;

    public TermAdapter(Context context) {
        inflator = LayoutInflater.from(context);
        this.context = context;
    }

    class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termIDItemView;
        private final TextView termTitleItemView;
        private final TextView termStartItemView;
        private final TextView termEndItemView;

        private TermViewHolder(View itemView) {
            super(itemView);
            termIDItemView = itemView.findViewById(R.id.idTextView);
            termTitleItemView = itemView.findViewById(R.id.textView);
            termStartItemView = itemView.findViewById(R.id.startDateTextView);
            termEndItemView = itemView.findViewById(R.id.endDateTextView);
            itemView.setOnClickListener(v -> {
                if(terms.size() == 0) {

                } else {
                    int position = getAdapterPosition();
                    final Term current = terms.get(position);
                    Intent intent = new Intent(context, AddEditViewTerm.class);
                    intent.putExtra("termID", current.getTermID());
                    intent.putExtra("title", current.getTitle());
                    intent.putExtra("startDateTextView", current.getStartDate());
                    intent.putExtra("endDateTextView", current.getEndDate());
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View itemView = inflator.inflate(R.layout.list_item, parent, false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull TermAdapter.TermViewHolder holder, int position) {
        if(terms.size() == 0) {
            holder.termTitleItemView.setText(R.string.noTermTitle);
            holder.termStartItemView.setText(R.string.noTermStart);
            holder.termEndItemView.setText(R.string.noTermEnd);
        } else {
            Term current = terms.get(position);
            holder.termIDItemView.setText(String.valueOf(current.getTermID()));
            holder.termTitleItemView.setText(current.getTitle());
            holder.termStartItemView.setText(current.getStartDate());
            holder.termEndItemView.setText(current.getEndDate());
        }
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(terms.size() == 0) {
            return 1;
        }
        return terms.size();
    }
}
