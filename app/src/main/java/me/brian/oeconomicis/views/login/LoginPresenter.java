package me.brian.oeconomicis.views.login;

import android.content.Context;
import android.util.Log;

import com.uber.autodispose.AutoDispose;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.brian.domain.usecases.LoginUseCase;
import me.brian.oeconomicis.R;
import me.brian.oeconomicis.views.BasePresenter;

@Reusable
public class LoginPresenter extends BasePresenter<LoginPresenter.View> {

    private static final String TAG = LoginPresenter.class.getSimpleName();

    @Inject
    public LoginPresenter(View view) {
        super(view);
    }

    @Inject
    Context context;
    @Inject
    LoginUseCase loginUseCase;

    public void onLoginClick(String username, String password) {
        if (username == null || password == null) {
            getView().onLoginError(context.getString(R.string.complete_all_fields));
            return;
        }
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            getView().onLoginError(context.getString(R.string.complete_all_fields));
            return;
        }

        try {
            loginUseCase.loginUser(username, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .as(AutoDispose.autoDisposable(getView()))
                    .subscribe(user -> {
                        if (user == null) {
                            getView().onLoginError(context.getString(R.string.user_doesnt_exist));
                        }else {
                            getView().onLoginSuccess();
                        }
                    }, throwable -> {
                        getView().onLoginError(context.getString(R.string.login_error));
                    });
        } catch (Exception e) {
            getView().onLoginError(context.getString(R.string.login_error));
            Log.e(TAG, "Login", e);
        }

    }

    public void start() {
        if (loginUseCase.getCurrentUser() != null) {
            getView().onLoginSuccess();
        }
    }

    public interface View extends BasePresenter.View {
        void onLoginSuccess();

        void onLoginError(String message);
    }


}
