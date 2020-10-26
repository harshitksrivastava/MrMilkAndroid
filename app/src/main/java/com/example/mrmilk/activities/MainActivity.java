package com.example.mrmilk.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mrmilk.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ImageButton btn;
    private EditText mCountryCode;
    private EditText mPhoneNumber;
    private TextView mErrorLoginFeedback;


    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (ImageButton) findViewById(R.id.submitPhone_Btn);
        mCountryCode = findViewById(R.id.country_code);
        mPhoneNumber = findViewById(R.id.phone_number);
        mErrorLoginFeedback = findViewById(R.id.login_error_feedback);
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country_code = mCountryCode.getText().toString();
                String phone_number = mPhoneNumber.getText().toString();
                String complete_phone_number = "+"+country_code+""+phone_number;

                if(country_code.isEmpty() || phone_number.isEmpty()){
                    mErrorLoginFeedback.setText("Please fill to continue");
                    mErrorLoginFeedback.setVisibility(View.VISIBLE);
                }
                else{
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            complete_phone_number,
                            60,
                            TimeUnit.SECONDS,
                            MainActivity.this,
                            mCallbacks
                    );
                }



//                Intent homeIntent=new Intent(MainActivity.this, Home.class);
//                // Intent homeIntent=new Intent(MainActivity.this, OtpActivity.class);
//                startActivity(homeIntent);
//                finish();
            }
        });


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.d("PHONE_VERIF", "Phone Number Verification Failed");

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                Intent otpIntent=new Intent(MainActivity.this, OtpActivity.class);
                otpIntent.putExtra("phone_number",mPhoneNumber.getText().toString());
                otpIntent.putExtra("AuthCredentials",s);
                startActivity(otpIntent);
                finish();

            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mCurrentUser != null){
            Intent homeIntent = new Intent(MainActivity.this,Home.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(homeIntent);
            finish();
        }
    }
}
