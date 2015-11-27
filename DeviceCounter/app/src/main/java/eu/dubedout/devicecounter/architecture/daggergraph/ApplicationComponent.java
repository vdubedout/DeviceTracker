package eu.dubedout.devicecounter.architecture.daggergraph;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Singleton;

import dagger.Component;

/***
 * A component whose lifetime is the life of the application. It injects both AndroidApplication and BaseActivity classes.
 */
@Singleton // Only one by application
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(AppCompatActivity activity);

    // Exposed to sub-graphs
    Context context();
}
