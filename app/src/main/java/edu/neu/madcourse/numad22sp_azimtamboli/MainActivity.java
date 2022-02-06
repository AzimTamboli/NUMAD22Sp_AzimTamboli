package edu.neu.madcourse.numad22sp_azimtamboli;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
                openActivity2();
            }
        });
    }

    private void openActivity2() {
        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent);
    }

    public void displayAboutMe(View view) {

        Context context = getApplicationContext();
        CharSequence text = "Name: Azim Tamboli\nEmail: tamboli.a@northeastern.edu";
        int duration = Toast.LENGTH_SHORT;



        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}