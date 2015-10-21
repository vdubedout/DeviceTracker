package eu.dubedout.devicecounter.client;

import java.util.ArrayList;
import java.util.List;

import eu.dubedout.devicecounter.bo.Device;

public class DeviceClient {
    public void setNewDevice(Device newDevice) {
        newDevice.toParseObject().saveInBackground(); //// TODO: VincentD 15-10-20 Do with a SaveCallback
    }

    public List<Device> getDevices() {
        return new ArrayList<>();
    }
}
