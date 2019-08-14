package me.brian.oeconomicis.views.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.brian.oeconomicis.R;
import me.brian.oeconomicis.views.BaseActivity;

public class RegisterActivity extends BaseActivity implements RegisterPresenter.View {

    public static Intent getCallIntent(Context context) {
        return new Intent(context, RegisterActivity.class);
    }

    @Inject
    RegisterPresenter presenter;

    @BindView(R.id.register_input_name)
    EditText personName;
    @BindView(R.id.register_input_lastname)
    EditText personLastName;
    @BindView(R.id.register_input_age)
    EditText personAge;
    @BindView(R.id.register_input_username)
    EditText username;
    @BindView(R.id.register_input_password)
    EditText password;
    @BindView(R.id.register_input_pwd)
    EditText pwd;
    @BindView(R.id.register_button)
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.register_button)
    public void onSignUpClick(View view) {
        inputsEnabled(false);

        presenter.onSignUpClick(
                personName.getText().toString(),
                personLastName.getText().toString(),
                personAge.getText().toString(),
                username.getText().toString(),
                password.getText().toString(),
                pwd.getText().toString()
        );
    }

    public void inputsEnabled(boolean enabled) {
        personName.setEnabled(enabled);
        personLastName.setEnabled(enabled);
        personAge.setEnabled(enabled);
        username.setEnabled(enabled);
        password.setEnabled(enabled);
        pwd.setEnabled(enabled);
        registerButton.setEnabled(enabled);
    }

    @Override
    public void onSigUpError(String message) {
        inputsEnabled(true);
        Snackbar.make(registerButton, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSigUpSuccess(String message) {
        Snackbar.make(registerButton, message, Snackbar.LENGTH_SHORT).show();
    }


}
