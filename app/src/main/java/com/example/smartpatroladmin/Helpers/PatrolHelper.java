package com.example.smartpatroladmin.Helpers;

import android.content.Context;

import com.example.smartpatroladmin.Firebase.Constants.FirebaseFields;
import com.example.smartpatroladmin.Firebase.Constants.FirestoreCollections;
import com.example.smartpatroladmin.Firebase.Constants.FirestorePaths;
import com.example.smartpatroladmin.Firebase.FirebaseRepository;
import com.example.smartpatroladmin.Interface.FirebaseDocumentRetriever;
import com.example.smartpatroladmin.Interface.PatrolRetriever;
import com.example.smartpatroladmin.Models.Guard;
import com.example.smartpatroladmin.Models.Patrols;
import com.example.smartpatroladmin.util.AppSystem;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PatrolHelper {

    public static void getPatrols(Guard guard, Context context,PatrolRetriever retriever){
        CollectionReference reference=FirestoreCollections.GUARDS_REFERENCE.document(guard.getuId()).collection(FirestorePaths.PATROLS_PATH);
        FirebaseRepository.getDocuments(reference, new FirebaseDocumentRetriever() {
            @Override
            public void onSuccess(Task<QuerySnapshot> snapshotTask) {
                //get the list of patrols
                List<Patrols> patrols=new ArrayList<>();
                for (DocumentSnapshot snapshot:snapshotTask.getResult()){
                    Patrols patrol=new Patrols();
                    patrol.setStartingTime(snapshot.getTimestamp(FirebaseFields.TIME));
                    patrol.setStartingLocation(
                            AppSystem.getLocationFromString(snapshot.getGeoPoint(FirebaseFields.STARTINGLOCATION),context));
                    patrol.setEndingLocation(
                            AppSystem.getLocationFromString(snapshot.getGeoPoint(FirebaseFields.ENDINGLOCATION),context));

                    patrols.add(patrol);

                }
                retriever.onSuccess(patrols);
            }

            @Override
            public void onError(String e) {

                retriever.onError(e);
            }
        });
    }

}
