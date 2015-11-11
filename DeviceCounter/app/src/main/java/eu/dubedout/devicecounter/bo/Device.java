package eu.dubedout.devicecounter.bo;

import eu.dubedout.devicecounter.helper.StringHelper;

public class Device {
    public static final String REGISTER_BY_DEFAULT = "ypgmobiledevteam@gmail.com";
    public static Device EMPTY_DEVICE = new Device();

    private String identifier = "";
    private String model = "";
    private String lastUser = "";
    private long lastUpdatedTimestamp = 0;
    private String registeredByEmail = "";

    private Device() {
    }

    public Device(String identifier, String model) {
        this(identifier, model, "", 0);
    }

    public Device(String identifier, String model, String lastUser, long lastUpdatedTimestamp) {
        this.identifier = identifier;
        this.model = model;
        this.lastUser = lastUser;
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
        this.registeredByEmail = REGISTER_BY_DEFAULT;
    }

    public String getRegisteredBy() {
        return registeredByEmail;
    }

    public long getLastUpdated() {
        return lastUpdatedTimestamp;
    }

    public Device setUser(String user) {
        if (StringHelper.isEmpty(user)) {
            new RuntimeException("User can't be null or empty");
            return this;
        }
        this.lastUser = user;
        this.lastUpdatedTimestamp = System.currentTimeMillis();
        return this;
    }

    public String getLastUser() {
        return lastUser;
    }

    public String getModel() {
        return model;
    }

    public String getIdentifier() {
        return identifier;
    }

    public boolean isEmpty() {
        if (StringHelper.isEmpty(identifier) || StringHelper.isEmpty(model)) {
            return true;
        }
        return false;
    }

}
