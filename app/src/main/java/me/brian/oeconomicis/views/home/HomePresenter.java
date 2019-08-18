package me.brian.oeconomicis.views.home;

import android.util.Log;

import com.uber.autodispose.AutoDispose;

import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.brian.domain.entities.Balance;
import me.brian.domain.entities.History;
import me.brian.domain.entities.User;
import me.brian.domain.usecases.HomeUseCase;
import me.brian.domain.usecases.LoginUseCase;
import me.brian.oeconomicis.views.BasePresenter;

@Reusable
public class HomePresenter extends BasePresenter<HomePresenter.View> {

    private static final String TAG = HomePresenter.class.getSimpleName();

    @Inject
    public HomePresenter(View view) {
        super(view);
    }

    @Inject
    LoginUseCase loginUseCase;
    @Inject
    HomeUseCase homeUseCase;

    private User currentUser;

    public void start() {
        currentUser = loginUseCase.getCurrentUser();
        getView().onSessionComplete(getCurrentUser());

        getBalance();
        getHistory();
    }

    private void getHistory() {
        try {
            homeUseCase.getHistory(loginUseCase.getCurrentUser())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .as(AutoDispose.autoDisposable(getView()))
                    .subscribe(
                            histories -> getView().onHistoryComplete(histories),
                            throwable -> getView().onError()
                    );
        } catch (Exception e) {
            Log.e(TAG, "getHistory", e);
        }
    }

    private void getBalance() {
        try {
            homeUseCase.getBalanceByUser(loginUseCase.getCurrentUser())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .as(AutoDispose.autoDisposable(getView()))
                    .subscribe(
                            balance -> getView().onBalanceIsReady(balance),
                            throwable -> Log.e(TAG, "getBalance", throwable)
                    );
        } catch (Exception e) {
            Log.e(TAG, "getBalance", e);
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public interface View extends BasePresenter.View {
        void onSessionComplete(User user);

        void onBalanceIsReady(Balance balance);

        void onError();

        void onHistoryComplete(List<History> histories);
    }
}
