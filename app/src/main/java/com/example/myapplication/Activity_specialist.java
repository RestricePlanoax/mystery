package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.jetbrains.annotations.NotNull;

public class Activity_specialist extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;
    private static final String TAG = "Done";
    private Bundle savedInstanceState;
    String username = null;
    FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist_home);
        createRequest();
        findViewById(R.id.imageButton5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        EditText editTextSpecialistName, editTextSpecialistEmailAddress, editTextSpecialistPassword, editTextSpecialistPhone;
        editTextSpecialistEmailAddress = (EditText) findViewById(R.id.editTextSpecialistEmailAddress);
        editTextSpecialistPhone = (EditText) findViewById(R.id.editTextSpecialistPhone);
        editTextSpecialistPassword = (EditText) findViewById(R.id.editTextSpecialistPassword);
        editTextSpecialistName = (EditText) findViewById(R.id.editTextSpecialistName);
        mAuth = FirebaseAuth.getInstance();
        Button specialist_login = (Button) findViewById(R.id.button10);
        specialist_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                this.specialistLogin();
            }

            private void specialistLogin() {
                 username = editTextSpecialistName.getText().toString().trim();
                String email = editTextSpecialistEmailAddress.getText().toString().trim();
                String password = editTextSpecialistPassword.getText().toString().trim();
                String phonenum = editTextSpecialistPhone.getText().toString().trim();
                if (email.isEmpty()) {
                    editTextSpecialistEmailAddress.setError("EMAIL REQUIRED!");
                    editTextSpecialistEmailAddress.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextSpecialistEmailAddress.setError("Please Enter a valid email");
                    editTextSpecialistEmailAddress.requestFocus();
                    return;
                }
                if (username.isEmpty()) {
                    editTextSpecialistName.setError("USERNAME REQUIRED!");
                    editTextSpecialistName.requestFocus();
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
                    editTextSpecialistPhone.setError("PHONE NUMBER IS REQUIRED!");
                    editTextSpecialistPhone.requestFocus();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent succ1 = new Intent(Activity_specialist.this, Activity_dashboard_specialist.class);
                            succ1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            succ1.putExtra("uname",username);
                            startActivity(succ1);
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        Button specialist_signup = (Button) findViewById(R.id.button12);
        specialist_signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent1_signup = new Intent(view.getContext(), Activity_specialist_signup.class);
                startActivityForResult(myIntent1_signup, 0);
            }
        });
    }

    private void createRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(),Activity_dashboard_specialist.class);
                            GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(Activity_specialist.this);
                            if(signInAccount!=null)
                            {
                                username = signInAccount.getDisplayName();
                                intent.putExtra("uname",username);
                            }
                          //  intent.putExtra("uname","Google Friend");
                            startActivity(intent);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            //updateUI(null);
                            Toast.makeText(Activity_specialist.this,"Sorry Authentication failed!",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
