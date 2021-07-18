package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Activity3 extends AppCompatActivity {
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);

        Button next = (Button) findViewById(R.id.button2);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Activity_specialist.class);
                startActivityForResult(myIntent, 0);
            }

        });

        Button patient = (Button) findViewById(R.id.button4);
        patient.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                Intent myIntent2 = new Intent(view.getContext(),Activity_patient.class);
                startActivityForResult(myIntent2,0);
            }
        });

    }
}