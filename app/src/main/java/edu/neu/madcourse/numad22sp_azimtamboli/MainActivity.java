package edu.neu.madcourse.numad22sp_azimtamboli;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //displayAboutMe();
                aboutMeNewActivity();
            }
        });

        button = (Button) findViewById(R.id.nextPage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });

        button = (Button) findViewById(R.id.link_collector);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openItemActivity();
            }
        });


        button = findViewById(R.id.locator);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLocator();
            }
        });



    }

    private void openLocator() {
        Intent intent = new Intent(this, LocatorActivity.class);
        startActivity(intent);
    }

    private void openItemActivity() {
        Intent intent = new Intent(this, ItemActivity.class);
        startActivity(intent);
    }

    private void openActivity2() {
        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent);
    }

    private void aboutMeNewActivity() {
        Intent intent = new Intent(this, AboutMeActivity.class);
        startActivity(intent);
    }

    public void displayAboutMe() {

        Context context = getApplicationContext();
        CharSequence text = "Name: Azim Tamboli\nEmail: tamboli.a@northeastern.edu";
        int duration = Toast.LENGTH_SHORT;



        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}