package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

public class patient_dashboard extends AppCompatActivity implements View.OnClickListener {
    private Bundle savedInstanceState;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<String> speciaList;
    Button one,two,three,four,five,six,seven,eight;
    String username = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_dashboard);
        System.out.println("Oncreate :)");
        firebaseDatabase = FirebaseDatabase.getInstance();
        Intent t4 = getIntent();
        username = t4.getStringExtra("pname");

        //databaseReference = firebaseDatabase.getReference("Specialists");
            Button one = (Button) findViewById(R.id.button_50);
        Button two = (Button) findViewById(R.id.button_51);
        Button three = (Button) findViewById(R.id.button_52);
        Button four = (Button) findViewById(R.id.button_53);
        Button five = (Button) findViewById(R.id.button_54);
        Button six = (Button) findViewById(R.id.button_55);
        Button seven = (Button) findViewById(R.id.button_56);
        Button eight = (Button) findViewById(R.id.button_57);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);



       //get the data from database !
        speciaList = getData();



        //speciaList -> list of  specialists !





    }
    private void goToDashboard(int i,String profession,View v) {
        System.out.println("HI");
        Intent intent =  new Intent(v.getContext(),Patient_then_specialist.class);
        String name  = speciaList.get(i);
        System.out.println("Lets go !");

        intent.putExtra("Specialist Name",name);
        intent.putExtra("Profession",profession);
        intent.putExtra("pname",username);
        startActivity(intent);
    }
    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.button_50:
                System.out.println("Bution clicked");
                goToDashboard(0,"Neurologist",view);
                break;
            case R.id.button_51:
                System.out.println("Bution clicked");
                goToDashboard(1,"Opthalmologist",view);
                break;
            case R.id.button_52:
                System.out.println("Bution clicked");
                goToDashboard(2,"Pulmonologist",view);
                break;
            case R.id.button_53:
                System.out.println("Bution clicked");
                goToDashboard(3,"Cardiologist",view);
                break;
            case R.id.button_54:
                System.out.println("Bution clicked");
                goToDashboard(4,"Dentist",view);
                break;
            case R.id.button_55:
                System.out.println("Bution clicked");
                goToDashboard(5,"ENT Specialist",view);
                break;
            case R.id.button_56:
                System.out.println("Bution clicked");
                goToDashboard(6,"Hepatologist",view);
                break;
            case R.id.button_57:
                System.out.println("Bution clicked");
                goToDashboard(7,"Nephrologist",view);
                break;
            case R.id.button_58:
                System.out.println("Bution clicked");
                goToDashboard(8,"Pancreatagist",view);
                break;
            default:
                break;

        }
    }

    private ArrayList<String> getData()
    {
        ArrayList<String> toReturnList = new ArrayList<>();
        ArrayList<String> temp = getFilled();


        ArrayList<String> finalToReturnList = toReturnList;
       /* databaseReference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                 for(DataSnapshot dataSnapshot:snapshot.getChildren())
                 {
                     specialistDetails s = dataSnapshot.getValue(specialistDetails.class);
                     finalToReturnList.add(s.toString());

                 }
             }

             @Override
             public void onCancelled(@NonNull @NotNull DatabaseError error) {
                 Toast.makeText(patient_dashboard.this,"Failed to retrieve data from database",Toast.LENGTH_SHORT).show();

             }
         });*/
         toReturnList = temp;
         return (ArrayList<String>) toReturnList;
    }

    private ArrayList<String> getFilled() {
        //Dr.Ananth Krishna
        //Dr. Arun desai
        //Dr. Radhan shetty
         //Dr Srikant Mohapatra
        //Dr Pavan Kumar
        //Dr Nikhil
        //Dr Wasim
        //Dr Anil
        ArrayList<String> temp  = new ArrayList<>();
        temp.add("Ananth Krishna");
        temp.add("Arun Desai");
        temp.add("Radhan Shetty");
        temp.add("Srikant Mohapatra");
        temp.add("Pavan Kumar");
        temp.add("Nikhil");
        temp.add("Wasim");
        temp.add("Anil");
        temp.add("Naveen Kanodia");

   return temp;
    }




}


