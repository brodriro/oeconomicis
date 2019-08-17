package me.brian.oeconomicis.views.transaction;

import android.util.Log;

import com.uber.autodispose.AutoDispose;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.brian.domain.entities.Category;
import me.brian.domain.usecases.CategoryUseCase;
import me.brian.oeconomicis.views.BasePresenter;

@Reusable
public class TransactionPresenter extends BasePresenter<TransactionPresenter.View> {

    private static final String TAG = TransactionPresenter.class.getSimpleName();

    @Inject
    public TransactionPresenter(View view) {
        super(view);
    }

    @Inject
    CategoryUseCase categoryUseCase;

    public void start() {
        try {
            categoryUseCase.getAll()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .as(AutoDispose.autoDisposable(getView()))
                    .subscribe(categories -> {
                        if (categories == null) categories = new ArrayList<>();
                        getView().onCategoriesReady(categories);
                    }, throwable -> getView().onError());
        } catch (Exception e) {
            getView().onError();
            Log.e(TAG, "getAllCategories", e);
        }
    }

    public interface View extends BasePresenter.View {
        void onCategoriesReady(List<Category> categories);

        void onError();
    }
}
