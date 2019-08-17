package me.brian.oeconomicis.views.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.brian.oeconomicis.R;
import me.brian.oeconomicis.views.BaseActivity;
import me.brian.oeconomicis.views.home.HomeActivity;
import me.brian.oeconomicis.views.register.RegisterActivity;

public class LoginActivity extends BaseActivity implements LoginPresenter.View {

    @Inject
    LoginPresenter presenter;

    @BindView(R.id.inputLoginUser)
    EditText inputUsername;
    @BindView(R.id.inputLoginPassword)
    EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.LoginTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        presenter.start();

        mockUser();
    }

    private void mockUser() {
        inputUsername.setText("xaiovz");
        inputPassword.setText("123456");

    }

    @OnClick(R.id.buttonLogin)
    public void onLoginClick(View v) {
        presenter.onLoginClick(inputUsername.getText().toString(), inputPassword.getText().toString());
    }

    @OnClick(R.id.login_signUp)
    public void onSignUpClick(View v) {
        startActivity(RegisterActivity.getCallIntent(this));
    }

    @Override
    public void onLoginSuccess() {
        startActivity(HomeActivity.getCallIntent(this));
        finish();
    }

    @Override
    public void onLoginError(String message) {
        Snackbar.make(findViewById(R.id.buttonLogin), message, Snackbar.LENGTH_SHORT).show();
    }
}
