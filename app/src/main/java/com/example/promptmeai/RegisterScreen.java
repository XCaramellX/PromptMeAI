package com.example.promptmeai;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputEditText;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterScreen extends AppCompatActivity{

   private TextInputEditText emailTextInput, usernameTextInput, passwordTextInput, reEnterPasswordTextInput;

   Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        register =findViewById(R.id.registerUser);

        emailTextInput = findViewById(R.id.emailTextInput);
        usernameTextInput = findViewById(R.id.usernameTextInput);
        passwordTextInput = findViewById(R.id.passwordTextInput);
        reEnterPasswordTextInput = findViewById(R.id.reEnterPasswordTextInput);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(inputValidation()){
                    Intent categoryExperience = new Intent(RegisterScreen.this,
                            CategoryExperience.class);
                    startActivity(categoryExperience);

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
}