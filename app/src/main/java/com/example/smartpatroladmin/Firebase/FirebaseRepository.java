package com.example.smartpatroladmin.Firebase;

import static com.example.smartpatroladmin.Firebase.Constants.FirestoreCollections.GUARDS_REFERENCE;

import com.example.smartpatroladmin.Firebase.Constants.FirestoreCollections;
import com.example.smartpatroladmin.Interface.Callback;
import com.example.smartpatroladmin.Interface.FirebaseDocumentRetriever;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;

public class FirebaseRepository  {

    public static void getDocuments(CollectionReference reference,FirebaseDocumentRetriever retriever){
        reference.get()
                .addOnCompleteListener(task -> retriever.onSuccess(task))
                .addOnFailureListener(e -> retriever.onError(e.getMessage()));

    }
    public static void getDocument(DocumentReference reference, Callback retriever){
        reference.get()
                .addOnCompleteListener(task -> retriever.onSuccess(task))
                .addOnFailureListener(e -> retriever.onFailure(e.getMessage()));

    }

}
