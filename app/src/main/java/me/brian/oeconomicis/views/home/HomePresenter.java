package me.brian.oeconomicis.views.home;

import javax.inject.Inject;

import dagger.Reusable;
import me.brian.domain.entities.User;
import me.brian.domain.usecases.LoginUseCase;
import me.brian.oeconomicis.views.BasePresenter;

@Reusable
public class HomePresenter extends BasePresenter<HomePresenter.View> {

    @Inject
    public HomePresenter(View view) {
        super(view);
    }

    @Inject
    LoginUseCase loginUseCase;

    private User currentUser;

    public void start() {
        currentUser = loginUseCase.getCurrentUser();

        getView().onSessionComplete(getCurrentUser());
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public interface View extends BasePresenter.View {
        void onSessionComplete(User user);
    }
}
