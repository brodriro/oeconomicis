package me.brian.oeconomicis.views.transaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import javax.inject.Inject;

import me.brian.domain.entities.Category;
import me.brian.oeconomicis.R;
import me.brian.oeconomicis.views.BaseActivity;
import me.brian.oeconomicis.views.category.CategoryActivity;

public class TransactionActivity extends BaseActivity implements TransactionPresenter.View{

    public static Intent getCallIntent(Context context) {
        return new Intent(context, TransactionActivity.class);
    }

    @Inject
    TransactionPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        initToolbar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onCategoriesReady(List<Category> categories) {
        if (categories.size() == 0){
            startActivity(CategoryActivity.getCallIntent(this));
            return;
        }

    }

    @Override
    public void onError() {
        Snackbar.make(findViewById(R.id.transaction_save), getString(R.string.error), Snackbar.LENGTH_SHORT).show();
    }
}
