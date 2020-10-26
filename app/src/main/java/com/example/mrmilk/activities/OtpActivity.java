package com.example.mrmilk.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mrmilk.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class OtpActivity extends AppCompatActivity {


    private TextView mPhoneNumberTextView ;
    private TextView mErrorOtpFeedback;

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    private String mPhoneNumber;
    private String mAuthVerificationId;

    private EditText mOtpText;
    private ImageButton mVerifyBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);


        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        mPhoneNumberTextView = findViewById(R.id.phone_number_textView);
        mErrorOtpFeedback = findViewById(R.id.otp_error_feedback);
        mVerifyBtn = findViewById(R.id.verify_otp_phone);

        mPhoneNumber = getIntent().getStringExtra("phone_number");
        mAuthVerificationId = getIntent().getStringExtra("AuthCredentials");

        mOtpText = findViewById(R.id.otp_text);

        mPhoneNumberTextView.setText(mPhoneNumber);

        mVerifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String otp = mOtpText.getText().toString();
                if(otp.isEmpty()){
                    mErrorOtpFeedback.setVisibility(View.VISIBLE);
                    mErrorOtpFeedback.setText("Please Enter OTP");
                }
                else{
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mAuthVerificationId, otp);
                    Log.e("credentials", String.valueOf(credential));
                    signInWithPhoneAuthCredential(credential);

                }

            }
        });




    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(OtpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(OtpActivity.this, "Verification successful", Toast.LENGTH_SHORT).show();
                            sendUserToHome();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                            }
                        }
                    }
                });
    }

    private void sendUserToHome() {
        Intent homeIntent = new Intent(OtpActivity.this,Home.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(homeIntent);
            finish();
    }



}