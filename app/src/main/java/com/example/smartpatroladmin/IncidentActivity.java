package com.example.smartpatroladmin;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.text.Paragraph;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.Toast;

import com.example.smartpatroladmin.Adapters.GuardAdapter;
import com.example.smartpatroladmin.Adapters.IncidentAdapter;
import com.example.smartpatroladmin.Helpers.GuardHelper;
import com.example.smartpatroladmin.Helpers.IncidentHelper;
import com.example.smartpatroladmin.Interface.FirebaseDocumentRetriever;
import com.example.smartpatroladmin.Interface.GuardsRetriever;
import com.example.smartpatroladmin.Interface.IncidentRetriever;
import com.example.smartpatroladmin.Interface.onResult;
import com.example.smartpatroladmin.Models.Guard;
import com.example.smartpatroladmin.Models.Incidents;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class IncidentActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 100;
    RecyclerView recyclerView;
    List<Incidents> incidents;
    Button generateIncReport;
    // declaring width and height
    // for our PDF file.
    int pageHeight = 1120;
    int pagewidth = 792;

    // creating a bitmap variable
    // for storing our images
    Bitmap bmp, scaledbmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident);
        initializeData();
        IncidentHelper.getAllIncidents(incidents1 -> {
            incidents=incidents1;
            setAdapter(incidents1);
        });
        setListeners();

    }

    private void setListeners() {
        generateIncReport.setOnClickListener(view -> createReport());
    }

    private void createReport() {
        if (checkPermission())
            generateReport(incidents);
        else {
            requestPermission();
        }
    }

    private void requestPermission() {
        // requesting permissions if not provided.
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    private void initializeData() {
        incidents=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerViewIncidents);
        generateIncReport=findViewById(R.id.generateIncReport);
    }

    private void setAdapter(List<Incidents> incident) {

        IncidentAdapter adapter=new IncidentAdapter(getApplicationContext(),incident);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }
    private boolean checkPermission() {
        // checking of permissions.
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                // after requesting permissions we are showing
                // users a toast message of permission granted.
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (writeStorage && readStorage) {
                    Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denied.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }


    private void generateReport(List<Incidents> incidents){

        PdfDocument pdfDocument = new PdfDocument();


        Paint paint = new Paint();
        Paint title = new Paint();


        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create();


        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);


        Canvas canvas = myPage.getCanvas();


        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        title.setTextSize(20);
        title.setColor(ContextCompat.getColor(this, R.color.purple_200));
        title.setStyle(Paint.Style.FILL);
        title.setTextAlign(Paint.Align.LEFT);

        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(14);
        paint.setColor(ContextCompat.getColor(this, R.color.black));
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));

        canvas.drawText("SMART PATROL PDF GENERATION", 100, 100, title);
        canvas.drawText("A PDF showing the files", 100, 80, paint);

        int startingIndex=140;
    for (Incidents incident:incidents){
        canvas.drawText("Title of incident: "+incident.getTitle(), 100, startingIndex, paint);
        canvas.drawText("Description of incident: "+incident.getDescription(), 100, startingIndex+20, paint);
        startingIndex=startingIndex+50;
    }



        pdfDocument.finishPage(myPage);



        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)+ "/IncidentReport.pdf");

        try {

            // after creating a file name we will
            // write our PDF file to that location.

            pdfDocument.writeTo(new FileOutputStream(file));

            // below line is to print toast message
            // on completion of PDF generation.
            Toast.makeText(IncidentActivity.this, "PDF file generated successfully.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            // below line is used
            // to handle error
            e.printStackTrace();
        }
        // after storing our pdf to that
        // location we are closing our PDF file.
        pdfDocument.close();
    }
}