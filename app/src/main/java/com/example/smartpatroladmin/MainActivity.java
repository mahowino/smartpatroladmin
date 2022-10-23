package com.example.smartpatroladmin;

import static com.example.smartpatroladmin.util.AppSystem.redirectActivity;
import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.smartpatroladmin.util.AppSystem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread splashScreenDisplay = new Thread() {
            @Override
            public void run() {
                load_splashscreen();
                super.run();
            }
        };

        splashScreenDisplay.start();
    }

    private void load_splashscreen() {

        //running of thread
        try {
            sleep(2000);
            redirectActivity(MainActivity.this, LoginScreen.class); finish();
        } catch (Exception exception) {

        }

    }
}