package edu.neu.madcourse.numad22sp_azimtamboli;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Button button = (Button) findViewById(R.id.buttonA);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.buttonB);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.buttonC);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.buttonD);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.buttonE);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.buttonF);
        button.setOnClickListener(this);

    }

    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    public void onClick(View view) {
        switch (view.getId()) {


            case R.id.buttonA:
                TextView t = (TextView)(findViewById(R.id.textView3));
                t.setText("Pressed A");
                break;

            case R.id.buttonB:
                t = (TextView)(findViewById(R.id.textView3));
                t.setText("Pressed B");
                break;

            case R.id.buttonC:
                t = (TextView) (findViewById(R.id.textView3));
                t.setText("Pressed C");
                break;

            case R.id.buttonD:
                t = (TextView)(findViewById(R.id.textView3));
                t.setText("Pressed D");
                break;

            case R.id.buttonE:
                t = (TextView)(findViewById(R.id.textView3));
                t.setText("Pressed E");
                break;

            case R.id.buttonF:
                t = (TextView)(findViewById(R.id.textView3));
                t.setText("Pressed F");
                break;

        }
    }
}