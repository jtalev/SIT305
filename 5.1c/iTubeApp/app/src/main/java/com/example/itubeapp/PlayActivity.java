package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;

import com.example.itubeapp.utils.YoutubePlayerUtil;

public class PlayActivity extends AppCompatActivity {
    ImageButton backButton;
    WebView youtubeWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        backButton = findViewById(R.id.back_button);
        youtubeWebView = findViewById(R.id.youtube_web_view);

        String videoId = getIntent().getStringExtra("VIDEO_ID");
        youtubeWebView.getSettings().setJavaScriptEnabled(true);
        youtubeWebView.loadData(new YoutubePlayerUtil().getJavaScriptString(videoId), "text/html", "utf-8");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}