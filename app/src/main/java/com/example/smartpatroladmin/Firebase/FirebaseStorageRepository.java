package com.example.smartpatroladmin.Firebase;

import android.net.Uri;

import com.example.smartpatroladmin.Firebase.Constants.FirebaseStorageBuckets;

public class FirebaseStorageRepository {

    public static Uri getAdminProfilePicture(){
        FirebaseStorageBuckets.profilePictures
                .child(FirebaseConstants.user.getUid()+".jpeg");
     return null;


    }
}
