package eu.dubedout.devicecounter.architecture.daggergraph;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides @PerActivity AppCompatActivity activity() {
        return this.activity;
    }
}
