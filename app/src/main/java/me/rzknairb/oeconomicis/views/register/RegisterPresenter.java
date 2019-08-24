package me.rzknairb.oeconomicis.views.register;

import android.content.Context;

import com.uber.autodispose.AutoDispose;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.rzknairb.domain.entities.User;
import me.rzknairb.domain.usecases.LoginUseCase;
import me.rzknairb.oeconomicis.R;
import me.rzknairb.oeconomicis.views.BasePresenter;

@Reusable
public class RegisterPresenter extends BasePresenter<RegisterPresenter.View> {

    @Inject
    Context context;
    @Inject
    LoginUseCase loginUseCase;

    @Inject
    public RegisterPresenter(View view) {
        super(view);
    }

    public void onSignUpClick(String name, String lastname, String age, String username, String password, String pwd) {
        if (name == null || lastname == null || age == null || username == null || password == null || pwd == null) {
            getView().onSigUpError(context.getString(R.string.complete_all_fields));
            return;
        }

        if (name.trim().isEmpty() || lastname.trim().isEmpty() || age.trim().isEmpty() || username.trim().isEmpty() || password.trim().isEmpty() || pwd.trim().isEmpty()) {
            getView().onSigUpError(context.getString(R.string.complete_all_fields));
            return;
        }

        if (username.length() < 6) {
            getView().onSigUpError(context.getString(R.string.username_length));
            return;
        }

        if (password.length() < 6) {
            getView().onSigUpError(context.getString(R.string.password_length));
            return;
        }

        if (!password.equals(pwd)) {
            getView().onSigUpError(context.getString(R.string.password_not_equals));
            return;
        }

        try {
            final User _user = new User(name, lastname, age, username, password);
            loginUseCase.findUser(username)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .as(AutoDispose.autoDisposable(getView()))
                    .subscribe(isCreated -> {
                        if (isCreated) {
                            getView().onSigUpError(context.getString(R.string.user_already_exist));
                        } else {
                            loginUseCase.createUser(_user)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .as(AutoDispose.autoDisposable(getView()))
                                    .subscribe(user -> {
                                        if (loginUseCase.getCurrentUser() != null)
                                            getView().onSigUpSuccess(context.getString(R.string.register_success));
                                        else
                                            getView().onSigUpError(context.getString(R.string.sign_up_error));
                                    }, throwable -> getView().onSigUpError(context.getString(R.string.sign_up_error)));
                        }
                    });
        } catch (Exception e) {
            getView().onSigUpError(context.getString(R.string.sign_up_error));
            e.printStackTrace();
        }

    }

    public interface View extends BasePresenter.View {
        void onSigUpError(String message);

        void onSigUpSuccess(String message);
    }
}
