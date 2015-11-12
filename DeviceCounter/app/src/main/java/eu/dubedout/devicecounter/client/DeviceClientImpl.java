package eu.dubedout.devicecounter.client;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

import eu.dubedout.devicecounter.bo.Device;
import eu.dubedout.devicecounter.helper.StringHelper;

public class DeviceClientImpl implements DeviceClient {
    private static final String PARSE_DEVICE_MODEL = "model";
    private static final String PARSE_DEVICE_IDENTIFIER = "identifier";
    private static final String PARSE_DEVICE_LAST_USER = "lastUser";
    private static final String PARSE_DEVICE_OBJECT = "Device";
    private static final String PARSE_DEVICE_REGISTERED_BY_EMAIL = "registeredByEmail";

    @Override
    public void setNewDevice(Device newDevice) {
        getParseObjectFrom(newDevice).saveInBackground(); // TODO: VincentD 15-10-20 Do with a SaveCallback
    }

    private ParseObject getParseObjectFrom(Device device) {
        ParseObject parseDevice = new ParseObject(PARSE_DEVICE_OBJECT);
        addParseField(parseDevice, PARSE_DEVICE_IDENTIFIER, device.getIdentifier());
        addParseField(parseDevice, PARSE_DEVICE_MODEL, device.getModel());
        addParseField(parseDevice, PARSE_DEVICE_LAST_USER, device.getLastUser());
        addParseField(parseDevice, PARSE_DEVICE_REGISTERED_BY_EMAIL, device.getRegisteredBy());
        return parseDevice;
    }

    private ParseObject addParseField(ParseObject parseObject, String fieldName, String fieldValue) {
        if (!StringHelper.isEmpty(fieldName) && !StringHelper.isEmpty(fieldValue)) {
            parseObject.put(fieldName, fieldValue);
        }
        return parseObject;
    }

    @Override
    public List<Device> getDevices() {
        return new ArrayList<>();
    }
}
