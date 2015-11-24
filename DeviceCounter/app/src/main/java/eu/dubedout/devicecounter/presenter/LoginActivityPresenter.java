package eu.dubedout.devicecounter.presenter;

import android.os.Bundle;

import eu.dubedout.devicecounter.presenter.viewable.LoginActivityViewable;

public class LoginActivityPresenter {
    private LoginActivityViewable viewable;

    public LoginActivityPresenter(LoginActivityViewable viewable) {
        this.viewable = viewable;
    }

    public void onCreate(Bundle savedInstanceState) {
        // TODO: VincentD 15-11-24 handle rotation
        // TODO: VincentD 15-11-24 handle savedInstanceState
    }

    public void onLoginButtonClick() {

    }
}
