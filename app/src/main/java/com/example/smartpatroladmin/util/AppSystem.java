package com.example.smartpatroladmin.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.firestore.GeoPoint;

import java.util.List;
import java.util.Locale;

public class AppSystem {
    public static  void redirectActivity(Activity activity, Class nextActivity){
        Intent intent=new Intent(activity, nextActivity);activity.startActivity(intent);}

    public static String getLocationFromString(GeoPoint geoPoint, Context context) {
        Geocoder geocoder=new Geocoder(context, Locale.getDefault());
        List<Address> addresses;
        LatLng p1=null;
        try {
            addresses=geocoder.getFromLocation(geoPoint.getLatitude(),geoPoint.getLongitude(),1);
            return addresses.get(0).getAddressLine(0);
        }
        catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }
}
