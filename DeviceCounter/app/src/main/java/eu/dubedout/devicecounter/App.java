package eu.dubedout.devicecounter;

import android.app.Application;

import com.parse.Parse;

import java.util.HashMap;

import eu.dubedout.devicecounter.architecture.daggergraph.ApplicationModule;
import eu.dubedout.devicecounter.architecture.daggergraph.DaggerServiceComponent;
import eu.dubedout.devicecounter.architecture.serviceregistry.ServiceRegistryImpl;
import eu.dubedout.devicecounter.business.PreferencesService;
import eu.dubedout.devicecounter.client.DeviceClient;
import eu.dubedout.devicecounter.client.DeviceClientImpl;


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

        DaggerServiceComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    private void createServiceRegistry() {
        serviceRegistry = new ServiceRegistryImpl();

        HashMap<Class, Object> map = new HashMap<>();
        PreferencesService preferencesService = new PreferencesService(this);
        map.put(PreferencesService.class, preferencesService);
        map.put(DeviceClient.class, new DeviceClientImpl(preferencesService));
        serviceRegistry.create(map);
    }

    public static ServiceRegistryImpl getServiceRegistry() {
        return serviceRegistry;
    }

    public static <T> T getInstance(Class<T> clazz) {
        return getServiceRegistry().getInstance(clazz);
    }
}
