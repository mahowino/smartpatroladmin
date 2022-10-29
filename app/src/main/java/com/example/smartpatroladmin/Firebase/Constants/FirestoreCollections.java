package com.example.smartpatroladmin.Firebase.Constants;

import static com.example.smartpatroladmin.Firebase.Constants.FirestorePaths.GUARDS_PATH;

import static com.example.smartpatroladmin.Firebase.Constants.FirestorePaths.INCIDENT_PATH;
import static com.example.smartpatroladmin.Firebase.FirebaseConstans.db;

import com.google.firebase.firestore.CollectionReference;

public class FirestoreCollections {

    public static final CollectionReference GUARDS_REFERENCE=db.collection(GUARDS_PATH);
    public static final CollectionReference INCIDENT_REFERENCE=db.collection(INCIDENT_PATH);


}
