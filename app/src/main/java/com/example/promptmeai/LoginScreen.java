package com.example.promptmeai;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import android.widget.EditText;


import com.google.android.material.textfield.TextInputEditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

public class LoginScreen extends AppCompatActivity{

    private TextInputEditText emailLoginTextInput, passwordLoginTextInput;

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    private String TAG = "LoginScreen";

    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        emailLoginTextInput = findViewById(R.id.emailLoginText);
        passwordLoginTextInput = findViewById(R.id.passwordLoginText);

        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            // make the login button click, validate, and login
            @Override
            public void onClick(View view) {
             String email, password;

             email = emailLoginTextInput.getText().toString();
             password = passwordLoginTextInput.getText().toString();

             inputValidation();

             existingUser(email, password);
            }
        });

    }

    private boolean inputValidation(){
        if(TextUtils.isEmpty(emailLoginTextInput.getText().toString().trim())){
            emailLoginTextInput.setError("Please enter your email");
            return false;
        }
        if(TextUtils.isEmpty(passwordLoginTextInput.getText().toString().trim())){
            passwordLoginTextInput.setError("Please enter your password");
            return false;
        }
        return true;
    }

    private void existingUser(String email, String password){
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginScreen.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
