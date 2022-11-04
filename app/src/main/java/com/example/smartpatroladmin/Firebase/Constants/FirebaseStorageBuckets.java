package com.example.smartpatroladmin.Firebase.Constants;

import com.example.smartpatroladmin.Firebase.FirebaseConstans;
import com.google.firebase.storage.StorageReference;

public class FirebaseStorageBuckets {
    public static StorageReference profilePictures= FirebaseConstans.firebaseStorageReference.child(FirebaseStoragePaths.ProfilePicturePath);

}
