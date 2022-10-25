package com.example.smartpatroladmin.Firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseConstans {

    public static FirebaseFirestore db=FirebaseFirestore.getInstance();
    public static FirebaseAuth mAuth=FirebaseAuth.getInstance();
}
