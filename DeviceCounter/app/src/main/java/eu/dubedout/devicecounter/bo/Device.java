package eu.dubedout.devicecounter.bo;

import java.util.Date;

import eu.dubedout.devicecounter.helper.StringHelper;

public class Device {
    public static final String REGISTER_BY_DEFAULT = "ypgmobiledevteam@gmail.com";
    public static Device EMPTY_DEVICE = new Device();

    private String identifier = "";
    private String model = "";
    private String currentUser = "";
    private Date updatedAt;
    private String registeredBy = "";

    private Device() {
    }

    public Device(String identifier, String model) {
        this(identifier, model, "", new Date(System.currentTimeMillis()));
    }

    public Device(String identifier, String model, String currentUser, Date updatedAt) {
        this(identifier,
                model,
                currentUser,
                updatedAt,
                REGISTER_BY_DEFAULT);
    }

    public Device(String identifier, String model, String currentUser, Date date, String registeredBy) {
        this.identifier = identifier;
        this.model = model;
        this.currentUser = currentUser;
        this.updatedAt = date;
        this.registeredBy = registeredBy;
    }

    public String getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(String registeredBy) {
        this.registeredBy = registeredBy;
    }

    public void setUser(String user) {
        this.currentUser = user;
    }

    public String getCurrentUser() {
        return currentUser;
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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public static class Builder {
        private String identifier;
        private String model;
        private String currentUser;
        private Date date;
        private String registeredBy;

        public Builder setIdentifier(String identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setCurrentUser(String currentUser) {
            this.currentUser = currentUser;
            return this;
        }

        public Builder setLastUpdated(Date date) {
            this.date = date;
            return this;
        }

        public Builder setRegisteredBy(String registeredBy) {
            this.registeredBy = registeredBy;
            return this;
        }

        public Device build() {
            return new Device(identifier, model, currentUser, date, registeredBy);
        }
    }
}
