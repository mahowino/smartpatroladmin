package com.example.smartpatroladmin.Interface;

import com.example.smartpatroladmin.Models.Guard;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public interface GuardsRetriever {
     void onSuccess(List<Guard> guards);
}
