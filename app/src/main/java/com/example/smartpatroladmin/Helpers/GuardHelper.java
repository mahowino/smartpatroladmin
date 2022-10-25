package com.example.smartpatroladmin.Helpers;

import static com.example.smartpatroladmin.Constants.FirestoreCollections.*;
import static com.example.smartpatroladmin.Firebase.FirebaseRepository.*;

import com.example.smartpatroladmin.Constants.FirestoreCollections;
import com.example.smartpatroladmin.Firebase.FirebaseRepository;
import com.example.smartpatroladmin.Interface.FirebaseDocumentRetriever;
import com.example.smartpatroladmin.Interface.GuardsRetriever;
import com.example.smartpatroladmin.Interface.onResult;
import com.example.smartpatroladmin.Models.Guard;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public  class GuardHelper {

    public static List<Guard> getGuards(GuardsRetriever retriever){

        getDocuments(GUARDS_REFERENCE,new FirebaseDocumentRetriever() {
           @Override
           public void onSuccess(Task<QuerySnapshot> snapshotTask) {

               List<Guard> guardArrayList=new ArrayList<>();
               for (DocumentSnapshot snapshot:snapshotTask.getResult()){
                   Guard guard=new Guard();
                   guard.setGuardName("mahalon");
                   guard.setGuardApproved(true);
                   guardArrayList.add(guard);
               }
               retriever.onSuccess(guardArrayList);

           }

           @Override
           public void onError(String e) {

           }
       });
        return null;
    }
}

