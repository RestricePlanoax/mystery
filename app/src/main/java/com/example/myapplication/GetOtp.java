package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.ktx.Firebase;

import java.util.concurrent.TimeUnit;

public class GetOtp extends AppCompatActivity {
    private Bundle savedInstanceState;
    String username = null;
    String phone_number = null;
    EditText phonenum;
    FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String logged = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getotp);

        username = getIntent().getStringExtra("uname");
        logged = getIntent().getStringExtra("logged");
        phonenum = (EditText)findViewById(R.id.phone_num);
        mAuth = FirebaseAuth.getInstance();

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull @NotNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull @NotNull FirebaseException e) {
                Toast.makeText(GetOtp.this,"Error! Please check net !",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCodeSent(@NonNull @NotNull String backendotp, @NonNull @NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                //super.onCodeSent(s, forceResendingToken);

                Intent succ = new Intent(getApplicationContext(),VerifyOtp.class);
                succ.putExtra("uname",username);
                succ.putExtra("phonenum",phone_number);
                succ.putExtra("backendotp",backendotp);
                succ.putExtra("logged",logged);
                // succ.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(succ);

            }
        };

      //  phone_number = phonenum.getText().toString();
       // System.out.println(phone_number);
        Button get_otp = (Button) findViewById(R.id.get_otp);
        get_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone_number = phonenum.getText().toString();
                System.out.println(phone_number);
                verify();
            }
        });

    }

    private void verify() {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber("+91"+phone_number)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(GetOtp.this)
                .setCallbacks(mCallbacks)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}
