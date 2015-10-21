package eu.dubedout.devicecounter.bo;

import com.parse.ParseObject;

import eu.dubedout.devicecounter.helper.StringHelper;

public class Device {
    private String identifier;
    private String model;
    private String lastUser;
    private long lastUpdatedTimestamp;
    public static Device EMPTY_DEVICE = new Device();

    private Device() {
        this.identifier = "";
        this.model = "";
    }

    public Device(String identifier, String model) {
        this.identifier = identifier;
        this.model = model;
    }

    public Device(String deviceId, String model, String lastUser, long lastUpdatedTimestamp) {
        this.identifier = deviceId;
        this.model = model;
        this.lastUser = lastUser;
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }

    public long getLastUpdatedTimestamp() {
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

    public ParseObject toParseObject() {
        ParseObject device = new ParseObject("Device");
        addParseField(device, "identifier", identifier);
        addParseField(device, "model", model);
        addParseField(device, "lastUser", lastUser);
        return device;
    }

    private ParseObject addParseField(ParseObject parseObject, String fieldName, String fieldValue) {
        if (!StringHelper.isEmpty(fieldName) && !StringHelper.isEmpty(fieldValue)) {
            parseObject.put(fieldName, fieldValue);
        }
        return parseObject;
    }

    public boolean isEmpty() {
        if (StringHelper.isEmpty(identifier) || StringHelper.isEmpty(model)) {
            return true;
        }
        return false;
    }

}
