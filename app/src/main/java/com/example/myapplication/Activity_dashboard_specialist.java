package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Activity_dashboard_specialist extends AppCompatActivity {

    private Bundle savedInstanceState;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    private TextView specialist_name;

    private ListView lview;

    List<UserDetails> userList;

    ImageButton calender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_specialist);

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference().child("users");
        lview = (ListView)findViewById(R.id.lview);
        userList = new ArrayList<>();

        specialist_name = (TextView) findViewById(R.id.textView18);
        calender = (ImageButton) findViewById(R.id.imageButton8);
        //lview = findViewById(R.id.lview);
        Intent i1 = getIntent();
        String received_name = i1.getStringExtra("uname");

        specialist_name.setText("Hello "+received_name+"!");
        //ArrayList<String> l = new ArrayList<>();
        //ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.list_item,l);
        //lview.setAdapter(adapter);
        //we want to access users database !

        //databaseReference.addValueEventListener( new ValueEventListener(){
          //  @Override
           // public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            ////{
                     //  l.clear();
                       //for(DataSnapshot snapshot:dataSnapshot.getChildren())
                       //{
                         //  snapshot.getChildren();
                       //}
            //}
        //}
        String canceled = "y";
        Button cancel = (Button) findViewById(R.id.button11);
        cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Activity_specialist.class);
                myIntent.putExtra("cleared",canceled);
                startActivityForResult(myIntent, 0);
            }

        });
        //ImageButton ib = (ImageButton) findViewById(R.id.imageButton3);
        //ib.setOnClickListener(new View.OnClickListener() {
          //  @Override
           // public void onClick(View v) {
                //Toast.makeText(TravelBite.this, "test", Toast.LENGTH_SHORT).show();
             //   Intent i2 = new Intent(v.getContext(),Activity_calendar.class);
              //  startActivity(i2);

            //}
        //});
        calender.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent i = new Intent(view.getContext(),Activity_calendar_appointment.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
              Intent present = getIntent();
              userList.clear();
              if(present.getStringExtra("cleared")!=null)
                userList.clear();
               else {
                   int i =0;
                  for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                      i++;
                      UserDetails u = userSnapshot.getValue(UserDetails.class);
                      userList.add(u);
                      if(i==2)break;

                  }
              }
                 UserList adapter = new UserList(Activity_dashboard_specialist.this,userList);
                 lview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
 //error handling ! lite !
            }
        });
    }
}

