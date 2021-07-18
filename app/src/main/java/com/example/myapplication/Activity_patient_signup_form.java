package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Activity_patient_signup_form extends AppCompatActivity implements View.OnClickListener{
    private Bundle savedInstanceState;

    EditText editTextPhone2,editTextTextPassword,editTextTextEmailAddress2,editTextTextPersonName2;

    //editTextEmailAddress2 = (EditText)findViewById(R.id.editTextTextEmailAddress2);

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_signup);
        editTextTextEmailAddress2 = (EditText)findViewById(R.id.editTextTextEmailAddress2);
        editTextPhone2 = (EditText)findViewById(R.id.editTextPhone2);
        editTextTextPassword  = (EditText)findViewById(R.id.editTextTextPassword);
        editTextTextPersonName2 = (EditText)findViewById(R.id.editTextTextPersonName2);
        mAuth = FirebaseAuth.getInstance();
       findViewById(R.id.button5).setOnClickListener(this);
      }
    private void registerUser() {
        String username = editTextTextPersonName2.getText().toString().trim();
        String email = editTextTextEmailAddress2.getText().toString().trim();
        String password = editTextTextPassword.getText().toString().trim();
        String phonenum = editTextPhone2.getText().toString().trim();
        if(email.isEmpty())
        {
            editTextTextEmailAddress2.setError("EMAIL REQUIRED!");
            editTextTextEmailAddress2.requestFocus();
             return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editTextTextEmailAddress2.setError("Please Enter a valid email");
            editTextTextEmailAddress2.requestFocus();
            return;
        }
        if(username.isEmpty())
        {
            editTextTextPersonName2.setError("USERNAME REQUIRED!");
            editTextTextPersonName2.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            editTextTextPassword.setError("PASSWORD REQUIRED!");
            editTextTextPassword.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            editTextTextPassword.setError("Minimum length of the password should be 6");
            editTextTextPassword.requestFocus();
            return;
        }
        if(phonenum.isEmpty())
        {
            editTextPhone2.setError("PHONE NUMBER IS REQUIRED!");
            editTextPhone2.requestFocus();
            return;
        }
       //now ready for user registration !
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    Toast.makeText(getApplicationContext(),"User Registeration Succesfull :)",Toast.LENGTH_SHORT).show();
                    Intent succ = new Intent(Activity_patient_signup_form.this, GetOtp.class);
                    succ.putExtra("uname",username);
                    succ.putExtra("logged","n");
                    succ.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(succ);
                }
                else
                {
                    //if registered already
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(),"You are already registered!",Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }

        );


    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.button5:
                registerUser();
                break;
        }
    }


}
