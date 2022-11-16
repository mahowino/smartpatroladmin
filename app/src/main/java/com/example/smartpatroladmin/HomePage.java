package com.example.smartpatroladmin;

import static com.example.smartpatroladmin.Helpers.GuardHelper.signOut;
import static com.example.smartpatroladmin.util.AppSystem.redirectActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.smartpatroladmin.databinding.ActivityHomePageBinding;

public class HomePage extends AppCompatActivity {

    private ActivityHomePageBinding binding;
    Button logOut;
    CardView card_ViewGuards, card_ViewIncidents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        logOut=findViewById(R.id.logOut);
        card_ViewGuards=findViewById(R.id.card_ViewGuards);
        card_ViewIncidents=findViewById(R.id.card_ViewIncidents);

        setListeners();

    }

    private void setListeners() {
        logOut.setOnClickListener(btn-> {
            signOut();
            redirectActivity(HomePage.this,LoginScreen.class);
        });

        card_ViewGuards.setOnClickListener(v -> {
            Intent intent=new Intent(HomePage.this,ViewGuardsActivity.class);
            startActivity(intent);
        });

        card_ViewIncidents.setOnClickListener(v -> {
            Intent intent=new Intent(HomePage.this,IncidentActivity.class);
            startActivity(intent);
        });
    }

}