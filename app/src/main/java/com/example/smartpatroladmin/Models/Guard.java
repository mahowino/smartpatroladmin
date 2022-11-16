package com.example.smartpatroladmin.Models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Guard implements Parcelable {
    String guardName, email, password, uId,phoneNumber;
    Uri guardProfilePicture;
    Schedule schedule;

    public Guard(String email, String phoneNumber, String guardName) {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    protected Guard(Parcel in) {
        guardName = in.readString();
        email = in.readString();
        password = in.readString();
        uId = in.readString();
        guardProfilePicture = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Guard> CREATOR = new Creator<Guard>() {
        @Override
        public Guard createFromParcel(Parcel in) {
            return new Guard(in);
        }

        @Override
        public Guard[] newArray(int size) {
            return new Guard[size];
        }
    };

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }



    public Guard() {
    }


    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Uri getGuardProfilePicture() {
        return guardProfilePicture;
    }

    public void setGuardProfilePicture(Uri guardProfilePicture) {
        this.guardProfilePicture = guardProfilePicture;
    }

    public String getGuardName() {
        return guardName;
    }

    public void setGuardName(String guardName) {
        this.guardName = guardName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(guardName);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(uId);
        parcel.writeParcelable(guardProfilePicture, i);
    }
}
