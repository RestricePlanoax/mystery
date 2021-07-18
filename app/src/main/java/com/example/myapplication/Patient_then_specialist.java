package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Patient_then_specialist extends AppCompatActivity {
    private Bundle savedInstanceState;
    private TextView specialist_name;
    private TextView profession_name;
    String username = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_specialist);
        specialist_name = (TextView) findViewById(R.id.textView24);
        profession_name = (TextView) findViewById(R.id.textView30);

        Intent i7 = getIntent();
        username = i7.getStringExtra("pname");
        String received_name = i7.getStringExtra("Specialist Name");
        String received_profession = i7.getStringExtra("Profession");
        specialist_name.setText("Dr." + received_name);
        profession_name.setText(received_profession);

        Button booking = (Button) findViewById(R.id.button13);
        booking.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(),Activity_booking_calendar.class);
                myIntent.putExtra("name",received_name);
                myIntent.putExtra("pname",username);
                startActivityForResult(myIntent, 0);
            }

        });

        



    }
}
