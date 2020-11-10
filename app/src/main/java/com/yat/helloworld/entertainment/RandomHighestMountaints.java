package com.yat.helloworld.entertainment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yat.helloworld.R;

import java.util.Random;

public class RandomHighestMountaints extends Fragment implements View.OnClickListener {

    TextView tvRandomMountains;
    Button btnShuffle;
    int randomNumber;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_random_highest_mountaints, container, false);

        tvRandomMountains = v.findViewById(R.id.tv_randomMountains);
        btnShuffle = v.findViewById(R.id.btn_shuffle);
        btnShuffle.setOnClickListener(this);

        return v;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_shuffle:
                final String[] mountains = {"Everest", "Alps Mountains",
                        "Gapl Tarek", "El mo2atam Mountain", "Colorado Mountains",
                        "Mousa Mountains", "Saint Catrine Mountains"};

                Random randomMountains = new Random();
                randomNumber = randomMountains.nextInt(mountains.length);
                tvRandomMountains.setText(mountains[randomNumber]);

                break;
        }
    }
}