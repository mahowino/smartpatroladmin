package com.example.smartpatroladmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smartpatroladmin.Adapters.GuardAdapter;
import com.example.smartpatroladmin.Helpers.GuardHelper;
import com.example.smartpatroladmin.Helpers.IncidentHelper;
import com.example.smartpatroladmin.Interface.FirebaseDocumentRetriever;
import com.example.smartpatroladmin.Interface.GuardsRetriever;
import com.example.smartpatroladmin.Models.Guard;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class IncidentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident);


    }

    private void createAdapter(List<Guard> guards) {
        GuardAdapter adapter=new GuardAdapter(getApplicationContext(),guards);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }
}