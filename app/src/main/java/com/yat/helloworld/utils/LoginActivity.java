package com.yat.helloworld.utils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.yat.helloworld.R;
import com.yat.helloworld.ui.DrawerActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    Button zorar_login;
    EditText etUsername, etPassword;
    String username, password;
    TextView tvForgetPassword, tvCreateNewAccount;
    AlertDialog.Builder buildAlertDialog;
    CheckBox chkRememberMe;
    SharedPreferences saveLoginDetails;
    boolean doubleBackToExitPressedOnce = false;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser currentUser;
    FirebaseAuth.AuthStateListener stateListener;


    private SignInButton mSignInButton;
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);//        getSupportActionBar().hide();

        //==================================================
        mSignInButton = (SignInButton) findViewById(R.id.sign_in_button);
        mFirebaseAuth = FirebaseAuth.getInstance();
        zorar_login = findViewById(R.id.btn_login); // give the object an ID
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        tvForgetPassword = findViewById(R.id.tv_forgetPassword);
        tvCreateNewAccount = findViewById(R.id.tv_createNewAccount);
        chkRememberMe = findViewById(R.id.chk_rememberMe);
        tvCreateNewAccount.setPaintFlags(tvCreateNewAccount.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvForgetPassword.setPaintFlags(tvForgetPassword.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        buildAlertDialog = new AlertDialog.Builder(this);
        saveLoginDetails = getSharedPreferences("rememberMe", 0);
        if (saveLoginDetails.getBoolean("bool", false)) {
            String emailAddress = saveLoginDetails.getString("email", "");
            etUsername.setText(emailAddress);
            String password = saveLoginDetails.getString("password", "");
            etPassword.setText(password);

            chkRememberMe.setChecked(true);
        }
        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToForgetPassword = new Intent(LoginActivity.this, ForgetPassword.class);
                startActivity(goToForgetPassword);
            }
        });


        zorar_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                username = etUsername.getText().toString().trim();
                password = etPassword.getText().toString().trim();

                if (!username.equals("") && !password.equals("")) {
                    
                    mFirebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent goToMainActivity = new Intent(LoginActivity.this, DrawerActivity.class);
                                startActivity(goToMainActivity);
                                Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else {
                                Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                        
                    });

                   

                } else if (!username.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_LONG).show();

                } else if (!password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter username", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(LoginActivity.this, "Please enter username and password", Toast.LENGTH_LONG).show();
                }
            }
        });
        mSignInButton.setOnClickListener(this);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mSignInClient = GoogleSignIn.getClient(this, gso);

        currentUser = mFirebaseAuth.getCurrentUser();
        stateListener = firebaseAuth -> {
            if (currentUser!=null){
                Intent goToMainActivity = new Intent(getApplicationContext(), DrawerActivity.class);
                startActivity(goToMainActivity);
                finish();
            }else {

            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(stateListener);
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {  //variable false
            super.onBackPressed();  //call default behaviour
            return;
        }

        this.doubleBackToExitPressedOnce = true;  // change value to true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false; // return the value to false again
            }
        }, 4000);  // wait 4 seconds

    }

    public void createNewAccount(View view) {
        Intent goToRegisterActivity = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(goToRegisterActivity);

    }

    public void rememberMe(View view) {
        if (chkRememberMe.isChecked()) {

            saveLoginDetails = getSharedPreferences("rememberMe", 0);
            SharedPreferences.Editor marker = saveLoginDetails.edit();
            marker.putString("email", etUsername.getText().toString());
            marker.putString("password", etPassword.getText().toString());
            marker.putBoolean("bool", true);
            marker.apply();
            Toast.makeText(this, "Your email and password saved", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }

    private void signIn() {
        Intent signInIntent = mSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent in signIn()
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(LoginActivity.this, DrawerActivity.class));
                            finish();
                        }
                    }
                });
    }
}


//
//  buildAlertDialog.setMessage(R.string.dialog_message)
//          .setCancelable(false)
//          .setIcon(R.drawable.multiapplogo)
//          .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialogInterface, int i) {
//        finish();
//        Toast.makeText(LoginActivity.this, "Good Bye", Toast.LENGTH_SHORT).show();
//        }
//        })
//        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialogInterface, int i) {
//        dialogInterface.cancel();
//        Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
//
//        }
//        });
//
//        AlertDialog alertDialog = buildAlertDialog.create();
//        alertDialog.setTitle(R.string.dialog_title);
//        alertDialog.show();