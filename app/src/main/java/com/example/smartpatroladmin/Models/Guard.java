package com.example.smartpatroladmin.Models;

import android.net.Uri;

public class Guard {
    String guardName,email,password,uId;
    Boolean isGuardApproved;
    Uri guardProfilePicture;

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
}
