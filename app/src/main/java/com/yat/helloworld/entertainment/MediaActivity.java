package com.yat.helloworld.entertainment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yat.helloworld.R;

public class MediaActivity extends Fragment implements View.OnClickListener {

    Button exoPlayer, mediaPlayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_media, container, false);
        exoPlayer = v.findViewById(R.id.btn_exoPlayer);
        mediaPlayer = v.findViewById(R.id.btn_MediaController);
        exoPlayer.setOnClickListener(this);
        mediaPlayer.setOnClickListener(this);


        return v;

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_exoPlayer:

                Intent intent = new Intent(getContext(), ExoPlayerActivity.class);
                startActivity(intent);
                break;


            case R.id.btn_MediaController:
                Intent intent2 = new Intent(getContext(), VideoActivity.class);
                startActivity(intent2);
                break;
        }

    }
}