package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class Activity_patient extends AppCompatActivity {
    private static final String TAG ="hell" ;
    private Bundle savedInstanceState;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN =123;
    String username = null;
    //facebook login stuff !
    private CallbackManager mCallbackManager;
    private LoginButton fb_button;
    private static final String TAG_FB = "FacebookAuthentication";
    private FirebaseAuth.AuthStateListener authStateListener;
    private AccessTokenTracker accessTokenTracker;
    private boolean fb_or_not = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        EditText editTextTextPersonName, editTextTextEmailAddress, editTextTextPassword2;
        editTextTextEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
       // editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextTextPassword2 = (EditText) findViewById(R.id.editTextTextPassword2);
        editTextTextPersonName = (EditText) findViewById(R.id.editTextTextPersonName);
        mAuth = FirebaseAuth.getInstance();
        //google !
        createRequest();
        //Facebook !
        //FacebookSdk.sdkInitialize(getApplicationContext());
 //gooogle sign in
        findViewById(R.id.imageButton5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        fb_button = findViewById(R.id.login_button);

        mCallbackManager = CallbackManager.Factory.create();
        fb_button.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG_FB,"OnSuccess"+loginResult);
                        fb_or_not = true;
                        handleFacebookToken(loginResult.getAccessToken());

                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });


                Button patient_signin = (Button) findViewById(R.id.button8);
        patient_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                this.userLogin();

            }

            private void userLogin() {
                 username = editTextTextPersonName.getText().toString().trim();
                String email = editTextTextEmailAddress.getText().toString().trim();
                String password = editTextTextPassword2.getText().toString().trim();
               // String phonenum = editTextPhone.getText().toString().trim();
                if (email.isEmpty()) {
                    editTextTextEmailAddress.setError("EMAIL REQUIRED!");
                    editTextTextEmailAddress.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextTextEmailAddress.setError("Please Enter a valid email");
                    editTextTextEmailAddress.requestFocus();
                    return;
                }
                if (username.isEmpty()) {
                    editTextTextPersonName.setError("USERNAME REQUIRED!");
                    editTextTextPersonName.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    editTextTextPassword2.setError("PASSWORD REQUIRED!");
                    editTextTextPassword2.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    editTextTextPassword2.setError("Minimum length of the password should be 6");
                    editTextTextPassword2.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {// if successfull we open a new activity
                            Toast.makeText(getApplicationContext(),"Signed In",Toast.LENGTH_SHORT).show();

                            Intent succ = new Intent(Activity_patient.this,GetOtp.class);
                            succ.putExtra("uname",username);
                            succ.putExtra("logged","y");
                            //succ.putExtra("backendotp",backendotp);
                            // succ.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(succ);


                        } else {
                            Intent fail_in = new Intent(Activity_patient.this, patient_failure.class);
                            startActivity(fail_in);
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
        Button patient_signup = (Button) findViewById(R.id.button9);
        patient_signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent_signup = new Intent(view.getContext(), Activity_patient_signup_form.class);
                startActivityForResult(myIntent_signup, 0);
            }
        });
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull @NotNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null)
                {
                    Toast.makeText(Activity_patient.this,"Already logged in !",Toast.LENGTH_SHORT).show();
                    username  = user.getDisplayName();
                    Intent i = new Intent(Activity_patient.this,GetOtp.class);
                    startActivity(i);
                }

            }
        };
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if(currentAccessToken ==null)
                {
                    mAuth.signOut();
                }
            }
        };


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener!=null)
        {
            mAuth.removeAuthStateListener(authStateListener);
        }
    }

    private void handleFacebookToken(AccessToken accessToken) {

        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser user = mAuth.getCurrentUser();
                    username = user.getDisplayName();
                    Toast.makeText(Activity_patient.this,"Fb Login Success",Toast.LENGTH_SHORT).show();
                    Intent i1 = new Intent(Activity_patient.this,GetOtp.class);
                    i1.putExtra("uname",username);
                    startActivity(i1);
                }
                else
                {
                    Toast.makeText(Activity_patient.this,"Fb login failure !",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }



    private void createRequest() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
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
        if (fb_or_not == true) {
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        } else {
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
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.w(TAG, "Google sign in failed", e);
                }
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
                            Intent i1 =new Intent(getApplicationContext(),fill_up_form.class);
                            GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(Activity_patient.this);
                            if(signInAccount!=null)
                            {
                                username = signInAccount.getDisplayName();
                                 i1.putExtra("uname",username);
                            }

                            //i1.putExtra("uname","Google Friend");
                            mAuth.signOut();
                            startActivity(i1);


                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Activity_patient.this,"Sorry Authentication failed!",Toast.LENGTH_SHORT).show();

                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                        }
                    }
                });
    }
    //facebook


}

