package com.example.smartpatroladmin.Helpers;

import static com.example.smartpatroladmin.Firebase.Constants.FirestoreCollections.*;
import static com.example.smartpatroladmin.Firebase.FirebaseAuthentication.*;
import static com.example.smartpatroladmin.Firebase.FirebaseRepository.*;

import com.example.smartpatroladmin.Firebase.Constants.FirestoreCollections;
import com.example.smartpatroladmin.Firebase.FirebaseAuthentication;
import com.example.smartpatroladmin.Firebase.FirebaseConstans;
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

    public static void getGuards(GuardsRetriever retriever){

        getDocuments(GUARDS_REFERENCE,new FirebaseDocumentRetriever() {
           @Override
           public void onSuccess(Task<QuerySnapshot> snapshotTask) {

               List<Guard> guardArrayList=new ArrayList<>();
               for (DocumentSnapshot snapshot:snapshotTask.getResult()){
                   Guard guard=new Guard();
                   guard.getGuardName();
                   guardArrayList.add(guard);
               }
               retriever.onSuccess(guardArrayList);

           }

           @Override
           public void onError(String e) {

           }
       });

    }
    public static void logInGuard(Guard guard,onResult result){
        String response=validateInput(guard);

        if (response!=null)result.onError(response);
        else signInUser(guard.getEmail(),guard.getPassword(),result);

    }

    private static String validateInput(Guard guard) {
        if (guard.getEmail().isEmpty()) {return "Email is required";}
        if (guard.getPassword().isEmpty()) {return"Password is required";}
        if (guard.getPassword().length() < 8) {return"Password must be more than 8 characters";}

        return null;
    }

    public static void signOut() {
        if (FirebaseConstans.user!=null){
            FirebaseConstans.mAuth.signOut();
        }


    }
}

