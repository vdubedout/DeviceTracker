package eu.dubedout.devicecounter;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    private static final String PARSE_APPLICATION_ID = "123";
    private static final String PARSE_APPLICATION_KEY = "toto";

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this,
                PARSE_APPLICATION_ID,
                PARSE_APPLICATION_KEY);
    }
}
