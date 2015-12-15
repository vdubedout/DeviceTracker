package eu.dubedout.devicecounter.client;

import java.util.List;

import eu.dubedout.devicecounter.business.bo.Device;
import eu.dubedout.devicecounter.architecture.ResponseHandler;
import eu.dubedout.devicecounter.architecture.ResponseCallback;

public interface DeviceClient {
    void setNewDevice(Device newDevice, ResponseCallback callback);

    void registerNewUserToDevice(Device newUserName, ResponseCallback callback);

    void getDevices(ResponseHandler<List<Device>> responseHandler);
}
