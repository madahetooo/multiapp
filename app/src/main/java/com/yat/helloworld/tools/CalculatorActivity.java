package com.yat.helloworld.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yat.helloworld.R;

public class CalculatorActivity extends Fragment implements View.OnClickListener {


    EditText et_number1, et_number2;  // declare object for edit text
    Button btn_sum, btn_sub, btn_mul, btn_div;  // declare object for buttons
    String number1, number2;  // declare object for strings
    double valueOfNumber1, valueOfNumber2, result;  // declare variables as integer
    TextView tvResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_calculator, container, false);


        et_number1 = v.findViewById(R.id.et_firstNumber);  // casting view
        et_number2 = v.findViewById(R.id.et_secondNumber);// casting view
        btn_sum = v.findViewById(R.id.btn_addition);// casting view
        btn_sub = v.findViewById(R.id.btn_sub);// casting view
        btn_mul = v.findViewById(R.id.btn_multiplication);// casting view
        btn_div = v.findViewById(R.id.btn_division);// casting view
        tvResult = v.findViewById(R.id.tv_result);
        btn_div.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_sum.setOnClickListener(this);

        return v;

    }

    @Override
    public void onClick(View view) {
        number1 = et_number1.getText().toString().trim();   // get value of edit text
        number2 = et_number2.getText().toString().trim();   // get value of edit text 2

        switch (view.getId()) {
            case R.id.btn_addition:
                if (!number1.isEmpty() && !number2.isEmpty()) {
                    valueOfNumber1 = Double.parseDouble(number1);      // parse value to integer


                    valueOfNumber2 = Double.parseDouble(number2);   // parse value to integer


                    result = valueOfNumber1 + valueOfNumber2;
                    // make an operation
                    tvResult.setText("Result is : " + result);
                } else {
                    Toast.makeText(getContext(), "Please Enter Values", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.btn_sub:

                if (!et_number1.equals("") && !et_number2.equals("")) {
                    number1 = et_number1.getText().toString().trim();   // get value of edit text
                    valueOfNumber1 = Double.parseDouble(number1);      // parse value to integer


                    number2 = et_number2.getText().toString().trim();   // get value of edit text 2
                    valueOfNumber2 = Double.parseDouble(number2);   // parse value to integer


                    result = valueOfNumber1 - valueOfNumber2;
                    // make an operation
                    tvResult.setText("Result is : " + result);
                } else {
                    Toast.makeText(getContext(), "Please Enter Values", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_multiplication:
                if (!et_number1.equals("") && !et_number2.equals("")) {
                    number1 = et_number1.getText().toString().trim();   // get value of edit text
                    valueOfNumber1 = Double.parseDouble(number1);      // parse value to integer


                    number2 = et_number2.getText().toString().trim();   // get value of edit text 2
                    valueOfNumber2 = Double.parseDouble(number2);   // parse value to integer


                    result = valueOfNumber1 * valueOfNumber2;
                    // make an operation
                    tvResult.setText("Result is : " + result);
                } else {
                    Toast.makeText(getContext(), "Please Enter Values", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_division:
                if (!et_number1.equals("") && !et_number2.equals("")) {
                    number1 = et_number1.getText().toString().trim();   // get value of edit text
                    valueOfNumber1 = Double.parseDouble(number1);      // parse value to integer


                    number2 = et_number2.getText().toString().trim();   // get value of edit text 2
                    valueOfNumber2 = Double.parseDouble(number2);   // parse value to integer


                    result = valueOfNumber1 / valueOfNumber2;
                    // make an operation
                    tvResult.setText("Result is : " + result);
                } else {
                    Toast.makeText(getContext(), "Please Enter Values", Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }
}
