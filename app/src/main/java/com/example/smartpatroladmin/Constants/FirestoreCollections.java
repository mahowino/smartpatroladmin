package com.example.smartpatroladmin.Constants;

import static com.example.smartpatroladmin.Constants.FirestorePaths.*;
import static com.example.smartpatroladmin.Firebase.FirebaseConstans.db;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreCollections {


    public static final CollectionReference GUARDS_REFERENCE=db.collection(GUARDS_PATH);

}
