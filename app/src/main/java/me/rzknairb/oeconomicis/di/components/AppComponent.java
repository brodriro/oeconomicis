package me.rzknairb.oeconomicis.di.components;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import me.rzknairb.oeconomicis.application.OeconomicisApp;
import me.rzknairb.oeconomicis.di.modules.ActivityBindingModule;
import me.rzknairb.oeconomicis.di.modules.AppModule;

@Singleton
@Component(modules = {ActivityBindingModule.class, AppModule.class})
public interface AppComponent extends AndroidInjector<OeconomicisApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

}