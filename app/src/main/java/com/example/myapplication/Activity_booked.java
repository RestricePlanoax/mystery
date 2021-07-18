package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class Activity_booked extends AppCompatActivity {
    private Bundle savedInstanceState;
    String username = null;
    String timing = null;
    TextView text1,text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked);
           text1 = (TextView)findViewById(R.id.textView41);
           text2 = (TextView)findViewById(R.id.textView43);
             Intent i3 = getIntent();
             username = i3.getStringExtra("uname");
             timing = i3.getStringExtra("Timing");
             text1.setText(username);
             text2.setText(timing);
    }
}
