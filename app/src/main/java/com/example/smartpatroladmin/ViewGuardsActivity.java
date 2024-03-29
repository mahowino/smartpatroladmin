package com.example.smartpatroladmin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpatroladmin.Adapters.GuardAdapter;
import com.example.smartpatroladmin.Helpers.GuardHelper;
import com.example.smartpatroladmin.Models.Guard;

import java.util.List;

public class ViewGuardsActivity extends AppCompatActivity {


    RecyclerView viewGuards;
    ImageView btnAddGuard,btnDeleteGuard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_guards);
        initializeData();
        setListeners();

        GuardHelper.getGuards(guards -> ViewGuardsActivity.this.setAdapter(guards));


    }

    private void setListeners() {
        btnAddGuard.setOnClickListener(v -> {
            Intent intent=new Intent(ViewGuardsActivity.this,AddGuardsActivity.class);
            startActivity(intent);
        });
        btnDeleteGuard.setOnClickListener(v -> {
            Intent intent=new Intent(ViewGuardsActivity.this,DeleteGuardsActivity.class);
            startActivity(intent);
        });

    }

    private void initializeData() {

        viewGuards=findViewById(R.id.view_guards_recyclerView);
        btnAddGuard=findViewById(R.id.btnAddGuard);
        btnDeleteGuard=findViewById(R.id.btnDeleteGuard);
    }
    private void setAdapter(List<Guard> guards){
        GuardAdapter adapter=new GuardAdapter(getApplicationContext(),ViewGuardsActivity.this,guards);
        viewGuards.setAdapter(adapter);
        viewGuards.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

    }
}