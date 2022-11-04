package com.example.smartpatroladmin.Models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Guard implements Parcelable {
    String guardName,email,password,uId;
    Boolean isGuardApproved;
    Uri guardProfilePicture;

    public Guard() {
    }

    protected Guard(Parcel in) {
        guardName = in.readString();
        email = in.readString();
        password = in.readString();
        uId = in.readString();
        byte tmpIsGuardApproved = in.readByte();
        isGuardApproved = tmpIsGuardApproved == 0 ? null : tmpIsGuardApproved == 1;
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

    public Boolean getGuardApproved() {
        return isGuardApproved;
    }

    public void setGuardApproved(Boolean guardApproved) {
        isGuardApproved = guardApproved;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(guardName);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(uId);
        dest.writeByte((byte) (isGuardApproved == null ? 0 : isGuardApproved ? 1 : 2));
        dest.writeParcelable(guardProfilePicture, flags);
    }
}
