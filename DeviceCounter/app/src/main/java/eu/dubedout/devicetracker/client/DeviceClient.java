package eu.dubedout.devicetracker.client;

import java.util.List;

import eu.dubedout.devicetracker.business.bo.Device;
import eu.dubedout.devicetracker.architecture.ResponseHandler;
import eu.dubedout.devicetracker.architecture.ResponseCallback;

public interface DeviceClient {
    void setNewDevice(Device newDevice, ResponseCallback callback);

    void registerNewUserToDevice(Device newUserName, ResponseCallback callback);

    void getDevices(ResponseHandler<List<Device>> responseHandler);
}
