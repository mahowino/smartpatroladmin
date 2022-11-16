package com.example.smartpatroladmin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpatroladmin.Firebase.FirebaseConstants;
import com.example.smartpatroladmin.Models.Guard;

public class AddGuardsActivity extends AppCompatActivity {
    Button createGuard;
    EditText name_edittext, password_edittext, email_edittext, phoneNumber_edittext;
    ImageView imageView_password,imageView;
    private static boolean isPasswordVisible=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_guards);
        initializeData();
        setListeners();
    }

    private void initializeData() {
        createGuard=findViewById(R.id.createGuard_button);
        name_edittext=findViewById(R.id.name_edittext);
        email_edittext=findViewById(R.id.email_edittext);
        password_edittext=findViewById(R.id.password_edittext);
        phoneNumber_edittext=findViewById(R.id.phoneNumber_edittext);
        imageView_password=findViewById(R.id.imageView_password);
        imageView=findViewById(R.id.imageView);
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private void setListeners() {
        imageView.setOnClickListener(v -> new Intent(AddGuardsActivity.this,ViewGuardsActivity.class));

        imageView_password.setOnClickListener(v -> {
            if (!isPasswordVisible){
                password_edittext.setTransformationMethod(null);
                imageView_password.setImageDrawable(getResources().getDrawable(R.drawable.password_visible));
                isPasswordVisible=true;
            }
            else {
                password_edittext.setTransformationMethod(PasswordTransformationMethod.getInstance());
                imageView_password.setImageDrawable(getResources().getDrawable(R.drawable.password_invisible));
                isPasswordVisible=false;
            }
        });


        createGuard.setOnClickListener(v -> uploadGuard());



    }

            private void uploadGuard() {
                String email=email_edittext.getText().toString();
                String password=password_edittext.getText().toString();
                String phoneNumber=phoneNumber_edittext.getText().toString();
                String guardName=name_edittext.getText().toString();


                if (email.isEmpty()){
                    email_edittext.setError("Email is required");
                    email_edittext.requestFocus();
                    return;
                }
                if (guardName.isEmpty()){
                    name_edittext.setError("Email is required");
                    name_edittext.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    email_edittext.setError("Please provide valid email");
                    email_edittext.requestFocus();
                    return;
                }
                if (password.isEmpty()){
                    password_edittext.setError("Password is required");
                    password_edittext.requestFocus();
                    return;
                }
                if (phoneNumber.isEmpty()){
                    phoneNumber_edittext.setError("Phone Number is required");
                    phoneNumber_edittext.requestFocus();
                    return;
                }


                FirebaseConstants.mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                new Guard(email,phoneNumber,guardName);
                                Toast.makeText(AddGuardsActivity.this, "Guard added successfully", Toast.LENGTH_SHORT).show();
                                Intent intent= new Intent(AddGuardsActivity.this, ViewGuardsActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(AddGuardsActivity.this, "Retry, Guard was not added.", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
            }






