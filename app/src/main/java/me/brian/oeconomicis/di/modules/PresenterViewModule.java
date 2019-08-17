package me.brian.oeconomicis.di.modules;


import dagger.Binds;
import dagger.Module;
import me.brian.oeconomicis.views.category.CategoryActivity;
import me.brian.oeconomicis.views.category.CategoryPresenter;
import me.brian.oeconomicis.views.home.HomeActivity;
import me.brian.oeconomicis.views.home.HomePresenter;
import me.brian.oeconomicis.views.login.LoginActivity;
import me.brian.oeconomicis.views.login.LoginPresenter;
import me.brian.oeconomicis.views.register.RegisterActivity;
import me.brian.oeconomicis.views.register.RegisterPresenter;
import me.brian.oeconomicis.views.transaction.TransactionActivity;
import me.brian.oeconomicis.views.transaction.TransactionPresenter;

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
