package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    Button playButton;
    Button addToPlaylistButton;
    Button myPlaylistButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        playButton = findViewById(R.id.play_button);
        addToPlaylistButton = findViewById(R.id.add_to_playlist_button);
        myPlaylistButton = findViewById(R.id.my_playlist_button);

        playButton.setOnClickListener(this);
        addToPlaylistButton.setOnClickListener(this);
        myPlaylistButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;

        if(clickedButton.getId()==R.id.play_button) {
            Intent intent = new Intent(HomeActivity.this, PlayActivity.class);
            startActivity(intent);
        } else if(clickedButton.getId() == R.id.my_playlist_button) {
            Intent intent = new Intent(HomeActivity.this, MyPlaylistActivity.class);
            startActivity(intent);
        }
    }
}