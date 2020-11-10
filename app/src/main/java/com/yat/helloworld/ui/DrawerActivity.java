package com.yat.helloworld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.yat.helloworld.utils.LoginActivity;
import com.yat.helloworld.R;
import com.yat.helloworld.entertainment.Chat;
import com.yat.helloworld.entertainment.Connect3;
import com.yat.helloworld.entertainment.DogOrCatActivity;
import com.yat.helloworld.entertainment.MediaActivity;
import com.yat.helloworld.entertainment.MusicPlayer;
import com.yat.helloworld.entertainment.RandomHighestMountaints;
import com.yat.helloworld.entertainment.Restaurant;
import com.yat.helloworld.tools.CalculatorActivity;
import com.yat.helloworld.tools.JavaTpointWebActivity;
import com.yat.helloworld.tools.MapsActivity;
import com.yat.helloworld.tools.PDFActivity;
import com.yat.helloworld.tools.PassingBioData;
import com.yat.helloworld.tools.TemperatureConverterActivity;
import com.yat.helloworld.tools.WidgetsActivity;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    Toolbar toolbar;
    private FirebaseAuth mFirebaseAuth;
    boolean doubleBackToExitPressedOnce = false;
    DatabaseReference databaseReference;

    TextView userName, email;
    String googleUserName, userEmail, userPhotoString;
    FirebaseUser currentUser;
    ImageView userPhoto;
    private GoogleSignInClient mSignInClient;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        mFirebaseAuth = FirebaseAuth.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        View header = navigationView.getHeaderView(0);
        userPhoto = header.findViewById(R.id.userPhoto);
        userName =  header.findViewById(R.id.txUserName);
        email = header.findViewById(R.id.txEmail);
        if (currentUser != null) {
            googleUserName = currentUser.getDisplayName();
            userName.setText(googleUserName);
            userEmail = currentUser.getEmail();
            email.setText(userEmail);
            userPhotoString= String.valueOf(currentUser.getPhotoUrl());
            Picasso.with(this).load(userPhotoString).into(userPhoto);

        }
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mSignInClient = GoogleSignIn.getClient(this, gso);
        drawer = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Chat()).commit();
            navigationView.setCheckedItem(R.id.item_chat);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_calculator:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CalculatorActivity()).commit();
                break;
            case R.id.item_chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Chat()).commit();
                break;
            case R.id.item_passing_bio_data:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PassingBioData()).commit();
                break;
            case R.id.item_connect3:
                Intent intent = new Intent(getApplicationContext(), Connect3.class);
                startActivity(intent);
                break;
            case R.id.item_dog_or_cat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DogOrCatActivity()).commit();
                break;
            case R.id.item_media_app:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MediaActivity()).commit();
                break;
            case R.id.item_javaTpoint:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new JavaTpointWebActivity()).commit();
                break;
            case R.id.item_google_maps:
                Intent intent2 = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent2);
                break;
            case R.id.item_music_app:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MusicPlayer()).commit();
                break;
            case R.id.item_pdf_view:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PDFActivity()).commit();
                break;
            case R.id.item_random_highest_mountains:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new RandomHighestMountaints()).commit();
                break;
            case R.id.item_restaurant:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Restaurant()).commit();
                break;
            case R.id.item_temperature_converter:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TemperatureConverterActivity()).commit();
                break;
            case R.id.item_yat_group:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new WidgetsActivity()).commit();
                break;
            case R.id.log_out:

                mFirebaseAuth.signOut();
                mSignInClient.signOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
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


    }

}
