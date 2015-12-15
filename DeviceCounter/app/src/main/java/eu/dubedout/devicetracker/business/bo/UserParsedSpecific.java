package eu.dubedout.devicetracker.business.bo;

import android.os.Parcel;

import com.parse.ParseUser;

public class UserParsedSpecific implements User {
    private String username;
    private String email;

    public UserParsedSpecific(ParseUser parseUser) {
        this.username = parseUser.getUsername();
        this.email = parseUser.getEmail();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.email);
    }

    protected UserParsedSpecific(Parcel in) {
        this.username = in.readString();
        this.email = in.readString();
    }

    public static final Creator<UserParsedSpecific> CREATOR = new Creator<UserParsedSpecific>() {
        public UserParsedSpecific createFromParcel(Parcel source) {
            return new UserParsedSpecific(source);
        }

        public UserParsedSpecific[] newArray(int size) {
            return new UserParsedSpecific[size];
        }
    };
}
