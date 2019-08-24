package me.rzknairb.oeconomicis.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import me.rzknairb.oeconomicis.views.category.CategoryActivity;
import me.rzknairb.oeconomicis.views.home.HomeActivity;
import me.rzknairb.oeconomicis.views.login.LoginActivity;
import me.rzknairb.oeconomicis.views.register.RegisterActivity;
import me.rzknairb.oeconomicis.views.transaction.TransactionActivity;

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

    @ContributesAndroidInjector(modules = {PresenterViewModule.class})
    abstract CategoryActivity categoryActivityInjector();
}