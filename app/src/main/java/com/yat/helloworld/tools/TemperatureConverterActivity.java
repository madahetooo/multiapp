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

public class TemperatureConverterActivity extends Fragment implements View.OnClickListener {

    EditText etCelsius, etFahrenheit;
    Button btnConvert, resetValues;
    TextView tvDegreeResult;

    String etValueStringCelsius, etValueStringFahrenheit, convertedValue;
    double etValueDoubleCelsius, etValueDoubleFahrenheit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_temperature_converter, container, false);

        etCelsius = v.findViewById(R.id.et_celsius);
        etFahrenheit = v.findViewById(R.id.et_fahrenheit);
        btnConvert = v.findViewById(R.id.btn_convert);
        resetValues = v.findViewById(R.id.btn_reset);
        btnConvert.setOnClickListener(this);
        resetValues.setOnClickListener(this);


        return v;

    }


    @Override
    public void onClick(View view) {
        etValueStringCelsius = etCelsius.getText().toString().trim(); //get value of edit text

        etValueStringFahrenheit = etFahrenheit.getText().toString().trim(); //get value of edit text

        switch (view.getId()) {
            case R.id.btn_reset:
                if (etValueStringCelsius.isEmpty() && etValueStringFahrenheit.isEmpty()){
                    Toast.makeText(getContext(), "There is no value to reset", Toast.LENGTH_SHORT).show();
                }
                else {
                    etCelsius.setText("");
                    etFahrenheit.setText("");
                }

                break;

            case R.id.btn_convert:

                if (!etValueStringCelsius.isEmpty() && etValueStringFahrenheit.isEmpty()) {
                    etValueDoubleCelsius = Double.parseDouble(etValueStringCelsius); //convert value of edit text to double

                    convertedValue = String.valueOf(etValueDoubleCelsius * 1.8 + 32);
                    etFahrenheit.setText(convertedValue);


                } else if (etValueStringCelsius.isEmpty() && !etValueStringFahrenheit.isEmpty()) {
                    etValueDoubleFahrenheit = Double.parseDouble(etValueStringFahrenheit); //convert value of edit text to double
                    convertedValue = String.valueOf(etValueDoubleFahrenheit - 32 / 1.8);
                    etCelsius.setText(convertedValue);
                } else if (!etValueStringCelsius.isEmpty() && !etValueStringFahrenheit.isEmpty()) {
                    etCelsius.setText("");
                    etFahrenheit.setText("");
                } else {
                    Toast.makeText(getContext(), "Please Enter value", Toast.LENGTH_SHORT).show();

                }
                break;

        }


    }
}
