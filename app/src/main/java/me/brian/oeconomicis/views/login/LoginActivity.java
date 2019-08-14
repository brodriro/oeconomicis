package me.brian.oeconomicis.views.login;

import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.brian.domain.repositories.UserDatabaseRepository;
import me.brian.oeconomicis.R;
import me.brian.oeconomicis.views.BaseActivity;
import me.brian.oeconomicis.views.home.HomeActivity;
import me.brian.oeconomicis.views.register.RegisterActivity;

public class LoginActivity extends BaseActivity implements LoginPresenter.View {

    @Inject
    LoginPresenter presenter;

    @Inject
    UserDatabaseRepository userLocalRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.LoginTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        /*try {
            boolean isSaved = userLocalRepository.saveUser(new User(1, "brian", "b.rodrig", "123456"));
            if (isSaved) {
                User user = userLocalRepository.getUser();
                Log.e("User", user.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @OnClick(R.id.buttonLogin)
    public void onLoginClick(View v) {
        startActivity(HomeActivity.getCallIntent(this));
        finish();
    }

    @OnClick(R.id.login_signUp)
    public void onSignUpClick(View v) {
        startActivity(RegisterActivity.getCallIntent(this));
        finish();
    }
}
