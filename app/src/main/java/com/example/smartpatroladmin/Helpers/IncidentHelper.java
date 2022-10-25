package com.example.smartpatroladmin.Helpers;

import static com.example.smartpatroladmin.Constants.FirestoreCollections.GUARDS_REFERENCE;
import static com.example.smartpatroladmin.Constants.FirestoreCollections.INCIDENT_REFERENCE;
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
                    //guard.setuId(snapshot.get());

                    //setters
                    // incidents.setTitle(snapshot.get());
                    // incidents.setMessage(snapshot.get());
                    // incidents.setGuard(guard);
                   // incidents.setCalendar(calendar.setTime(snapshot.getDate()));

                 //   incidentsArrayList.add(guard);
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
