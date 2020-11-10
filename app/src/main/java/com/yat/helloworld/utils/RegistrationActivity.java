package com.yat.helloworld.utils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yat.helloworld.R;
import com.yat.helloworld.ui.DrawerActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

//    Spinner spinnerCountry;
//    String[] countries = {"Egypt", "Saudi Arabia", "UAE", "Algeria", "Tunisia", "Morocco"};
    EditText etFirstName, etLastName, etEmailAddress, etPhoneNumber, etPassword, etConfirmPassword;
    String firstName, lastName, emailAddress, phoneNumber, password, confirmPassword;

    DatabaseReference databaseReference;
    FirebaseAuth accountAuthentication;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
//        spinnerCountry = findViewById(R.id.spinner_country);
        etFirstName = findViewById(R.id.et_FirstName);
        etLastName = findViewById(R.id.et_LastName);
        etEmailAddress = findViewById(R.id.et_EmailAddress);
        etPhoneNumber = findViewById(R.id.et_PhoneNumber);
        etPassword = findViewById(R.id.et_NewPassword);
        etConfirmPassword = findViewById(R.id.et_ConfirmPassword);
        accountAuthentication = FirebaseAuth.getInstance();


//        spinnerCountry.setOnItemSelectedListener(this);
//
//
//        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countries);
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerCountry.setAdapter(spinnerAdapter);

    }

    public void register(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Users");
        firstName = etFirstName.getText().toString();
        lastName = etLastName.getText().toString();
        emailAddress = etEmailAddress.getText().toString();
        phoneNumber = etPhoneNumber.getText().toString();
        password = etPassword.getText().toString();
        confirmPassword = etConfirmPassword.getText().toString();

        if (firstName.equals("")) {
            etFirstName.setError("Please enter first name");
        }
        if (lastName.equals("")) {
            etLastName.setError("Please enter last name");
        }

        if (isValid(emailAddress, password, confirmPassword, phoneNumber) && !firstName.equals("") && !lastName.equals("")) {
            accountAuthentication.createUserWithEmailAndPassword(emailAddress, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        user = task.getResult().getUser();

                        UserProfileChangeRequest ProfileChangeRequest = new UserProfileChangeRequest.Builder()
                                .setDisplayName(firstName)
                                .build();
                        user.updateProfile(ProfileChangeRequest);

                        DatabaseReference newUser = databaseReference.child(user.getUid());
                        newUser.child("firstName").setValue(firstName);
                        newUser.child("lastName").setValue(lastName);
                        newUser.child("emailAddress").setValue(emailAddress);
                        newUser.child("phoneNumber").setValue(phoneNumber);
                        newUser.child("password").setValue(password);

                        Intent goToMainActivity = new Intent(RegistrationActivity.this, DrawerActivity.class);
                        startActivity(goToMainActivity);
                        finish();
                        Toast.makeText(getApplicationContext(), "Registration Successfully", Toast.LENGTH_LONG).show();

                    } else {
                        if (task.getException().getMessage().equals("The email address is already in use by another account.")) {
                            Toast.makeText(getApplicationContext(), "This Email is already Taken ", Toast.LENGTH_SHORT).show();

                        }

                    }
                }
            });
        }
    }

    private boolean isValidPhone(String phone) {
        boolean valid = false;
        String PHONE_PATTERN = "^[+]?[0-9]{10,13}$";
        Pattern pattern = Pattern.compile(PHONE_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()) valid = true;
        return valid;
    }

    private boolean isValidPassword(String pass) {// validation for password
        boolean valid = false;
        String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(pass);
        if (matcher.matches()) valid = true;
        return valid;
    }

    private boolean isValidEmail(String email) {// validation for email
        boolean isValid = false;
        String EMAIL_PATTERN = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,255}$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) isValid = true;
        return isValid;
    }

    public boolean isValid(String str_email, String pass, String pass_conf, String phone) {
        boolean valid = true;
        if (!isValidEmail(str_email)) {
            etEmailAddress.setError("Enter valid Email");
            valid = false;
        }
        if (!pass.equals(pass_conf)) {
            etConfirmPassword.setError("Password not matched");
            valid = false;
        }
        if (!isValidPassword(pass)) {
            etPassword.setError("Use Numbers Upper and Lower case");
            valid = false;
        }
        if (!isValidPhone(phone)) {
            etPhoneNumber.setError("Enter valid phone number");
            valid = false;
        }

        return valid;
    }


//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//
//        Toast.makeText(this, countries[position], Toast.LENGTH_SHORT).show();
//
//
//    }

//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }


}