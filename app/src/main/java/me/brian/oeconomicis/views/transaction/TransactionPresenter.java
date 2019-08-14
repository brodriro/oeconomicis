package me.brian.oeconomicis.views.transaction;

import javax.inject.Inject;

import dagger.Reusable;
import me.brian.oeconomicis.views.BasePresenter;

@Reusable
public class TransactionPresenter extends BasePresenter<TransactionPresenter.View> {

    @Inject
    public TransactionPresenter(View view) {
        super(view);
    }

    public interface View extends BasePresenter.View{

    }
}
