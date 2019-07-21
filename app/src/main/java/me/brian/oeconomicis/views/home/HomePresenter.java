package me.brian.oeconomicis.views.home;

import javax.inject.Inject;

import me.brian.oeconomicis.views.BasePresenter;

public class HomePresenter extends BasePresenter {

    @Inject
    public HomePresenter(BasePresenter.View view) {
        super(view);
    }

    public interface View {

    }
}
