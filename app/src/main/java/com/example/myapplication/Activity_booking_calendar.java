package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.collection.LLRBNode;

public class Activity_booking_calendar extends AppCompatActivity {
    private Bundle savedInstanceState;

    boolean left = false;
    boolean right = false;
    boolean middle = false;
    String uname=null;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_calendar);
        Intent temp = getIntent();
        uname = temp.getStringExtra("pname");
         Button btn_left = (Button) findViewById(R.id.button_left);
         btn_left.setOnTouchListener(new View.OnTouchListener()
         {
             @Override
             public boolean onTouch(View view, MotionEvent event)
             {
                 if(event.getAction()==MotionEvent.ACTION_UP)
                 {
                     left = true;
                     btn_left.setBackgroundColor(Color.argb(100,0,137,255));
                 }
                 else if(event.getAction()==MotionEvent.ACTION_DOWN)
                 {
                     left = false;
                     btn_left.setBackgroundColor(Color.argb(100,0,175,255));
                 }
                 return false;
             }

         });
         Button btn_center = (Button) findViewById(R.id.button_center);
        btn_center.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent event)
            {
                if(event.getAction()==MotionEvent.ACTION_UP)
                {
                    middle = true;
                    btn_center.setBackgroundColor(Color.argb(100,0,137,255));
                }
                else if(event.getAction()==MotionEvent.ACTION_DOWN)
                {
                    middle = false;
                    btn_center.setBackgroundColor(Color.argb(100,0,175,255));
                }
                return false;
            }

        });
        Button btn_right = (Button) findViewById(R.id.button_right);
        btn_right.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent event)
            {
                if(event.getAction()==MotionEvent.ACTION_UP)
                {
                    right = true;
                    btn_right.setBackgroundColor(Color.argb(100,0,137,255));
                }
                else if(event.getAction()==MotionEvent.ACTION_DOWN)
                {
                    right = false;
                    btn_right.setBackgroundColor(Color.argb(100,0,175,255));
                }
                return false;
            }

        });
        Button close_all = (Button) findViewById(R.id.button_cancelall);
        close_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                left = false;
                right = false;
                middle = false;
                btn_left.setBackgroundColor(Color.argb(100,0,175,255));
                btn_right.setBackgroundColor(Color.argb(100,0,175,255));
                btn_center.setBackgroundColor(Color.argb(100,0,175,255));
            }
        });
       Button book_all = (Button) findViewById(R.id.button_bookall);
       book_all.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(left && middle && right)
               {
                   left = false;
                   right = false;
                   middle = false;
                   btn_left.setBackgroundColor(Color.argb(100,0,175,255));
                   btn_right.setBackgroundColor(Color.argb(100,0,175,255));
                   btn_center.setBackgroundColor(Color.argb(100,0,175,255));
                   Toast.makeText(getApplicationContext(),"Choose only one timing",Toast.LENGTH_SHORT).show();
                   return;
               }
               else if((left==true && right==true) || (left==true&&middle==true) || (middle==true && right==true))
               {
                   left = false;
                   middle = false;
                   right = false;
                   btn_left.setBackgroundColor(Color.argb(100,0,175,255));
                   btn_right.setBackgroundColor(Color.argb(100,0,175,255));
                   btn_center.setBackgroundColor(Color.argb(100,0,175,255));
                   Toast.makeText(getApplicationContext(),"Choose only one timing",Toast.LENGTH_SHORT).show();
                   return;
               }
               else
               {
                   //sucess
                   String timing = null;
                   if(left) timing = "12:00pm";
                   else if(middle) timing = "4:00pm";
                   else timing = "8:00pm";
                   Intent i1 = new Intent(Activity_booking_calendar.this,Activity_booked.class);
                   i1.putExtra("Timing",timing);
                   i1.putExtra("uname",uname);
                   startActivity(i1);

               }



           }
       });

}
}
