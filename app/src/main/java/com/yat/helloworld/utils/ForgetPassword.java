package com.yat.helloworld.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yat.helloworld.R;

public class ForgetPassword extends AppCompatActivity {

    EditText etRecoveryMail;
    Button btnSendCode;
    String recoveryMail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        etRecoveryMail = findViewById(R.id.et_recoveryMail);
        btnSendCode = findViewById(R.id.btn_sendCode);

        btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recoveryMail = etRecoveryMail.getText().toString().trim();
                if (!recoveryMail.equals("")){
                    Toast.makeText(ForgetPassword.this, "We send the code to : "+recoveryMail, Toast.LENGTH_LONG).show();
                    Intent goToLoginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(goToLoginActivity);
                    finish();
                }
                else {
                    Toast.makeText(ForgetPassword.this, "Please enter your recovery Mail", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}