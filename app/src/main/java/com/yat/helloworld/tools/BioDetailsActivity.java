package com.yat.helloworld.tools;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.yat.helloworld.R;

import java.util.HashMap;

public class BioDetailsActivity extends AppCompatActivity {

    TextView txBio;
    HashMap <String ,String> nagip2,taha2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio_details);
        txBio= findViewById(R.id.tx_bioDetails);
        nagip2  = (HashMap<String,String>) getIntent().getExtras().get("nagipKey");
        taha2  = (HashMap<String,String>) getIntent().getExtras().get("tahaKey");

        if (nagip2!=null){
            showBioDetails(nagip2);
        }
        else if (taha2!=null){
            showBioDetails(taha2);
        }


    }

    public void showBioDetails(HashMap<String ,String > hashMapData){

        hashMapData.get(1);
        hashMapData.get(2);

        txBio.setText(hashMapData.get(1) + "\n"+ hashMapData.get(2) );

    }
}