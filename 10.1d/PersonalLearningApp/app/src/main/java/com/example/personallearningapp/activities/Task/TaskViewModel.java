package com.example.personallearningapp.activities.Task;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.personallearningapp.models.Quiz;
import com.example.personallearningapp.models.UserInterest;
import com.example.personallearningapp.persistence.AppDatabase;
import com.example.personallearningapp.utils.QuizFetchListener;
import com.example.personallearningapp.utils.QuizService;
import com.example.personallearningapp.utils.RetrofitClient;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskViewModel extends ViewModel {
    Quiz quiz;
    QuizService quizService;
    private Context context;
    private AppDatabase appDb;
    private QuizFetchListener quizFetchListener;

    public TaskViewModel(Context context) {
        this.context = context;
        this.appDb = AppDatabase.getInstance(context);
    }

    public void setQuizFetchListener(QuizFetchListener listener) {
        this.quizFetchListener = listener;
    }

    public ListenableFuture<List<UserInterest>> getAllByUsername(String username) {
        return appDb.getUserInterestDao().getAllByUsername(username);
    }

    public void fetchQuizData(String topic) {
        quizService = RetrofitClient.getClient().create(QuizService.class);
        Call<Quiz> call = quizService.getQuiz(topic);

        call.enqueue(new Callback<Quiz>() {
            @Override
            public void onResponse(Call<Quiz> call, Response<Quiz> response) {
                if(response.isSuccessful()) {
                    quiz = response.body();
                    quiz.setTopic(quiz.getTopic().replaceAll("%20", " "));
                    if (quizFetchListener != null) {
                        quizFetchListener.onQuizFetched(quiz);
                    }
                } else {
                    Toast.makeText(context, "error fetching quiz data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Quiz> call, Throwable throwable) {
                Toast.makeText(context, "failed fetching quiz data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
