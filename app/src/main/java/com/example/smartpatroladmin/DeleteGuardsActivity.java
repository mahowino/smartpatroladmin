package com.example.smartpatroladmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.example.smartpatroladmin.Adapters.DeleteAdapter;
import com.example.smartpatroladmin.Adapters.GuardAdapter;
import com.example.smartpatroladmin.Helpers.GuardHelper;
import com.example.smartpatroladmin.Models.Guard;

import java.util.List;

public class DeleteGuardsActivity extends AppCompatActivity {
    RecyclerView viewGuards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_guards);
        initializeData();
        setListeners();

        GuardHelper.getGuards(guards -> DeleteGuardsActivity.this.setAdapter(guards));

    }

    private void setListeners() {

    }

    private void initializeData() {
        viewGuards=findViewById(R.id.view_guards_recyclerView);
    }
    private void setAdapter(List<Guard> guards){
        DeleteAdapter adapter=new DeleteAdapter(DeleteGuardsActivity.this,guards);
        viewGuards.setAdapter(adapter);
        viewGuards.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

    }
}