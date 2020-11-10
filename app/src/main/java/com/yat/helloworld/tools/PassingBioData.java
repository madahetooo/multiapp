package com.yat.helloworld.tools;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yat.helloworld.R;

import java.util.HashMap;

public class PassingBioData extends Fragment implements View.OnClickListener {

    ImageView imageNagip, imageTaha;

    String nagibBio = "Born in Cairo in 1911, Naguib Mahfouz began writing " +
            "when he was seventeen. His first novel was published in 1939 and ten more were written before" +
            " the Egyptian Revolution of July 1952, when he stopped" +
            " writing for several years. One novel was republished in 1953,";

    String tahaBio = "Taha Hussein was born in Izbet el Kilo, a village in the Minya Governorate in central Upper Egypt." +
            " He went to a kuttab," +
            " and thereafter was admitted to El Azhar University, where he studied Religion and Arabic literature. ";

    HashMap<Integer, String> nagip, taha;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_passing_bio_data, container, false);


        imageNagip = v.findViewById(R.id.iv_nagip);
        imageTaha = v.findViewById(R.id.iv_taha);
        imageNagip.setOnClickListener(this);
        imageTaha.setOnClickListener(this);


        nagip = new HashMap<>();
        taha = new HashMap<>();

        nagip.put(1, "Nagip Mahfouz");
        nagip.put(2, nagibBio);

        taha.put(1, "Taha Hussien");
        taha.put(2, tahaBio);


        return v;

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_nagip:
                Intent goToBioDetails = new Intent(getContext(), BioDetailsActivity.class);
                goToBioDetails.putExtra("nagipKey", nagip);
                startActivity(goToBioDetails);

                break;
            case R.id.iv_taha:
                goToBioDetails = new Intent(getContext(), BioDetailsActivity.class);
                goToBioDetails.putExtra("tahaKey", taha);
                startActivity(goToBioDetails);

                break;

        }

    }
}