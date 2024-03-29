package com.example.smartpatroladmin.Helpers;

import static com.example.smartpatroladmin.Firebase.Constants.FirestoreCollections.INCIDENT_REFERENCE;
import static com.example.smartpatroladmin.Firebase.Constants.FirebaseFields.*;
import static com.example.smartpatroladmin.Firebase.FirebaseRepository.getDocuments;

import com.example.smartpatroladmin.Interface.FirebaseDocumentRetriever;
import com.example.smartpatroladmin.Interface.IncidentRetriever;
import com.example.smartpatroladmin.Models.Guard;
import com.example.smartpatroladmin.Models.Incidents;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class IncidentHelper {
    Incidents incident;

    public IncidentHelper(Guard guard) {
        incident=new Incidents();
        //getIncidentFromDatabase
    }

    public static void getAllIncidents(IncidentRetriever retriever){
        getDocuments(INCIDENT_REFERENCE,new FirebaseDocumentRetriever() {
            @Override
            public void onSuccess(Task<QuerySnapshot> snapshotTask) {

                List<Incidents> incidentsArrayList=new ArrayList<>();
                for (DocumentSnapshot snapshot:snapshotTask.getResult()){
                    //init
                    Incidents incidents=new Incidents();
                    Calendar calendar = Calendar.getInstance();
                    Guard guard=new Guard();

                    //guard
                    guard.setuId(snapshot.getString(GUARD_UID));

                    //setters
                    incidents.setTitle(snapshot.getString(TITLE));
                    incidents.setDescription(snapshot.getString(DESCRIPTION));
                    incidents.setGuard(guard);
                    calendar.setTime(new Date());
                    incidents.setCalendar(calendar);

                    incidentsArrayList.add(incidents);
                }
                retriever.onSuccess(incidentsArrayList);

            }

            @Override
            public void onError(String e) {

            }
        });
    }

    public String generateIncidentReport(){
    return null;
    }

}
