package com.example.smartpatroladmin.util;

import android.app.Activity;
import android.content.Intent;

public class AppSystem {
    public static  void redirectActivity(Activity activity, Class nextActivity){
        Intent intent=new Intent(activity, nextActivity);activity.startActivity(intent);}

}
