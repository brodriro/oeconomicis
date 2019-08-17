package me.brian.oeconomicis.views.category;

import android.content.Context;
import android.util.Log;

import com.uber.autodispose.AutoDispose;

import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.brian.domain.entities.Category;
import me.brian.domain.usecases.CategoryUseCase;
import me.brian.oeconomicis.R;
import me.brian.oeconomicis.utils.Utils;
import me.brian.oeconomicis.views.BasePresenter;

@Reusable
public class CategoryPresenter extends BasePresenter<CategoryPresenter.View> {

    private static final String TAG = CategoryPresenter.class.getSimpleName();
    @Inject
    Context context;

    @Inject
    CategoryUseCase categoryUseCase;

    @Inject
    public CategoryPresenter(View view) {
        super(view);

    }

    public void onCreateCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            getView().onError(context.getString(R.string.complete_all_fields));
            return;
        }

        try {
            categoryUseCase.createCategory(category, Utils.generateCategoryCode(category))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .as(AutoDispose.autoDisposable(getView()))
                    .subscribe(categories -> {
                        if (categories == null || categories.size() == 0) {
                            getView().onError(context.getString(R.string.error));
                            return;
                        }
                        getView().onSuccess(categories);
                    }, throwable -> getView().onError(context.getString(R.string.error)));
        } catch (Exception e) {
            getView().onError(context.getString(R.string.error));
            Log.e(TAG, "create category", e);
        }
    }


    public interface View extends BasePresenter.View {

        void onError(String string);

        void onSuccess(List<Category> categories);
    }
}
