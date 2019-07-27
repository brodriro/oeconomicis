package me.brian.oeconomicis.views.login;

import javax.inject.Inject;

import dagger.Reusable;
import me.brian.oeconomicis.views.BasePresenter;

@Reusable
public class LoginPresenter extends BasePresenter<LoginPresenter.View> {

    @Inject
    public LoginPresenter(View view) {
        super(view);
    }

    public interface View extends BasePresenter.View {

    }


}
