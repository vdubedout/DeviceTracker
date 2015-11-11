package eu.dubedout.devicecounter.client;

import java.util.List;

import eu.dubedout.devicecounter.bo.Device;

public interface DeviceClient {
    void setNewDevice(Device newDevice);

    List<Device> getDevices();
}
