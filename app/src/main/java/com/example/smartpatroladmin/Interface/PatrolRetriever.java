package com.example.smartpatroladmin.Interface;

import com.example.smartpatroladmin.Models.Guard;
import com.example.smartpatroladmin.Models.Patrols;

import java.util.List;

public interface PatrolRetriever {
    void onSuccess(List<Patrols> patrols);
    void onError(String error);
}
