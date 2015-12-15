package eu.dubedout.devicetracker.business.bo;

import android.os.Parcelable;

public interface User extends Parcelable {

    String getUsername();

    void setUsername(String username);

    String getEmail();

    void setEmail(String email);
}
