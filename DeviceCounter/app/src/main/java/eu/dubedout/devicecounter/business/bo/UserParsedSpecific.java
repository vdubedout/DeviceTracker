package eu.dubedout.devicecounter.business.bo;

import com.parse.ParseUser;

public class UserParsedSpecific implements User {
    private String username;
    private String email;
    private ParseUser parseUser;

    public UserParsedSpecific(ParseUser parseUser) {
        this.parseUser = parseUser;
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
}
