package me.brian.oeconomicis.views.login;

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import me.brian.domain.entities.User;
import me.brian.domain.repositories.UserDatabaseRepository;
import me.brian.oeconomicis.R;
import me.brian.oeconomicis.views.BaseActivity;

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

        try {
            boolean isSaved = userLocalRepository.saveUser(new User(1, "brian", "b.rodrig", "123456"));
            if (isSaved) {
                User user = userLocalRepository.getUser();
                Log.e("User", user.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
