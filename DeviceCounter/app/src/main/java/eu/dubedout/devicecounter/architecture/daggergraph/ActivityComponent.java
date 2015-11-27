package eu.dubedout.devicecounter.architecture.daggergraph;

import android.support.v7.app.AppCompatActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    //exposed to sub graphs
    AppCompatActivity activity();
}
