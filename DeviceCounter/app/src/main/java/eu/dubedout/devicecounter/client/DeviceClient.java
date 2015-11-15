package eu.dubedout.devicecounter.client;

import java.util.List;

import eu.dubedout.devicecounter.bo.Device;
import eu.dubedout.devicecounter.helper.ResponseCallback;
import eu.dubedout.devicecounter.helper.ResponseHandler;

public interface DeviceClient {
    void setNewDevice(Device newDevice, ResponseHandler callback);

    void setNewUser(Device newUserName, ResponseHandler callback);

    void getDevices(ResponseCallback<List<Device>> responseHandler);
}
