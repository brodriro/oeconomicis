package me.rzknairb.oeconomicis.di.modules;


import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;
import me.rzknairb.domain.repositories.BalanceDatabaseRepository;
import me.rzknairb.domain.repositories.CategoryDatabaseRepository;
import me.rzknairb.domain.repositories.HistoryDatabaseRepository;
import me.rzknairb.domain.repositories.UserDatabaseRepository;
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
    UserDatabaseRepository providesUserDatabaseRepository(UserLocalRepository loginLocalRepository) {
        return loginLocalRepository;
    }

    @Provides
    @Singleton
    CategoryDatabaseRepository providesCategoryDatabaseRepository(CategoryLocalRepository categoryLocalRepository) {
        return categoryLocalRepository;
    }

    @Provides
    @Singleton
    BalanceDatabaseRepository providesBalanceDatabaseRepository(BalanceLocalRepository balanceLocalRepository) {
        return balanceLocalRepository;
    }

    @Provides
    @Singleton
    HistoryDatabaseRepository providesHistoryDatabaseRepository(HistoryLocalRepository historyLocalRepository) {
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