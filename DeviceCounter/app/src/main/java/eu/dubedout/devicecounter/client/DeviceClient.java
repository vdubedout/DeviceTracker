package eu.dubedout.devicecounter.client;

import java.util.List;

import eu.dubedout.devicecounter.business.bo.Device;
import eu.dubedout.devicecounter.architecture.ResponseCallback;
import eu.dubedout.devicecounter.architecture.ResponseHandler;

public interface DeviceClient {
    void setNewDevice(Device newDevice, ResponseHandler callback);

    void setNewUser(Device newUserName, ResponseHandler callback);

    void getDevices(ResponseCallback<List<Device>> responseHandler);
}
