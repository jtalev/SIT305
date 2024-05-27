package com.example.personallearningapp.activities.History;

import android.content.ContentProvider;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personallearningapp.R;
import com.example.personallearningapp.models.PastQuiz;
import com.example.personallearningapp.models.QuizHistory;

import java.util.ArrayList;
import java.util.List;

public class HistoryContainerAdapter extends RecyclerView.Adapter<HistoryContainerAdapter.ViewHolder> {

    private final QuizHistory userQuizHistory;

    public HistoryContainerAdapter(
            QuizHistory userQuizHistory
    ) {
        this.userQuizHistory = userQuizHistory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType
    ) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_container_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PastQuiz pastQuiz = userQuizHistory.getHistory().get(position);
        holder.question.setText(pastQuiz.getQuestion());
        holder.answer1.setText(pastQuiz.getAnswer1());
        holder.answer2.setText(pastQuiz.getAnswer2());
        holder.answer3.setText(pastQuiz.getAnswer3());

        String actualAnswer = pastQuiz.getActualAnswer();
        String userAnswer = pastQuiz.getUserAnswer();

        List<TextView> answerList = new ArrayList<>();
        answerList.add(holder.answer1);
        answerList.add(holder.answer2);
        answerList.add(holder.answer3);

        for (TextView answer : answerList) {
            if (answer.getText().toString().equals(userAnswer)) {
                answer.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.red));
            }
        }
        for (TextView answer : answerList) {
            if (answer.getText().toString().equals(actualAnswer)) {
                answer.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.green));
            }
        }
    }

    @Override
    public int getItemCount() {
        return userQuizHistory.getHistory().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView question;
        TextView answer1;
        TextView answer2;
        TextView answer3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question_text);
            answer1 = itemView.findViewById(R.id.answer_1);
            answer2 = itemView.findViewById(R.id.answer_2);
            answer3 = itemView.findViewById(R.id.answer_3);
        }
    }
}
