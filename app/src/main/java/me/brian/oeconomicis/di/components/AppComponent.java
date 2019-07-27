package me.brian.oeconomicis.di.components;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import me.brian.oeconomicis.application.OeconomicisApp;
import me.brian.oeconomicis.di.modules.ActivityBindingModule;

@Singleton
@Component(modules = {ActivityBindingModule.class,})
public interface AppComponent extends AndroidInjector<OeconomicisApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    //void inject(MEAApplication meaApplication);
}