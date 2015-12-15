package eu.dubedout.devicecounter.business.bo;

import java.util.Date;

import eu.dubedout.devicecounter.helper.StringHelper;

public class Device {
    public static Device EMPTY_DEVICE = new Device();

    private String identifier = "";
    private String model = "";
    private String currentUser = "";
    private Date updatedAt;

    private Device() {

    }

    public Device(String identifier, String model) {
        this(identifier, model, "", new Date(System.currentTimeMillis()));
    }


    public Device(String identifier, String model, String currentUser, Date date) {
        this.identifier = identifier;
        this.model = model;
        this.currentUser = currentUser;
        this.updatedAt = date;
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

        public Device build() {
            return new Device(identifier, model, currentUser, date);
        }
    }
}
