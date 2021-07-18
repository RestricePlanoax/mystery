package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

public class VerifyOtp extends AppCompatActivity {
    private Bundle savedInstanceState;
    EditText ip1, ip2, ip3, ip4, ip5, ip6;
    String phonenum = null;
    String backendotp = null;
    String username = null;
    //final Button verifyOtp = null;
    String logged = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifying_screen);
        phonenum = getIntent().getStringExtra("phonenum");
        Button verifyOtp = (Button) findViewById(R.id.otpverify);
        username = getIntent().getStringExtra("uname");
        logged = getIntent().getStringExtra("logged");

        ip1 = (EditText) findViewById(R.id.inputotp1);
        ip2 = (EditText) findViewById(R.id.inputotp2);
        ip3 = (EditText) findViewById(R.id.inputotp3);
        ip4 = (EditText) findViewById(R.id.inputotp4);
        ip5 = (EditText) findViewById(R.id.inputotp5);
        ip6 = (EditText) findViewById(R.id.inputotp6);

        TextView t =(TextView) findViewById(R.id.textView47);
        t.setText(String.format(
                "91-%s",getIntent().getStringExtra("phonenum")
        ));
        backendotp=getIntent().getStringExtra("backendotp");
        verifyOtp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if(!ip1.getText().toString().isEmpty() &&!ip2.getText().toString().isEmpty() && !ip3.getText().toString().isEmpty() && !ip4.getText().toString().isEmpty() && !ip5.getText().toString().isEmpty() && !ip6.getText().toString().isEmpty())
                {
                    String entercodeotp = ip1.getText().toString() +
                            ip2.getText().toString() +
                            ip3.getText().toString() +
                            ip4.getText().toString() +
                            ip5.getText().toString() +
                            ip6.getText().toString();
                    if(backendotp!=null)
                    {
                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(backendotp,entercodeotp);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(
                                new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                        if(task.isSuccessful()) {
                                            if (logged.equals("y")) {
                                                Intent intent = new Intent(getApplicationContext(), patient_dashboard.class);
                                                intent.putExtra("uname", username);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                            } else {
                                                Intent intent1 = new Intent(getApplicationContext(), fill_up_form.class);
                                                intent1.putExtra("uname", username);
                                                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent1);

                                            }
                                        }
                                        else
                                        {
                                            Toast.makeText(VerifyOtp.this,"Enter correct OTP",Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                }
                        );
                    }
                    else
                    {
                        Toast.makeText(VerifyOtp.this,"Check net!",Toast.LENGTH_SHORT);
                    }
                    Toast.makeText(VerifyOtp.this,"OTP verify.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(VerifyOtp.this,"Please enter all numbers",Toast.LENGTH_SHORT).show();
                }

            }

        });

        numberotpmove();




    }

    private void numberotpmove() {
        ip1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                  if(!charSequence.toString().trim().isEmpty())
                  {
                      ip2.requestFocus();
                  }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ip2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty())
                {
                    ip3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ip3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty())
                {
                    ip4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ip4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty())
                {
                    ip5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ip5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty())
                {
                    ip6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
