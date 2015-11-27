package eu.dubedout.devicecounter.business;

import android.content.Context;
import android.content.SharedPreferences;

import eu.dubedout.devicecounter.business.bo.Device;
import eu.dubedout.devicecounter.helper.StringHelper;

// TODO: VincentD 15-10-20 register in ServiceRegistryImpl
public class PreferencesService {
    private static final String DEVICE_PREF_NAME = "device";
    private static final String PREF_DEVICE_IDENTIFIER = "device_identifier";
    private static final String PREF_DEVICE_MODEL = "device_model";
    private static final String PREF_DEVICE_REGISTERBY = "register_by";
    private static final String PREF_PARSE_DEVICE_OBJECT_ID = "parse_object_id";

    private Context context;

    public PreferencesService(Context context) {
        this.context = context;
    }

    public void registerDevice(Device device) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences(DEVICE_PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(PREF_DEVICE_IDENTIFIER, device.getIdentifier());
        editor.putString(PREF_DEVICE_MODEL, device.getModel());
        editor.putString(PREF_DEVICE_REGISTERBY, device.getRegisteredBy());
        editor.apply();
    }

    public Device getDeviceRegistered() {
        SharedPreferences pref = this.context.getSharedPreferences(DEVICE_PREF_NAME, Context.MODE_PRIVATE);
        String deviceId = pref.getString(PREF_DEVICE_IDENTIFIER, "");
        String deviceModel = pref.getString(PREF_DEVICE_MODEL, "");
        String deviceRegisteredBy = pref.getString(PREF_DEVICE_REGISTERBY, "");
        if (!StringHelper.isEmpty(deviceId)
                && !StringHelper.isEmpty(deviceModel)
                && !StringHelper.isEmpty(deviceRegisteredBy)) {
            Device device = new Device(deviceId, deviceModel);
            device.setRegisteredBy(deviceRegisteredBy);
            return device;
        }
        return Device.EMPTY_DEVICE;
    }

    public void registerParseDeviceId(String deviceObjectId) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences(DEVICE_PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(PREF_PARSE_DEVICE_OBJECT_ID, deviceObjectId);
        editor.apply();
    }

    public String getParseDeviceId() {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(DEVICE_PREF_NAME, Context.MODE_PRIVATE);
        String objectId = sharedPreferences.getString(PREF_PARSE_DEVICE_OBJECT_ID, "");
        return objectId;
    }
}
