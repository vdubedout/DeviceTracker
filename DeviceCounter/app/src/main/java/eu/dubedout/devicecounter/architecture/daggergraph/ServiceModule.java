package eu.dubedout.devicecounter.architecture.daggergraph;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import eu.dubedout.devicecounter.business.PreferencesService;
import eu.dubedout.devicecounter.client.DeviceClient;
import eu.dubedout.devicecounter.client.DeviceClientImpl;

@Module
public class ServiceModule {
    @Provides
    PreferencesService providePreferencesService(Context context) {
        return new PreferencesService(context);
    }

    @Provides
    DeviceClient provideDeviceClient(PreferencesService preferencesService) {
        return new DeviceClientImpl(preferencesService);
    }
}
