package com.example.smartpatroladmin.Firebase;

import static com.example.smartpatroladmin.Firebase.FirebaseConstants.*;

import com.example.smartpatroladmin.Interface.onResult;

public class FirebaseAuthentication {

    public static void signInUser(String email, String password,onResult result){
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnSuccessListener(v->result.onSuccess())
                .addOnFailureListener(e->result.onError(e.getMessage()));
    }

    public void logOut(){
        mAuth.signOut();
    }

}
