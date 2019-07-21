package me.brian.oeconomicis.views.login;

import javax.inject.Inject;

import me.brian.oeconomicis.views.BasePresenter;

public class LoginPresenter extends BasePresenter {
    public interface View {

    }

    @Inject
    public LoginPresenter(BasePresenter.View view) {
        super(view);
    }


}
