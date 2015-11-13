package eu.dubedout.devicecounter.client;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import eu.dubedout.devicecounter.bo.Device;
import eu.dubedout.devicecounter.helper.PreferencesHelper;
import eu.dubedout.devicecounter.helper.ResponseHandler;
import eu.dubedout.devicecounter.helper.StringHelper;

public class DeviceClientImpl implements DeviceClient {
    private static final String PARSE_OBJECT_ID = "objectId";
    private static final String PARSE_DEVICE_MODEL = "model";
    private static final String PARSE_DEVICE_IDENTIFIER = "identifier";
    private static final String PARSE_DEVICE_LAST_USER = "lastUser";
    private static final String PARSE_DEVICE_OBJECT = "Device";
    private static final String PARSE_DEVICE_REGISTERED_BY_EMAIL = "registeredByEmail";
    private String deviceObjectId;
    private PreferencesHelper preferencesHelper;

    public DeviceClientImpl(PreferencesHelper preferencesHelper) {
        this.preferencesHelper = preferencesHelper;
        deviceObjectId = preferencesHelper.getParseDeviceId();
    }

    @Override
    public void setNewDevice(Device newDevice, final ResponseHandler callback) {
        final ParseObject parseDevice = getParseObjectFrom(newDevice);
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
    public void setNewUser(Device newUserNameDevice, final ResponseHandler callback) {
        getParseObjectFrom(newUserNameDevice).saveInBackground(new SaveCallback() {
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

    private ParseObject getParseObjectFrom(Device device) {
        ParseObject parseDevice = new ParseObject(PARSE_DEVICE_OBJECT);
        addParseField(parseDevice, PARSE_DEVICE_IDENTIFIER, device.getIdentifier());
        addParseField(parseDevice, PARSE_DEVICE_MODEL, device.getModel());
        addParseField(parseDevice, PARSE_DEVICE_LAST_USER, device.getLastUser());
        addParseField(parseDevice, PARSE_DEVICE_REGISTERED_BY_EMAIL, device.getRegisteredBy());
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
    public List<Device> getDevices() {
        return new ArrayList<>();
    }
}
