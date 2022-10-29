package com.example.smartpatroladmin.Firebase;

import static com.example.smartpatroladmin.Firebase.Constants.FirestoreCollections.GUARDS_REFERENCE;

import com.example.smartpatroladmin.Firebase.Constants.FirestoreCollections;
import com.example.smartpatroladmin.Interface.FirebaseDocumentRetriever;
import com.google.firebase.firestore.CollectionReference;

public class FirebaseRepository  {

    public static void getDocuments(CollectionReference reference,FirebaseDocumentRetriever retriever){
        reference.get()
                .addOnCompleteListener(task -> retriever.onSuccess(task))
                .addOnFailureListener(e -> retriever.onError(e.getMessage()));

    }

}
