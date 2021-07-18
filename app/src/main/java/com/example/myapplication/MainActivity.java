package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity {

      private FirebaseAuth mAuth;
      @Override
      protected void onStart()
      {
          super.onStart();
          FirebaseUser u1 = mAuth.getCurrentUser();
          if(u1!=null)
          {
              mAuth.signOut();
          }
      }

      public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        Button next = (Button) findViewById(R.id.button3);
        next.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Activity2.class);
                startActivityForResult(myIntent, 0);
            }

        });
    }
}