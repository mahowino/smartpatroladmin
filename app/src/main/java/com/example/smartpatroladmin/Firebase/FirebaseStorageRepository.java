package com.example.smartpatroladmin.Firebase;

import android.net.Uri;

import com.example.smartpatroladmin.Firebase.Constants.FirebaseStorageBuckets;
import com.example.smartpatroladmin.Firebase.Constants.FirebaseStoragePaths;

public class FirebaseStorageRepository {

    public static Uri getAdminProfilePicture(){
        FirebaseStorageBuckets.profilePictures
                .child(FirebaseConstans.user.getUid()+".jpeg");
     return null;


    }
}
