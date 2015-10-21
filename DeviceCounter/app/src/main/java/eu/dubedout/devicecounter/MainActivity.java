package eu.dubedout.devicecounter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import eu.dubedout.devicecounter.bo.Device;
import eu.dubedout.devicecounter.helper.PreferencesHelper;

public class MainActivity extends AppCompatActivity {
    // TODO: VincentD 15-10-20 get domain name (Filter by domain to display devices
    // TODO: VincentD 15-10-20 register device
    // TODO: VincentD 15-10-20 get list of devices and registered names

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        tempRegisterThisDevice();
    }

    public void tempRegisterThisDevice() {
        if (PreferencesHelper.getDeviceRegistered(this).isEmpty()) {
            PreferencesHelper.registerDevice(this, new Device("temp_id", "temp_model"));
            Snackbar.make(this.findViewById(R.id.activity_main_coordinator), R.string.warning_temp_device_registered, Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
