package com.example.itubeapp.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.itubeapp.playlist.MyPlaylistActivity;
import com.example.itubeapp.PlayActivity;
import com.example.itubeapp.R;
import com.example.itubeapp.data.playlist.PlaylistItem;
import com.example.itubeapp.utils.ViewModelUtils;
import com.google.android.material.textfield.TextInputEditText;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    TextInputEditText urlInput;
    Button playButton;
    Button addToPlaylistButton;
    Button myPlaylistButton;
    int uid;
    HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        uid = getIntent().getIntExtra("UID", -1);
        viewModel = new ViewModelUtils().getViewModel(this, HomeViewModel.class);

        urlInput = findViewById(R.id.url_input);
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
        String urlText = urlInput.getText().toString().trim();

        if(clickedButton.getId()==R.id.play_button) {
            String videoId = extractVideoId(urlText);
            if(urlText.isEmpty()){
                Toast.makeText(this, "Enter a url to play video", Toast.LENGTH_SHORT).show();
                return;
            }
            if (videoId == null) {
                Toast.makeText(this, "Incorrect url format", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(HomeActivity.this, PlayActivity.class);
            intent.putExtra("VIDEO_ID", videoId);
            startActivity(intent);
        } else if(clickedButton.getId() == R.id.my_playlist_button) {
            Intent intent = new Intent(HomeActivity.this, MyPlaylistActivity.class);
            intent.putExtra("UID", uid);
            startActivity(intent);
        } else if (clickedButton.getId() == R.id.add_to_playlist_button) {
            if (urlText.isEmpty()) {
                Toast.makeText(this, "Enter url before attempting to save", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!isValidYouTubeUrl(urlText)) {
                Toast.makeText(this, "Invalid YouTube video url", Toast.LENGTH_SHORT).show();
                return;
            }
            PlaylistItem item = new PlaylistItem(uid, urlText);
            viewModel.insertPlaylistItem(item, this);
            Toast.makeText(this, "Url added to playlist", Toast.LENGTH_SHORT).show();
        }
    }

    private String extractVideoId(String youtubeUrl) {
        String videoId = null;
        if (youtubeUrl != null && youtubeUrl.trim().length() > 0) {
            String[] split = youtubeUrl.split("=");
            if (split.length > 1) {
                videoId = split[1];
            }
        }
        return videoId;
    }

    private boolean isValidYouTubeUrl(String url) {
        String regex = "^(https?\\:\\/\\/)?(www\\.)?(youtube\\.com\\/watch\\?v=\\w{11}|youtu\\.be\\/\\w{11})$";
        return url.matches(regex);
    }
}