package com.example.smartpatroladmin.Firebase;

import static com.example.smartpatroladmin.Constants.FirestoreCollections.GUARDS_REFERENCE;

import com.example.smartpatroladmin.Constants.FirestoreCollections;
import com.example.smartpatroladmin.Interface.FirebaseDocumentRetriever;
import com.example.smartpatroladmin.Interface.onResult;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FirebaseRepository  {

    public static void getDocuments(CollectionReference reference,FirebaseDocumentRetriever retriever){
        reference.get()
                .addOnCompleteListener(task -> retriever.onSuccess(task))
                .addOnFailureListener(e -> retriever.onError(e.getMessage()));

    }

}
