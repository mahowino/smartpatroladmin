package com.example.smartpatroladmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smartpatroladmin.Adapters.GuardAdapter;
import com.example.smartpatroladmin.Helpers.GuardHelper;
import com.example.smartpatroladmin.Models.Guard;

import java.util.ArrayList;
import java.util.List;

public class ViewGuardsActivity extends AppCompatActivity {


    RecyclerView viewGuards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_guards);
        initializeData();

        GuardHelper.getGuards(guards -> {
            setAdapter(guards);
        });


    }

    private void initializeData() {

        viewGuards=findViewById(R.id.view_guards_recyclerView);
    }
    private void setAdapter(List<Guard> guards){
        GuardAdapter adapter=new GuardAdapter(getApplicationContext(),guards);
        viewGuards.setAdapter(adapter);
        viewGuards.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

    }
}