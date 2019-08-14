package me.brian.oeconomicis.views.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import javax.inject.Inject;

import me.brian.oeconomicis.R;
import me.brian.oeconomicis.views.BaseActivity;
import me.brian.oeconomicis.views.login.LoginActivity;

public class RegisterActivity  extends BaseActivity implements RegisterPresenter.View{

    public static Intent getCallIntent(Context context) {
       return new Intent(context, RegisterActivity.class);
    }

    @Inject
    RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
