package me.rzknairb.oeconomicis.di.modules;


import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;
import me.rzknairb.domain.repositories.BalanceLocalRepository;
import me.rzknairb.domain.repositories.CategoryLocalRepository;
import me.rzknairb.domain.repositories.HistoryLocalRepository;
import me.rzknairb.domain.repositories.UserLocalRepository;
import r.brian.data.local.repositories.BalanceLocalRepositoryImp;
import r.brian.data.local.repositories.CategoryLocalRepositoryImp;
import r.brian.data.local.repositories.HistoryLocalRepositoryImp;
import r.brian.data.local.repositories.UserLocalRepositoryImp;

@Module//(includes = AndroidInjectionModule.class)
public class AppModule {

    @Provides //scope is not necessary for parameters stored within the module
    public Context context(Application application) {
        return application;
    }

    @Provides
    @Singleton
    UserLocalRepository providesUserDatabaseRepository(UserLocalRepositoryImp loginLocalRepository) {
        return loginLocalRepository;
    }

    @Provides
    @Singleton
    CategoryLocalRepository providesCategoryDatabaseRepository(CategoryLocalRepositoryImp categoryLocalRepositoryImp) {
        return categoryLocalRepositoryImp;
    }

    @Provides
    @Singleton
    BalanceLocalRepository providesBalanceDatabaseRepository(BalanceLocalRepositoryImp balanceLocalRepositoryImp) {
        return balanceLocalRepositoryImp;
    }

    @Provides
    @Singleton
    HistoryLocalRepository providesHistoryDatabaseRepository(HistoryLocalRepositoryImp historyLocalRepositoryImp) {
        return historyLocalRepositoryImp;
    }

    @Singleton
    @Provides
    RealmConfiguration providerRealm(Application context) {
        Realm.init(context);
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder();
        builder.name("oeconomicis");
        builder.rxFactory(new RealmObservableFactory());
        return builder.build();
    }
}