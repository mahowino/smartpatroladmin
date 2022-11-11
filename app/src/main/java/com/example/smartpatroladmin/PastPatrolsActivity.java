package com.example.smartpatroladmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smartpatroladmin.Adapters.GuardAdapter;
import com.example.smartpatroladmin.Adapters.PatrolAdapter;
import com.example.smartpatroladmin.Helpers.GuardHelper;
import com.example.smartpatroladmin.Helpers.PatrolHelper;
import com.example.smartpatroladmin.Interface.GuardsRetriever;
import com.example.smartpatroladmin.Interface.PatrolRetriever;
import com.example.smartpatroladmin.Models.Guard;
import com.example.smartpatroladmin.Models.Patrols;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class PastPatrolsActivity extends AppCompatActivity {
    Guard guard;
    RecyclerView view_patrols_recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_patrols);
        initializeData();



    }

    private void setAdapter(List <Patrols> patrols) {

        PatrolAdapter adapter=new PatrolAdapter(getApplicationContext(),patrols);
        view_patrols_recyclerView.setAdapter(adapter);
        view_patrols_recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));


    }


    private void initializeData() {
    guard=getIntent().getParcelableExtra(GuardAdapter.GUARD_DATA);
        view_patrols_recyclerView=findViewById(R.id.view_patrols_recyclerView);

        PatrolHelper.getPatrols(guard,this, new PatrolRetriever() {
            @Override
            public void onSuccess(List<Patrols> patrols) {

                setAdapter(patrols);
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}