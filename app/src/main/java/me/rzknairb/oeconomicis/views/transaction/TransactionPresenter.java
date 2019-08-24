package me.rzknairb.oeconomicis.views.transaction;

import android.content.Context;
import android.util.Log;

import com.uber.autodispose.AutoDispose;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.rzknairb.domain.entities.Category;
import me.rzknairb.domain.entities.History;
import me.rzknairb.domain.usecases.CategoryUseCase;
import me.rzknairb.domain.usecases.LoginUseCase;
import me.rzknairb.domain.usecases.TransactionUseCase;
import me.rzknairb.oeconomicis.R;
import me.rzknairb.oeconomicis.views.BasePresenter;

@Reusable
public class TransactionPresenter extends BasePresenter<TransactionPresenter.View> {

    private static final String TAG = TransactionPresenter.class.getSimpleName();

    @Inject
    public TransactionPresenter(View view) {
        super(view);
    }

    @Inject
    CategoryUseCase categoryUseCase;
    @Inject
    TransactionUseCase transactionUseCase;
    @Inject
    LoginUseCase loginUseCase;
    @Inject
    Context context;

    private List<Category> list;

    public void start() {
        try {
            categoryUseCase.getAll()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .as(AutoDispose.autoDisposable(getView()))
                    .subscribe(categories -> {
                        if (categories == null) categories = new ArrayList<>();
                        list = categories;
                        getView().onCategoriesReady(categories);
                    }, throwable -> getView().onError());
        } catch (Exception e) {
            getView().onError();
            Log.e(TAG, "getAllCategories", e);
        }
    }

    public void onTransactionSave(String amount, String description, int position) {
        if (amount == null || amount.trim().isEmpty()) {
            getView().onInputRequired(context.getString(R.string.amount_required));
            return;
        }

        if (description == null || description.trim().isEmpty()) {
            getView().onInputRequired(context.getString(R.string.description_required));
            return;
        }

        if (list.size() == 0) {
            getView().onCategoryIsEmpty();
            return;
        }

        double _amount = Double.parseDouble(amount);
        Category category = list.get(position);


        History history = new History(
                loginUseCase.getCurrentUser().getId(), category.getId(), _amount,
                category.getName(), new Date(), description
        );
        try {
            transactionUseCase.createHistory(history)
                    .subscribeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .as(AutoDispose.autoDisposable(getView()))
                    .subscribe(aBoolean -> {
                        if (aBoolean) {
                            getView().onSuccess();
                            return;
                        }
                        getView().onError();
                    }, throwable -> {
                        getView().onError();
                        Log.e(TAG, "createHistory", throwable);
                    });
        } catch (Exception e) {
            getView().onError();
            Log.e(TAG, "createHistory", e);
        }

    }

    public interface View extends BasePresenter.View {
        void onCategoriesReady(List<Category> categories);

        void onError();

        void onSuccess();

        void onInputRequired(String string);

        void onCategoryIsEmpty();
    }
}
