package me.brian.oeconomicis.views;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.uber.autodispose.lifecycle.CorrespondingEventsFunction;
import com.uber.autodispose.lifecycle.LifecycleEndedException;
import com.uber.autodispose.lifecycle.LifecycleScopeProvider;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.annotations.Nullable;
import io.reactivex.subjects.BehaviorSubject;

/*
Basado en https://github.com/uber/AutoDispose/tree/master/sample/src/main/java/com/uber/autodispose/recipes
con soporte añadido para Dagger
 */
@SuppressLint("Registered")
public class BaseActivity extends DaggerAppCompatActivity implements LifecycleScopeProvider<BaseActivity.ActivityEvent> {

    public enum ActivityEvent {
        CREATE,
        START,
        RESUME,
        PAUSE,
        STOP,
        DESTROY
    }

    private static final CorrespondingEventsFunction<ActivityEvent> CORRESPONDING_EVENTS = activityEvent -> {
        switch (activityEvent) {
            case CREATE:
                return ActivityEvent.DESTROY;
            case START:
                return ActivityEvent.STOP;
            case RESUME:
                return ActivityEvent.PAUSE;
            case PAUSE:
                return ActivityEvent.STOP;
            case STOP:
                return ActivityEvent.DESTROY;
            default:
                throw new LifecycleEndedException("Cannot bind to Activity lifecycle after destroy.");
        }
    };

    private final BehaviorSubject<ActivityEvent> lifecycleEvents = BehaviorSubject.create();

    @Override
    public Observable<ActivityEvent> lifecycle() {
        return lifecycleEvents.hide();
    }

    @Override
    public CorrespondingEventsFunction<ActivityEvent> correspondingEvents() {
        return CORRESPONDING_EVENTS;
    }

    @Nullable
    @Override
    public ActivityEvent peekLifecycle() {
        return lifecycleEvents.getValue();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleEvents.onNext(ActivityEvent.CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifecycleEvents.onNext(ActivityEvent.START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycleEvents.onNext(ActivityEvent.RESUME);
    }

    @Override
    protected void onPause() {
        lifecycleEvents.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    @Override
    protected void onStop() {
        lifecycleEvents.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        lifecycleEvents.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
    }
}