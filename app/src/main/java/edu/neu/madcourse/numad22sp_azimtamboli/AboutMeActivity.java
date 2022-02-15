package edu.neu.madcourse.numad22sp_azimtamboli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AboutMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        TextView name = (TextView)  findViewById(R.id.name);
        TextView email = (TextView)  findViewById(R.id.email);
        name.setText("Name : Azim Tamboli");
        email.setText("Email : tamboli.a@northeastern.edu");
    }
}