package eu.dubedout.devicecounter.client;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import eu.dubedout.devicecounter.bo.Device;
import eu.dubedout.devicecounter.helper.PreferencesHelper;
import eu.dubedout.devicecounter.helper.ResponseCallback;
import eu.dubedout.devicecounter.helper.ResponseHandler;
import eu.dubedout.devicecounter.helper.StringHelper;

public class DeviceClientImpl implements DeviceClient {
    private static final String PARSE_DEVICE_OBJECT = "Device";
    private static final String PARSE_DEVICE_MODEL = "model";
    private static final String PARSE_DEVICE_IDENTIFIER = "identifier";
    private static final String PARSE_DEVICE_CURRENT_USER = "currentUser";
    private static final String PARSE_DEVICE_REGISTERED_BY = "registeredBy";
    private String deviceObjectId;
    private PreferencesHelper preferencesHelper;

    public DeviceClientImpl(PreferencesHelper preferencesHelper) {
        this.preferencesHelper = preferencesHelper;
        deviceObjectId = preferencesHelper.getParseDeviceId();
    }

    @Override
    public void setNewDevice(final Device newDevice, final ResponseHandler callback) {
        final ParseObject parseDevice = getParseObjectFromDevice(newDevice);
        parseDevice.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    saveDeviceObjectId(parseDevice.getObjectId());
                    callback.onSuccess();
                } else {
                    callback.onFailure(e);
                }
            }
        });
    }

    private void saveDeviceObjectId(String deviceObjectId) {
        if (StringHelper.isEmpty(this.deviceObjectId)) {
            this.deviceObjectId = deviceObjectId;
            preferencesHelper.registerParseDeviceId(deviceObjectId);
        }
    }

    @Override
    public void setNewUser(final Device newUserNameDevice, final ResponseHandler callback) {
        getParseObjectFromDevice(newUserNameDevice).saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    callback.onSuccess();
                } else {
                    callback.onFailure(e);
                }
            }
        });

    }

    private ParseObject getParseObjectFromDevice(Device device) {
        ParseObject parseDevice = new ParseObject(PARSE_DEVICE_OBJECT);
        addParseField(parseDevice, PARSE_DEVICE_IDENTIFIER, device.getIdentifier());
        addParseField(parseDevice, PARSE_DEVICE_MODEL, device.getModel());
        addParseField(parseDevice, PARSE_DEVICE_CURRENT_USER, device.getCurrentUser());
        addParseField(parseDevice, PARSE_DEVICE_REGISTERED_BY, device.getRegisteredBy());
        if (!StringHelper.isEmpty(deviceObjectId)) {
            parseDevice.setObjectId(deviceObjectId);
        }
        return parseDevice;
    }

    private ParseObject addParseField(ParseObject parseObject, String fieldName, String fieldValue) {
        if (!StringHelper.isEmpty(fieldName)) {
            parseObject.put(fieldName, fieldValue);
        }
        return parseObject;
    }

    @Override
    public void getDevices(final ResponseCallback<List<Device>> responseHandler) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(PARSE_DEVICE_OBJECT);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseDevices, ParseException exception) {
                if (parseDevices != null) {
                    responseHandler.onSuccess(getDeviceListFromParseObjectList(parseDevices));
                } else {
                    responseHandler.onFailure(exception);
                }
            }
        });
    }

    private List<Device> getDeviceListFromParseObjectList(List<ParseObject> parseDevices) {
        ArrayList<Device> devices = new ArrayList<>();
        for (ParseObject parseDevice : parseDevices) {
            devices.add(getDeviceFromParseObject(parseDevice));
        }
        return devices;
    }

    private Device getDeviceFromParseObject(ParseObject parseDevice) {
        return new Device.Builder()
                .setIdentifier(parseDevice.getString(PARSE_DEVICE_IDENTIFIER))
                .setModel(parseDevice.getString(PARSE_DEVICE_MODEL))
                .setCurrentUser(parseDevice.getString(PARSE_DEVICE_CURRENT_USER))
                .setRegisteredBy(parseDevice.getString(PARSE_DEVICE_REGISTERED_BY))
                .setLastUpdated(parseDevice.getUpdatedAt())
                .build();
    }
}
