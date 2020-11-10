package com.yat.helloworld.entertainment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.yat.helloworld.R;

public class VideoActivity extends AppCompatActivity {

    VideoView videoView;
    MediaController mediaController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_controller);
        videoView = findViewById(R.id.video_local);

        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.jutin);
        videoView.setVideoURI(videoUri);


        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.music:
                Intent intent = new Intent(getApplicationContext(), MusicPlayer.class);
                startActivity(intent);
                break;
            case R.id.test:
                Toast.makeText(this, "Test Toast Clicked", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}