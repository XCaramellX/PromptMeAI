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

public class RegisterScreen extends AppCompatActivity{

   private TextInputEditText emailTextInput, usernameTextInput, passwordTextInput, reEnterPasswordTextInput;

   private FirebaseAuth auth = FirebaseAuth.getInstance();

   private String TAG = "RegisterScreen";
   Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        register = findViewById(R.id.registerUser);

        emailTextInput = findViewById(R.id.emailTextInput);
        usernameTextInput = findViewById(R.id.usernameTextInput);
        passwordTextInput = findViewById(R.id.passwordTextInput);
        reEnterPasswordTextInput = findViewById(R.id.reEnterPasswordTextInput);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;

                email = emailTextInput.getText().toString();
                password = passwordTextInput.getText().toString();

                if(inputValidation()) {

                    newUser(email, password);

                }

            }
        });



    }

    private boolean inputValidation() {
        if(TextUtils.isEmpty(emailTextInput.getText().toString().trim())){
            emailTextInput.setError("Email is Required!");
            return false;
        }
        if(TextUtils.isEmpty(usernameTextInput.getText().toString().trim())){
            usernameTextInput.setError("Username is Required!");
            return false;
        }
        if(TextUtils.isEmpty(passwordTextInput.getText().toString().trim())){
            passwordTextInput.setError("Password is Required!");
            return false;
        }else if(passwordTextInput.length() < 8){
            passwordTextInput.setError("Password is less than 8 characters!");
        }
        if(!passwordTextInput.getText().toString().trim()
                .equals(reEnterPasswordTextInput.getText().toString().trim())){
                    reEnterPasswordTextInput.setError("Password does not match!");
                    return false;
        }
        return true;
    }

    private void newUser(String email, String password){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task){
                    if(task.isSuccessful()) {
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = auth.getCurrentUser();
                        Intent categoryExperience = new Intent(RegisterScreen.this,
                                CategoryExperience.class);
                        startActivity(categoryExperience);

                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        String errorMessage = task.getException() != null ? task.getException().getMessage()
                                : "Unknown Error";
                        Toast.makeText(RegisterScreen.this, "Authentication failed."
                                        + errorMessage,
                                Toast.LENGTH_SHORT).show();

                    }
                }
            });

    }


}