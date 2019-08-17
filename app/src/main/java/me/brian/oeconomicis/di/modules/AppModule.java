package me.brian.oeconomicis.di.modules;


import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import me.brian.domain.repositories.CategoryDatabaseRepository;
import me.brian.domain.repositories.UserDatabaseRepository;
import r.brian.data.local.repositories.CategoryLocalRepository;
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
    CategoryDatabaseRepository proCategoryDatabaseRepository(CategoryLocalRepository categoryLocalRepository) {
        return categoryLocalRepository;
    }

    @Singleton
    @Provides
    RealmConfiguration providerRealm(Application context) {
        Realm.init(context);
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder();
        builder.name("oeconomicis");
        return builder.build();
    }
}