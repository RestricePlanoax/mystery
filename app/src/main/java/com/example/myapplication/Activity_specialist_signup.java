package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class Activity_specialist_signup extends AppCompatActivity{
    private Bundle savedInstanceState;
    Button submit;

    DatabaseReference databaseSpecialists;
    private FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState) {


        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specialist_form_fill);
        databaseSpecialists = FirebaseDatabase.getInstance().getReference("Specialists");
        EditText editTextSpecialistName2, editTextSpecialistEmailAddress2, editTextSpecialistPassword, editTextSpecialistPhone2;
        editTextSpecialistEmailAddress2 = (EditText) findViewById(R.id.editTextSpecialistEmailAddress2);
        editTextSpecialistPhone2 = (EditText) findViewById(R.id.editTextSpecialistPhone2);
        editTextSpecialistPassword = (EditText) findViewById(R.id.editTextSpecialistPassword);
        editTextSpecialistName2 = (EditText) findViewById(R.id.editTextSepcialistName2);
        mAuth = FirebaseAuth.getInstance();

        Button specialist_signup = (Button) findViewById(R.id.button22);
        specialist_signup.setOnClickListener(new View.OnClickListener(){
        @Override
            public void onClick(View view)
        {
            this.specialist_signup();
        }
        private void specialist_signup()
        {
            String username = editTextSpecialistName2.getText().toString().trim();
            String email = editTextSpecialistEmailAddress2.getText().toString().trim();
            String password = editTextSpecialistPassword.getText().toString().trim();
            String phonenum = editTextSpecialistPhone2.getText().toString().trim();
            if (email.isEmpty()) {
                editTextSpecialistEmailAddress2.setError("EMAIL REQUIRED!");
                editTextSpecialistEmailAddress2.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                editTextSpecialistEmailAddress2.setError("Please Enter a valid email");
                editTextSpecialistEmailAddress2.requestFocus();
                return;
            }
            if (username.isEmpty()) {
                editTextSpecialistName2.setError("USERNAME REQUIRED!");
                editTextSpecialistName2.requestFocus();
                return;
            }
            if (password.isEmpty()) {
                editTextSpecialistPassword.setError("PASSWORD REQUIRED!");
                editTextSpecialistPassword.requestFocus();
                return;
            }
            if (password.length() < 6) {
                editTextSpecialistPassword.setError("Minimum length of the password should be 6");
                editTextSpecialistPassword.requestFocus();
                return;
            }
            if (phonenum.isEmpty()) {
                editTextSpecialistPhone2.setError("PHONE NUMBER IS REQUIRED!");
                editTextSpecialistPhone2.requestFocus();
                return;
            }
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //go back to login !
                        Intent succ1 = new Intent(Activity_specialist_signup.this, Activity_dashboard_specialist.class);
                        succ1.putExtra("uname",username);
                        Toast.makeText(getApplicationContext(),"Doc registered Successfully!",Toast.LENGTH_SHORT).show();
                        startActivity(succ1);
                    } else {

                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                    }
                }
            });
            //now add it to the collections of specilist details !
            String id = databaseSpecialists.push().getKey();
            specialistDetails specialists_det = new specialistDetails(username,email,phonenum,password);
            databaseSpecialists.child(id).setValue(specialists_det);



        }
    });


}
}
