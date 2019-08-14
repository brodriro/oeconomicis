package me.brian.oeconomicis.views.register;

import android.content.Context;

import javax.inject.Inject;

import dagger.Reusable;
import me.brian.oeconomicis.R;
import me.brian.oeconomicis.views.BasePresenter;

@Reusable
public class RegisterPresenter extends BasePresenter<RegisterPresenter.View> {

    @Inject
    Context context;

    @Inject
    public RegisterPresenter(View view) {
        super(view);
    }

    public void onSignUpClick(String name, String lastname, String age, String username, String password, String pwd) {
        if (name == null || lastname == null || age == null || username == null || password == null || pwd == null) {
            getView().onSigUpError(context.getString(R.string.complete_all_fields));
            return;
        }

        if (name.trim().isEmpty() || lastname.trim().isEmpty() || age.trim().isEmpty() || username.trim().isEmpty() || password.trim().isEmpty() || pwd.trim().isEmpty()){
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

        if (!password.equals(pwd)){
            getView().onSigUpError(context.getString(R.string.password_not_equals));
            return;
        }

        getView().onSigUpSuccess(context.getString(R.string.register_success));

    }

    public interface View extends BasePresenter.View {
        void onSigUpError(String message);
        void onSigUpSuccess(String message);
    }
}
