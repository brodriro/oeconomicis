package me.rzknairb.oeconomicis.di.modules;


import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;
import me.rzknairb.domain.repositories.BalanceLocalRepositoryImp;
import me.rzknairb.domain.repositories.CategoryLocalRepositoryImp;
import me.rzknairb.domain.repositories.HistoryLocalRepositoryImp;
import me.rzknairb.domain.repositories.UserLocalRepositoryImp;
import r.brian.data.local.repositories.BalanceLocalRepository;
import r.brian.data.local.repositories.CategoryLocalRepository;
import r.brian.data.local.repositories.HistoryLocalRepository;
import r.brian.data.local.repositories.UserLocalRepository;

@Module//(includes = AndroidInjectionModule.class)
public class AppModule {

    @Provides //scope is not necessary for parameters stored within the module
    public Context context(Application application) {
        return application;
    }

    @Provides
    @Singleton
    UserLocalRepositoryImp providesUserDatabaseRepository(UserLocalRepository loginLocalRepository) {
        return loginLocalRepository;
    }

    @Provides
    @Singleton
    CategoryLocalRepositoryImp providesCategoryDatabaseRepository(CategoryLocalRepository categoryLocalRepository) {
        return categoryLocalRepository;
    }

    @Provides
    @Singleton
    BalanceLocalRepositoryImp providesBalanceDatabaseRepository(BalanceLocalRepository balanceLocalRepository) {
        return balanceLocalRepository;
    }

    @Provides
    @Singleton
    HistoryLocalRepositoryImp providesHistoryDatabaseRepository(HistoryLocalRepository historyLocalRepository) {
        return historyLocalRepository;
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