package com.example.smartpatroladmin.Firebase.Constants;

import com.example.smartpatroladmin.Firebase.FirebaseConstants;
import com.google.firebase.storage.StorageReference;

public class FirebaseStorageBuckets {
    public static StorageReference profilePictures= FirebaseConstants.firebaseStorageReference.child(FirebaseStoragePaths.ProfilePicturePath);

}
