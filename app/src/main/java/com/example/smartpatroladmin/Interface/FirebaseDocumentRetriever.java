package com.example.smartpatroladmin.Interface;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public interface FirebaseDocumentRetriever {

    void onSuccess(Task<QuerySnapshot> snapshotTask);
    void onError(String e);
}
