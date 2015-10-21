package eu.dubedout.devicecounter.helper;

import android.content.Context;
import android.content.SharedPreferences;

import eu.dubedout.devicecounter.bo.Device;

// TODO: VincentD 15-10-20 register in ServiceRegistry
public class PreferencesHelper {
    private static String DEVICE_PREF_NAME = "device";
    private static String PREF_DEVICE_IDENTIFIER = "device_identifier";
    private static String PREF_DEVICE_MODEL = "device_model";

    private Context context;

    public PreferencesHelper(Context context) {
        this.context = context;
    }

    public void registerDevice(Device device) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences(DEVICE_PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(PREF_DEVICE_IDENTIFIER, device.getIdentifier());
        editor.putString(PREF_DEVICE_MODEL, device.getModel());
        editor.apply();
    }

    public Device getDeviceRegistered() {
        SharedPreferences pref = this.context.getSharedPreferences(DEVICE_PREF_NAME, Context.MODE_PRIVATE);
        String device_id = pref.getString(PREF_DEVICE_IDENTIFIER, null);
        String device_model = pref.getString(PREF_DEVICE_MODEL, null);
        if (device_id != null && device_model != null) {
            return new Device(device_id, device_model);
        }
        return Device.EMPTY_DEVICE;
    }

}
