package com.hfad.happylibrary;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText email, password;
    Button sign_in;
    TextView go_to;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        sign_in = findViewById(R.id.sign_in);
        go_to = findViewById(R.id.go_to_registration);
        go_to.setPaintFlags(go_to.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        go_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,
                        Registration.class);
                startActivity(intent);
            }
        });

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callLogin(email.getText().toString(), password.getText().toString());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null && currentUser.isEmailVerified()) {
            Intent intent = new Intent(getApplicationContext()
                    , MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(Login.this, "Authentication failed.",
                    Toast.LENGTH_SHORT);
        }
    }

    public void callLogin(String email, String password) {
//        code is about firebase login

        if (email.equals(null) || password.equals(null) || email.equals("") || password.equals("")){
            Toast.makeText(Login.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
        }else{
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SSSSSSS", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user.isEmailVerified()) {
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                                finish();

                            } else {
                                Log.d("SSSSSSS", "error: ");
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("RRRRRRRR", "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        }
    }
}
