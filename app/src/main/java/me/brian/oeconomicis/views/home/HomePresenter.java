package me.brian.oeconomicis.views.home;

import javax.inject.Inject;

import dagger.Reusable;
import me.brian.oeconomicis.views.BasePresenter;

@Reusable
public class HomePresenter extends BasePresenter<HomePresenter.View> {

    @Inject
    public HomePresenter(View view) {
        super(view);
    }

    public interface View extends BasePresenter.View {

    }
}
