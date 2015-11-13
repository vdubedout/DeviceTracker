package eu.dubedout.devicecounter.bo;

import eu.dubedout.devicecounter.helper.StringHelper;

public class Device {
    public static final String REGISTER_BY_DEFAULT = "ypgmobiledevteam@gmail.com";
    public static Device EMPTY_DEVICE = new Device();

    private String identifier = "";
    private String model = "";
    private String lastUser = "";
    private String registeredBy = "";

    private Device() {
    }

    public Device(String identifier, String model) {
        this(identifier, model, "", 0);
    }

    public Device(String identifier, String model, String lastUser, long lastUpdatedTimestamp) {
        this.identifier = identifier;
        this.model = model;
        this.lastUser = lastUser;
        this.registeredBy = REGISTER_BY_DEFAULT;
    }

    public String getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(String registeredBy) {
        this.registeredBy = registeredBy;
    }

    public void setUser(String user) {
        this.lastUser = user;
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
