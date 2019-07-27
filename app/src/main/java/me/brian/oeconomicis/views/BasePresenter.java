package me.brian.oeconomicis.views;

import com.uber.autodispose.ScopeProvider;

public abstract class BasePresenter<T extends BasePresenter.View> {

    private View view;

    public BasePresenter(View view) {
        this.view = view;
    }

    public interface View extends ScopeProvider {
    }

    public T getView() {
        return (T) view;
    }
}