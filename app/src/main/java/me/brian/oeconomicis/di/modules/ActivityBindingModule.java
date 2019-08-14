package me.brian.oeconomicis.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import me.brian.oeconomicis.views.home.HomeActivity;
import me.brian.oeconomicis.views.login.LoginActivity;
import me.brian.oeconomicis.views.register.RegisterActivity;
import me.brian.oeconomicis.views.transaction.TransactionActivity;

@Module(includes = AndroidSupportInjectionModule.class)
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {PresenterViewModule.class})
    abstract LoginActivity loginActivityInjector();

    @ContributesAndroidInjector(modules = {PresenterViewModule.class})
    abstract HomeActivity homeActivityInjector();

    @ContributesAndroidInjector(modules = {PresenterViewModule.class})
    abstract RegisterActivity registerActivityInjector();

    @ContributesAndroidInjector(modules = {PresenterViewModule.class})
    abstract TransactionActivity transactionActivityInjector();
}