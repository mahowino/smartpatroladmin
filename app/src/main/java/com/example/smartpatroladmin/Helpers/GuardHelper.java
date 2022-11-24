package com.example.smartpatroladmin.Helpers;

import static com.example.smartpatroladmin.Firebase.Constants.FirestoreCollections.*;
import static com.example.smartpatroladmin.Firebase.FirebaseAuthentication.*;
import static com.example.smartpatroladmin.Firebase.FirebaseRepository.*;

import androidx.annotation.NonNull;

import com.example.smartpatroladmin.Firebase.Constants.FirebaseFields;
import com.example.smartpatroladmin.Firebase.Constants.FirestorePaths;
import com.example.smartpatroladmin.Firebase.FirebaseAuthentication;
import com.example.smartpatroladmin.Firebase.FirebaseConstants;
import com.example.smartpatroladmin.Firebase.FirebaseRepository;
import com.example.smartpatroladmin.Interface.callback;
import com.example.smartpatroladmin.Interface.FirebaseDocumentRetriever;
import com.example.smartpatroladmin.Interface.GuardsRetriever;
import com.example.smartpatroladmin.Interface.getSchedules;
import com.example.smartpatroladmin.Interface.onResult;
import com.example.smartpatroladmin.Models.Guard;
import com.example.smartpatroladmin.Models.Schedule;
import com.example.smartpatroladmin.Models.TempGuardStorage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public  class GuardHelper {
    static Guard guardInHelper;



    public static void getGuards(GuardsRetriever retriever){

        getDocuments(GUARDS_REFERENCE,new FirebaseDocumentRetriever() {
           @Override
           public void onSuccess(Task<QuerySnapshot> snapshotTask) {

               List<Guard> guardArrayList=new ArrayList<>();
               for (DocumentSnapshot snapshot:snapshotTask.getResult()){
                   Guard guard=new Guard();
                   guard.setGuardName(String.valueOf(snapshot.get(FirebaseFields.EMAIL)));
                   guard.setuId(snapshot.getId());
                   guardArrayList.add(guard);
               }
               retriever.onSuccess(guardArrayList);

           }

           @Override
           public void onError(String e) {

           }
       });

    }
    private static void getGuardDetails(String uid, callback callback){
        getDocument(GUARDS_REFERENCE.document(uid), new callback() {
            @Override
            public void onSuccess(Object o) {
                DocumentSnapshot snapshot=((Task<DocumentSnapshot>)o).getResult();

                Guard guard=new Guard();
                guard.setGuardName(String.valueOf(snapshot.get(FirebaseFields.EMAIL)));
                guard.setuId(snapshot.getId());
                Map<String, Object> schedule_days=(Map<String, Object>) snapshot.get(FirebaseFields.SCHEDULE);
                Schedule schedule=new Schedule();

                for (int day_of_week = 0; day_of_week< Objects.requireNonNull(schedule_days).size(); day_of_week++){
                    if (schedule_days.containsKey(FirebaseFields.MONDAY))
                        schedule.setMonday((Map<String, Date>) schedule_days.get(FirebaseFields.MONDAY));
                   else if (schedule_days.containsKey(FirebaseFields.TUESDAY))
                        schedule.setMonday((Map<String, Date>) schedule_days.get(FirebaseFields.TUESDAY));
                   else if (schedule_days.containsKey(FirebaseFields.WEDNESDAY))
                        schedule.setMonday((Map<String, Date>) schedule_days.get(FirebaseFields.WEDNESDAY));
                    else if (schedule_days.containsKey(FirebaseFields.THURSDAY))
                        schedule.setMonday((Map<String, Date>) schedule_days.get(FirebaseFields.THURSDAY));
                    else if (schedule_days.containsKey(FirebaseFields.FRIDAY))
                        schedule.setMonday((Map<String, Date>) schedule_days.get(FirebaseFields.FRIDAY));


                }

                guard.setSchedule(schedule);
                callback.onSuccess(guard);
            }

            @Override
            public void onFailure(Object o) {
                callback.onFailure("failed to get guard");
            }
        });

    }
    public static void logInGuard(Guard guard,onResult result){
        String response=validateInput(guard);

        if (response!=null)result.onError(response);
        else {
            signInUser(guard.getEmail(), guard.getPassword(), new onResult() {
                @Override
                public void onSuccess() {
                    setFirebaseToken(guard,result);
                }

                @Override
                public void onError(String e) {

                }
            });

        }

    }

    private static void setFirebaseToken(Guard guard,onResult result) {

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        result.onError("error");
                        return;
                    }

                    // Get new FCM registration token
                    String token = task.getResult();

                    updateTokenInDatabase(guard,getTokenData(token),result);

                });
    }
    private static void updateTokenInDatabase(Guard guard,Map<String,Object> map,onResult result) {
        String path= "Guard/"+guard.getuId();
        setDocument(map, createDocumentReference(path), SetOptions.merge(), new callback() {
            @Override
            public void onSuccess(Object object) {

                result.onSuccess();
            }

            @Override
            public void onFailure(Object object) {

                result.onError(((Exception)object).getMessage());
            }
        });

    }
    private static Map<String, Object> getTokenData(String token) {
        Map<String,Object> map=new HashMap<>();
        map.put("token",token);
        return map;
    }



    private static void getSchedule(String guardId,getSchedules getSchedules){
        getGuardDetails(guardId, new callback() {
            @Override
            public void onSuccess(Object o) {
                Guard guard=(Guard) o;

            }

            @Override
            public void onFailure(Object o) {

            }
        });


    }

    public static void createGuardCollectionInFirebase(Guard guard,callback callback) {
        DocumentReference reference=FirebaseConstants.db.collection("Guard").document(FirebaseConstants.user.getUid());
        FirebaseRepository.setDocument(createGuard(guard),reference,callback);


    }


    private static Map<String, Object> createGuard(Guard guard) {
        Map<String, Object> map=new HashMap<>();
        map.put(FirebaseFields.EMAIL,guard.getEmail());
        return map;
    }

    private static String validateInput(Guard guard) {
        if (guard.getEmail().isEmpty()) {return "Email is required";}
        if (guard.getPassword().isEmpty()) {return"Password is required";}
        if (guard.getPassword().length() < 8) {return"Password must be more than 8 characters";}

        return null;
    }

    public static void signOut() {
        if (FirebaseConstants.user!=null){
            FirebaseConstants.mAuth.signOut();
        }


    }
}

