package kpr.am.tripsheet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity5 extends AppCompatActivity {

    Button button;
    String codeSent;
    FirebaseAuth mAuth;
    PhoneAuthProvider.ForceResendingToken tokenone;
    private EditText editTextName, editTextMail, editTextPhone, editTextPass, editTextCompPass;
    private String Name, Loc, Phone,Pass,Cpass;
    TextView textView2;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);
        mAuth = FirebaseAuth.getInstance();
        button= findViewById(R.id.button);
        textView2= findViewById(R.id.textView2);
        tv= findViewById(R.id.textView);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextName = findViewById(R.id.editTextTextPersonName);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Phone=editTextPhone.getText().toString();
                Log.d("321abcd", "sendotp: " + "+91" + editTextPhone.getText().toString());
                PhoneAuthOptions options =
                        PhoneAuthOptions.newBuilder(mAuth)
                                .setPhoneNumber("+91" + editTextPhone.getText().toString())       // Phone number to verify
                                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                .setActivity(MainActivity5.this)                 // Activity (for callback binding)
                                .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                                .build();
                    PhoneAuthProvider.verifyPhoneNumber(options);      // OnVerificationStateChangedCallbacks


                Thread t = new Thread() {
                    public void run() {
                        for (int i = 30; i > 0; i--) {
                            try {
                                final int a = i;
                                int finalI = i;
                                runOnUiThread(() -> {
                                    textView2.setText("Please Wait "+ finalI +" seconds to Resend OTP");
                                    if (finalI==2){
                                        button.setVisibility(View.VISIBLE);
                                    }
                                });
                                sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                t.start();



            }
        });

    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeSent = s;
            tokenone=forceResendingToken;
            Log.d("132abcd", "sendotp: " + s + "\n\n\n" + codeSent);
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            Toast.makeText(MainActivity5.this, "Completed!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(MainActivity5.this, "Failed!", Toast.LENGTH_SHORT).show();
            Log.d("321TAG", "onVerificationFailed: " + e.getMessage().toString());
        }
    };
    public void sgn(View view) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, editTextName.getText().toString());
        signInWithPhoneAuthCredential(credential);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            tv.setText("Success!!");
                            Intent intent= new Intent(MainActivity5.this, MainActivity4.class);
                            intent.putExtra("num",Phone);
                            startActivity(intent);
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(getApplicationContext(), "Incorrect Verification code", Toast.LENGTH_SHORT).show();
                                tv.setText("RETRY Please !");
                            }
                        }
                    }
                });
    }


    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }
}