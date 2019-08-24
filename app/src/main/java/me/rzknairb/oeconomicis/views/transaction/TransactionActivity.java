package me.rzknairb.oeconomicis.views.transaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.rzknairb.domain.entities.Category;
import me.rzknairb.oeconomicis.R;
import me.rzknairb.oeconomicis.views.BaseActivity;
import me.rzknairb.oeconomicis.views.category.CategoryActivity;

public class TransactionActivity extends BaseActivity implements TransactionPresenter.View {

    private static final int ACTIVITY_RESULT = 1000;

    public static Intent getCallIntent(Context context) {
        return new Intent(context, TransactionActivity.class);
    }

    @Inject
    TransactionPresenter presenter;

    @BindView(R.id.transaction_amount)
    EditText amount;
    @BindView(R.id.transaction_spinner_categories)
    AppCompatSpinner spinner;
    @BindView(R.id.transaction_description)
    EditText description;
    @BindView(R.id.transaction_save)
    Button buttonSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        ButterKnife.bind(this);
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

    @OnClick(R.id.transaction_save)
    public void onTransactionSave(View view) {
        presenter.onTransactionSave(amount.getText().toString(), description.getText().toString(), spinner.getSelectedItemPosition());
    }

    @Override
    public void onCategoriesReady(List<Category> categories) {
        if (categories.size() == 0) {
            startActivityForResult(CategoryActivity.getCallIntent(this), ACTIVITY_RESULT);
            return;
        }

        ArrayAdapter<Category> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        spinner.setAdapter(arrayAdapter);
    }

    @Override
    public void onError() {
        Snackbar.make(findViewById(R.id.transaction_save), getString(R.string.error), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        finish();
    }

    @Override
    public void onInputRequired(String message) {
        Snackbar.make(buttonSave, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onCategoryIsEmpty() {
        startActivityForResult(CategoryActivity.getCallIntent(this), ACTIVITY_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == ACTIVITY_RESULT) {
            presenter.start();
        }

    }
}
