package me.rzknairb.oeconomicis.di.modules;


import dagger.Binds;
import dagger.Module;
import me.rzknairb.oeconomicis.views.category.CategoryActivity;
import me.rzknairb.oeconomicis.views.category.CategoryPresenter;
import me.rzknairb.oeconomicis.views.home.HomeActivity;
import me.rzknairb.oeconomicis.views.home.HomePresenter;
import me.rzknairb.oeconomicis.views.login.LoginActivity;
import me.rzknairb.oeconomicis.views.login.LoginPresenter;
import me.rzknairb.oeconomicis.views.register.RegisterActivity;
import me.rzknairb.oeconomicis.views.register.RegisterPresenter;
import me.rzknairb.oeconomicis.views.transaction.TransactionActivity;
import me.rzknairb.oeconomicis.views.transaction.TransactionPresenter;

@Module
public abstract class PresenterViewModule {

    @Binds
    abstract LoginPresenter.View provideLoginView(LoginActivity loginActivity);

    @Binds
    abstract HomePresenter.View provideHomeView(HomeActivity homeActivity);

    @Binds
    abstract RegisterPresenter.View provideRegisterView(RegisterActivity registerActivity);

    @Binds
    abstract TransactionPresenter.View provideTransactionView(TransactionActivity transactionActivity);

    @Binds
    abstract CategoryPresenter.View provideCategoryView(CategoryActivity categoryActivity);
}
