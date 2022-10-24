package com.example.smartpatroladmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smartpatroladmin.Adapters.GuardAdapter;
import com.example.smartpatroladmin.Models.Guard;

import java.util.ArrayList;
import java.util.List;

public class ViewGuardsActivity extends AppCompatActivity {

    List<Guard> guards;
    RecyclerView viewGuards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_guards);
        initializeData();

        //after populating data from the db into a guard list, if there exists guards in db
        GuardAdapter adapter=new GuardAdapter(this,guards);
        viewGuards.setAdapter(adapter);
        viewGuards.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false));

    }

    private void initializeData() {
        guards=new ArrayList<>();
        viewGuards=findViewById(R.id.view_guards_recyclerView);
    }
}