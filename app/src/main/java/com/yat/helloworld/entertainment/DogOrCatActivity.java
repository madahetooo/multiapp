package com.yat.helloworld.entertainment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.yat.helloworld.R;

public class DogOrCatActivity extends Fragment  implements View.OnClickListener {

    RadioGroup radioGroupOne, radioGroupTwo, radioGroupThree, radioGroupFour;
    RadioButton radioButtonSelected1, radioButtonSelected2, radioButtonSelected3, radioButtonSelected4;
    TextView tvResult;
    Button btnGetResult;
    View v;

    int result = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         v = inflater.inflate(R.layout.activity_dog_or_cat, container, false);

        radioGroupOne = v.findViewById(R.id.radio_group1);
        radioGroupTwo = v.findViewById(R.id.radio_group2);
        radioGroupThree = v.findViewById(R.id.radio_group3);
        radioGroupFour = v.findViewById(R.id.radio_group4);
        tvResult = v.findViewById(R.id.tv_result);
        btnGetResult = v.findViewById(R.id.btn_get_result);
        btnGetResult.setOnClickListener(this);

        return v;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_get_result:

                int selectedRadioButtonId = radioGroupOne.getCheckedRadioButtonId();
                radioButtonSelected1 = v.findViewById(selectedRadioButtonId);

                int selectedRadioButtonId2 = radioGroupTwo.getCheckedRadioButtonId();
                radioButtonSelected2 = v.findViewById(selectedRadioButtonId2);

                int selectedRadioButtonId3 = radioGroupThree.getCheckedRadioButtonId();
                radioButtonSelected3 = v.findViewById(selectedRadioButtonId3);

                int selectedRadioButtonId4 = radioGroupFour.getCheckedRadioButtonId();
                radioButtonSelected4 = v.findViewById(selectedRadioButtonId4);


                if (selectedRadioButtonId == -1 || selectedRadioButtonId2 ==-1 ||
                        selectedRadioButtonId3==-1 || selectedRadioButtonId4==-1){
                    Toast.makeText(getActivity(), "Please choose at least one answer", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (radioButtonSelected1.getText().toString().toUpperCase().equals("NEVER") &&
                            radioButtonSelected2.getText().toString().toUpperCase().equals("SOMETIMES") &&
                            radioButtonSelected3.getText().toString().toUpperCase().equals("NEVER WATCH TV") &&
                            radioButtonSelected4.getText().toString().toUpperCase().equals("DAILY")) {

                        tvResult.setText("You are a Cat Person");
                    } else if (radioButtonSelected1.getText().toString().toUpperCase().equals("SOMETIMES") &&
                            radioButtonSelected2.getText().toString().toUpperCase().equals("ALL THE TIME") &&
                            radioButtonSelected3.getText().toString().toUpperCase().equals("NOT TOO MUCH") &&
                            radioButtonSelected4.getText().toString().toUpperCase().equals("OCCASIONALLY")) {


                        tvResult.setText("You are a Dog Person");

                    } else if (radioButtonSelected1.getText().toString().toUpperCase().equals("ALWAYS") &&
                            radioButtonSelected2.getText().toString().toUpperCase().equals("MAYBE") &&
                            radioButtonSelected3.getText().toString().toUpperCase().equals("NO") &&
                            radioButtonSelected4.getText().toString().toUpperCase().equals("FROM TIME TO TIME")) {
                        tvResult.setText("You are a CAT Person");

                    } else {
                        tvResult.setText("You are a Dog Person");
                    }

                }



                break;
        }

    }
}