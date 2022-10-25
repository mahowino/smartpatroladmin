package com.example.smartpatroladmin.Interface;

import com.example.smartpatroladmin.Models.Guard;
import com.example.smartpatroladmin.Models.Incidents;

import java.util.List;

public interface IncidentRetriever {
    void onSuccess(List<Incidents> incidents);
}
