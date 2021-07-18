package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Activity_calendar_appointment extends AppCompatActivity implements View.OnClickListener {
    private Bundle savedInstanceState;
    ArrayList<String> l = new ArrayList<String>(3);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_appointments);
        Button one = (Button) findViewById(R.id.btn_day1);
        // one.setOnClickListener(this);
        l.add("one");
        l.add("two");
        l.add("three");

        Button two = (Button) findViewById(R.id.btn_day2);
        // two.setOnClickListener(this);
        Button three = (Button) findViewById(R.id.btn_day3);
        //three.setOnClickListener(this);
        Button four = (Button) findViewById(R.id.btn_day4);
        //four.setOnClickListener(this);
        TextView btn_one = (TextView) findViewById(R.id.btn_one);
        TextView btn_two = (TextView)findViewById(R.id.btn_two);
        TextView btn_three = (TextView) findViewById(R.id.btn_three);


        one.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                  // int rndm_btn_no= generateRandom();
                    one.setBackgroundColor(Color.argb(100, 0, 137, 255));
                    two.setBackgroundColor(Color.argb(100, 0, 176, 255));
                    three.setBackgroundColor(Color.argb(100, 0, 176, 255));
                    four.setBackgroundColor(Color.argb(100, 0, 176, 255));
                    btn_one.setBackgroundColor(Color.RED);
                    btn_two.setBackgroundColor(Color.RED);
                    btn_three.setBackgroundColor(Color.GREEN);

                }
                return false;

            }
        });
        two.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    two.setBackgroundColor(Color.argb(100, 0, 137, 255));
                    one.setBackgroundColor(Color.argb(100, 0, 176, 255));
                    three.setBackgroundColor(Color.argb(100, 0, 176, 255));
                    four.setBackgroundColor(Color.argb(100, 0, 176, 255));
                    btn_one.setBackgroundColor(Color.GREEN);
                    btn_two.setBackgroundColor(Color.RED);
                    btn_three.setBackgroundColor(Color.RED);
                }
                return false;

            }
        });
        three.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    three.setBackgroundColor(Color.argb(100, 0, 137, 255));
                    one.setBackgroundColor(Color.argb(100, 0, 176, 255));
                    two.setBackgroundColor(Color.argb(100, 0, 176, 255));
                    four.setBackgroundColor(Color.argb(100, 0, 176, 255));
                    btn_one.setBackgroundColor(Color.RED);
                    btn_two.setBackgroundColor(Color.GREEN);
                    btn_three.setBackgroundColor(Color.RED);
                }
                return false;

            }
        });
        four.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    four.setBackgroundColor(Color.argb(100, 0, 137, 255));
                    one.setBackgroundColor(Color.argb(100, 0, 176, 255));
                    three.setBackgroundColor(Color.argb(100, 0, 176, 255));
                    two.setBackgroundColor(Color.argb(100, 0, 176, 255));
                    btn_one.setBackgroundColor(Color.RED);
                    btn_two.setBackgroundColor(Color.RED);
                    btn_three.setBackgroundColor(Color.GREEN);
                }
                return false;

            }
        });


    }

    private int generateRandom() {
        int min = 1;
        int max=3;
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        return random_int;
    }

    @Override
    public void onClick(View view) {

    }
}
