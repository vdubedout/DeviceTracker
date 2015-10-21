package eu.dubedout.devicecounter;

import android.app.Application;

import com.parse.Parse;

import java.util.HashMap;

import eu.dubedout.devicecounter.helper.PreferencesHelper;
import eu.dubedout.devicecounter.helper.ServiceRegistry;

public class App extends Application {
    private static final String PARSE_APPLICATION_ID = "eOjAfYckkkxD31cKbNeOIiGDBvGsaqGWWD5wlqyq";
    private static final String PARSE_APPLICATION_KEY = "HkHLLkd0Hb7yVwhjBFKZYi1979y0qVk8xq7jAbzb";

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this,
                PARSE_APPLICATION_ID,
                PARSE_APPLICATION_KEY);

        createServiceRegistry();
    }

    private void createServiceRegistry() {
        HashMap<Class, Object> map = new HashMap<>();
        map.put(PreferencesHelper.class, new PreferencesHelper(this));

        ServiceRegistry.create(map);
    }


}
