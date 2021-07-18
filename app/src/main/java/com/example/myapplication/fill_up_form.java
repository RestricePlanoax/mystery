package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class fill_up_form extends AppCompatActivity {
    private Bundle savedInstanceState;

    Button submit;
    String selected_gender = null;
    String age_group = null;
    String covid_vac = null;
    String pre_existing = null;
    String username = null;
    private TextView patient_name;

    private  DatabaseReference databaseUsers;

    RadioGroup rg1, rg2, rg3, rg4;
    RadioButton r1, r2, r3, r4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_fill);

        databaseUsers = FirebaseDatabase.getInstance().getReference();
        //databaseUsers.child("trials").setValue("Yo");
        Intent i_p = getIntent();

        username = i_p.getStringExtra("uname");
        patient_name = (TextView) findViewById(R.id.textView40);
        patient_name.setText("Hey" + " " + username + "!");
        rg1 = (RadioGroup) findViewById(R.id.radio_group_gender);
        rg2 = (RadioGroup) findViewById(R.id.radio_group_age);
        rg3 = (RadioGroup) findViewById(R.id.radio_group_covid);
        rg4 = (RadioGroup) findViewById(R.id.radio_group_pre);
        // radio groups  - 4
        /*
        RadioGroup r1 = (RadioGroup)findViewById(R.id.radio_group_gender);
        r1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb1 =(RadioButton)findViewById(i);
                selected_gender=(String)rb1.getText();
                //adding to the database !
            }
        });
        RadioGroup r2 = (RadioGroup)findViewById(R.id.radio_group_age);
        r2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb2 =(RadioButton)findViewById(i);
                age_group=(String)rb2.getText();
                //adding to the database !
            }
        });
        RadioGroup r3 = (RadioGroup)findViewById(R.id.radio_group_covid);
        r3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb3 =(RadioButton)findViewById(i);
                covid_vac=(String)rb3.getText();
                //adding to the database !
            }
        });
        RadioGroup r4 = (RadioGroup)findViewById(R.id.radio_group_gender);
        r4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb4 =(RadioButton)findViewById(i);
                pre_existing=(String)rb4.getText();
                //adding to the database !
            }
        }); */

        submit = (Button) findViewById(R.id.button7);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDetails();
                Intent i1 = new Intent(view.getContext(), Finding_doc_home.class);
                i1.putExtra("pname",username);
                startActivity(i1);
            }
        });


    }

    public void checkButton1(View v) {
        int radioId = rg1.getCheckedRadioButtonId();
        r1 = findViewById(radioId);
        selected_gender = r1.getText().toString();
    }

    public void checkButton2(View v) {
        int radioId2 = rg2.getCheckedRadioButtonId();
        r2 = findViewById(radioId2);
        age_group = r2.getText().toString();
    }

    public void checkButton3(View v) {
        int radioId3 = rg3.getCheckedRadioButtonId();
        r3 = findViewById(radioId3);
        covid_vac = r3.getText().toString();
    }

    public void checkButton4(View v) {
        int radioId4 = rg4.getCheckedRadioButtonId();
        r4 = findViewById(radioId4);
        pre_existing = r4.getText().toString();
    }

    private void addDetails() {
        if (selected_gender.isEmpty()) {

            Toast.makeText(this, "You should Choose your Gender!", Toast.LENGTH_LONG).show();
            return;
        }
        if (age_group.isEmpty()) {
            Toast.makeText(this, "You should choose your age group!", Toast.LENGTH_LONG).show();
            return;
        }
        if (covid_vac.isEmpty()) {
            Toast.makeText(this, "You should specify if you have taken covid vaccine or not!", Toast.LENGTH_LONG).show();
            return;
        }
        if (pre_existing.isEmpty()) {
            Toast.makeText(this, "You should specify if you have a pre existing condition or not!", Toast.LENGTH_LONG).show();
            return;
        }
        //no errors ! all perfect :)
        String user_id = databaseUsers.push().getKey();
            System.out.println(username);
            System.out.println(selected_gender);
            System.out.println(age_group);
            System.out.println(covid_vac);
            System.out.println(pre_existing);

        System.out.println("Pushed!");
        UserDetails user_det = new UserDetails(user_id, username, selected_gender, age_group, covid_vac, pre_existing);

        databaseUsers.child("users").child(user_id).setValue(user_det);
        Toast.makeText(this, "User details saved!", Toast.LENGTH_LONG).show();


    }

}