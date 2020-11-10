package com.yat.helloworld.entertainment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yat.helloworld.R;

public class Restaurant extends Fragment implements View.OnClickListener {

    CheckBox chkPizza, chkBurger, chkChicken, chkVegetables, chkTea, chkPepsi, chkCoffee, chkAnise;
    Button btnOrderNow;
    TextView tvInvoice;
    int invoice = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_restaurant, container, false);

        chkAnise = v.findViewById(R.id.chk_anise);
        chkTea = v.findViewById(R.id.chk_tea);
        chkCoffee = v.findViewById(R.id.chk_coffe);
        chkPepsi = v.findViewById(R.id.chk_pepsi);
        chkPizza = v.findViewById(R.id.chk_pizza);
        chkBurger = v.findViewById(R.id.chk_burger);
        chkChicken = v.findViewById(R.id.chk_chicken);
        chkVegetables = v.findViewById(R.id.chk_vegetables);
        btnOrderNow = v.findViewById(R.id.btn_orderNow);
        btnOrderNow.setOnClickListener(this);

        tvInvoice = v.findViewById(R.id.tv_invoice);


        return v;

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_orderNow:

                StringBuilder totalOrder = new StringBuilder();

                if (chkVegetables.isChecked()) {
                    totalOrder.append(chkVegetables.getText().toString() + ", ");
                    invoice += 30;
                }

                if (chkChicken.isChecked()) {
                    totalOrder.append(chkChicken.getText().toString() + ", ");
                    invoice += 50;
                }

                if (chkBurger.isChecked()) {
                    totalOrder.append(chkBurger.getText().toString() + ", ");
                    invoice += 60;
                }

                if (chkPizza.isChecked()) {
                    totalOrder.append(chkPizza.getText().toString() + ", ");
                    invoice += 80;
                }

                if (chkPepsi.isChecked()) {
                    totalOrder.append(chkPepsi.getText().toString() + ", ");
                    invoice += 8;
                }

                if (chkTea.isChecked()) {
                    totalOrder.append(chkTea.getText().toString() + ", ");
                    invoice += 5;
                }

                if (chkAnise.isChecked()) {
                    totalOrder.append(chkAnise.getText().toString() + ", ");
                    invoice += 3;
                }

                if (chkCoffee.isChecked()) {
                    totalOrder.append(chkCoffee.getText().toString() + ",");
                    invoice += 6;
                }

                tvInvoice.setText("Your total order is " + totalOrder + " and Your invoice is " + " \" " + invoice + " $\" ");
                invoice = 0;

                break;
        }
    }
}