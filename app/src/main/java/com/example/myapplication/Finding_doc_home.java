package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Finding_doc_home extends AppCompatActivity {
    private Bundle savedInstanceState;
    String username = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_doc_home);
        Intent  temp = getIntent();
        username = temp.getStringExtra("pname");

        Button choosing_specialist = (Button) findViewById(R.id.button10);
        choosing_specialist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), patient_dashboard.class);
                myIntent.putExtra("pname",username);
                startActivityForResult(myIntent, 0);
            }

        });
    }
}
