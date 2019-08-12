package me.brian.oeconomicis.di.modules;


import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import me.brian.domain.repositories.UserDatabaseRepository;
import me.brian.oeconomicis.application.OeconomicisApp;
import r.brian.data.local.repositories.UserLocalRepository;

@Module//(includes = AndroidInjectionModule.class)
public class AppModule {
    /*
        @Provides @Singleton com.everis.domain.repositories.LoginRepository provideLoginRepository(KeyManagerUtil keyManagerUtil) {
            return new com.everis.data.repositories.LoginRepository(keyManagerUtil.getKeyManager());
        }*/

    @Provides //scope is not necessary for parameters stored within the module
    public Context context(Application application) {
        return application;
    }

    @Provides
    @Singleton
    UserDatabaseRepository providesUserDatabaseRepository(UserLocalRepository userLocalRepository){
        return userLocalRepository;
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