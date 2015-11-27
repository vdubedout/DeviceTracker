package eu.dubedout.devicecounter.architecture.daggergraph;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import eu.dubedout.devicecounter.business.PreferencesService;
import eu.dubedout.devicecounter.client.DeviceClient;

@Singleton
@Component(modules = {ServiceModule.class, ApplicationModule.class})
public interface ServiceComponent {
//    void inject(Context context);

    Context context();
    PreferencesService preferenceService();
    DeviceClient deviceClient();
}
