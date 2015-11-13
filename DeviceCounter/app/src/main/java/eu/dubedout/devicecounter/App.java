package eu.dubedout.devicecounter;

import android.app.Application;

import com.parse.Parse;

import java.util.HashMap;

import eu.dubedout.devicecounter.client.DeviceClient;
import eu.dubedout.devicecounter.client.DeviceClientImpl;
import eu.dubedout.devicecounter.helper.PreferencesHelper;
import eu.dubedout.devicecounter.helper.ServiceRegistryImpl;

public class App extends Application {
    private static final String PARSE_APPLICATION_ID = "eOjAfYckkkxD31cKbNeOIiGDBvGsaqGWWD5wlqyq";
    private static final String PARSE_APPLICATION_KEY = "HkHLLkd0Hb7yVwhjBFKZYi1979y0qVk8xq7jAbzb";
    private static ServiceRegistryImpl serviceRegistry;

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this,
                PARSE_APPLICATION_ID,
                PARSE_APPLICATION_KEY);

        createServiceRegistry();
    }

    private void createServiceRegistry() {
        serviceRegistry = new ServiceRegistryImpl();

        HashMap<Class, Object> map = new HashMap<>();
        PreferencesHelper preferencesHelper = new PreferencesHelper(this);
        map.put(PreferencesHelper.class, preferencesHelper);
        map.put(DeviceClient.class, new DeviceClientImpl(preferencesHelper));
        serviceRegistry.create(map);
    }

    public static ServiceRegistryImpl getServiceRegistry() {
        return serviceRegistry;
    }

    public static <T> T getInstance(Class<T> clazz) {
        return getServiceRegistry().getInstance(clazz);
    }


}
