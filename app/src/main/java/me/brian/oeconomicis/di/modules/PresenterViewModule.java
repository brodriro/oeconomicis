package me.brian.oeconomicis.di.modules;


import dagger.Binds;
import dagger.Module;
import me.brian.oeconomicis.views.home.HomeActivity;
import me.brian.oeconomicis.views.home.HomePresenter;
import me.brian.oeconomicis.views.login.LoginActivity;
import me.brian.oeconomicis.views.login.LoginPresenter;

@Module
public abstract class PresenterViewModule {

    @Binds
    abstract LoginPresenter.View provideLoginView(LoginActivity loginActivity);

    @Binds
    abstract HomePresenter.View provideHomeView(HomeActivity homeActivity);
}
