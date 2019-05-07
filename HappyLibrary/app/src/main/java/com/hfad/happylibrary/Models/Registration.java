package com.hfad.happylibrary;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity {

    EditText email,password, number, name;
    Button sign_up;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.reg_email);
        password = findViewById(R.id.reg_password);
        sign_up = findViewById(R.id.reg_sign_up);
        number = findViewById(R.id.reg_number);
        name = findViewById(R.id.reg_Name);


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRegistration(email.getText().toString(),
                        password.getText().toString());
            }
        });
    }

    public void callRegistration(String email, String password){
//        code is about Registration
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Success", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            user.sendEmailVerification();
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Fail", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Registration.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}
