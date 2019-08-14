package me.brian.oeconomicis.views.register;

import javax.inject.Inject;

import dagger.Reusable;
import me.brian.oeconomicis.views.BasePresenter;

@Reusable
public class RegisterPresenter extends BasePresenter<RegisterPresenter.View> {

    @Inject
    public RegisterPresenter(View view) {
        super(view);
    }

    public interface View extends BasePresenter.View{

    }
}
