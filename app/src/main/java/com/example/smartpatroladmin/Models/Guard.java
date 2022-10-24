package com.example.smartpatroladmin.Models;

import android.net.Uri;

public class Guard {
    String guardName;
    Boolean isGuardApproved;
    Uri guardProfilePicture;

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
