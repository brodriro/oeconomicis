package me.brian.oeconomicis.views.category;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.brian.domain.entities.Category;
import me.brian.oeconomicis.R;
import me.brian.oeconomicis.views.BaseActivity;

public class CategoryActivity extends BaseActivity implements CategoryPresenter.View {

    public static Intent getCallIntent(Context context) {
        return new Intent(context, CategoryActivity.class);
    }

    @BindView(R.id.input_name_category)
    EditText category;
    @BindView(R.id.button_create_category)
    Button createButton;

    @Inject
    CategoryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
    }

    public void enabledField(boolean enabled){
        category.setEnabled(enabled);
        createButton.setEnabled(enabled);
    }

    @OnClick(R.id.button_create_category)
    public void onCreateCategory(View view) {
        enabledField(false);
        presenter.onCreateCategory(category.getText().toString());
    }

    @Override
    public void onError(String message) {
        Snackbar.make(createButton, message, Snackbar.LENGTH_SHORT).show();
        enabledField(true);
    }

    @Override
    public void onSuccess(List<Category> categories) {
        setResult(RESULT_OK);
        finish();
    }
}
