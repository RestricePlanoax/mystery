package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;

public class Activity_calendar extends Activity {
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

    }
}

