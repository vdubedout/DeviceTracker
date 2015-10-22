package eu.dubedout.devicecounter.bo;

import com.parse.ParseObject;

import eu.dubedout.devicecounter.helper.StringHelper;

public class Device {
    private int parseId = 0;
    private String identifier = "";
    private String model = "";
    private String lastUser = "";
    private long lastUpdatedTimestamp = 0;
    private String registeredByEmail = "ypgmobiledevteam@gmail.com";
    public static Device EMPTY_DEVICE = new Device();

    private Device() {
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

    public static final String PARSE_DEVICE_OBJECT = "Device";
    public static final String PARSE_DEVICE_IDENTIFIER = "identifier";
    public static final String PARSE_DEVICE_MODEL = "model";
    public static final String PARSE_DEVICE_LAST_USER = "lastUser";
    public static final String PARSE_DEVICE_REGISTERED_BY_EMAIL = "registeredByEmail";
    public ParseObject toParseObject() {
        ParseObject device = new ParseObject(PARSE_DEVICE_OBJECT);
        addParseField(device, PARSE_DEVICE_IDENTIFIER, identifier);
        addParseField(device, PARSE_DEVICE_MODEL, model);
        addParseField(device, PARSE_DEVICE_LAST_USER, lastUser);
        addParseField(device, PARSE_DEVICE_REGISTERED_BY_EMAIL, registeredByEmail);
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
