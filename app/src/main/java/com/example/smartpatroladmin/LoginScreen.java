package com.example.smartpatroladmin;

import static com.example.smartpatroladmin.util.AppSystem.redirectActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartpatroladmin.util.LoadingDialog;

public class LoginScreen extends AppCompatActivity {
    EditText memail,mpassword;
    Button mlogin;
    TextView register,forgotPassword;
   //FirebaseAuth fAuth;
    LoadingDialog loadingDialog;
    ImageView passwordVisibility;
    ImageButton googleBtn,facebookBtn;
    private static boolean isPasswordVisible=false;
    String userID,email,password;
    // FirebaseFirestore fstore;

    private int RC_SIGN_IN=1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        initializeVariables();
        setListeners();

    }
    private void initializeVariables() {
        loadingDialog=new LoadingDialog(this);

        memail = findViewById(R.id.email_login_edittext);
        mpassword = findViewById(R.id.password_edittext_login);
        mlogin = findViewById(R.id.login_button);
        forgotPassword=findViewById(R.id.forgot_password);
        passwordVisibility=findViewById(R.id.imageView_password_login);

      //  fstore=FirebaseFirestore.getInstance();

        //fAuth = FirebaseAuth.getInstance();
    }


    private void setListeners() {


        forgotPassword.setOnClickListener(v -> redirectActivity(LoginScreen.this,ForgotPassword.class));



        //onclick listeners

        passwordVisibility.setOnClickListener(v -> {
            if (!isPasswordVisible) setPasswordTransformation(null,getResources().getDrawable(R.drawable.password_visible),true);
            else setPasswordTransformation(PasswordTransformationMethod.getInstance(),getResources().getDrawable(R.drawable.password_invisible),false);
        });
        mlogin.setOnClickListener(v -> validateInput());
    }

    private void validateInput() {
        redirectActivity(LoginScreen.this,HomePage.class);
    }

    private void setPasswordTransformation(PasswordTransformationMethod method, Drawable drawable, boolean isPasswordVisible){
        mpassword.setTransformationMethod(null);
        passwordVisibility.setImageDrawable(getResources().getDrawable(R.drawable.password_visible));
        this.isPasswordVisible=isPasswordVisible;
    }

}