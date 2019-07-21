package me.brian.oeconomicis.views.login;

import android.os.Bundle;

import me.brian.oeconomicis.R;
import me.brian.oeconomicis.views.BaseActivity;

public class LoginActivity extends BaseActivity implements LoginPresenter.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
