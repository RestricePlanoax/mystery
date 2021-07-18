package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class patient_failure extends AppCompatActivity{
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_failed);

        Button back_to_login = (Button) findViewById(R.id.button6);
        back_to_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent failed_intent = new Intent(view.getContext(),Activity_patient.class);
                startActivity(failed_intent);
            }
        });
    }
}
