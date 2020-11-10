package com.yat.helloworld.entertainment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yat.helloworld.R;

import java.util.Timer;
import java.util.TimerTask;

public class MusicPlayer extends Fragment implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    MediaPlayer musicPlayer;
    SeekBar seekBarPosition;
    int seekPosition;
    ImageView play, pause, stop;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_music_player, container, false);
        play = v.findViewById(R.id.iv_play);
        pause = v.findViewById(R.id.iv_pause);
        stop = v.findViewById(R.id.iv_stop);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        seekBarPosition = v.findViewById(R.id.seekBar);
        seekBarPosition.setOnSeekBarChangeListener(this);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (musicPlayer != null)
                    seekBarPosition.setProgress(musicPlayer.getCurrentPosition());
            }
        }, 0, 500);

        return v;

    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int position, boolean user) {

        if (user) {
            musicPlayer.seekTo(position);
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_play:
                if (musicPlayer == null) {
                    musicPlayer = MediaPlayer.create(getContext(), R.raw.baby);
                    seekBarPosition.setMax(musicPlayer.getDuration());
                    musicPlayer.start();
                } else if (!musicPlayer.isPlaying()) {
                    musicPlayer.seekTo(seekPosition);
                    musicPlayer.start();
                }
                break;
            case R.id.iv_pause:
                musicPlayer.pause();
                seekPosition = musicPlayer.getCurrentPosition();
                break;
            case R.id.iv_stop:
                musicPlayer.stop();
                seekBarPosition.setProgress(0);
                musicPlayer = null;
                break;


        }


    }


}