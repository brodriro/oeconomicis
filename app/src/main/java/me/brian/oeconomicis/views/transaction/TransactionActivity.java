package me.brian.oeconomicis.views.transaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import javax.inject.Inject;

import me.brian.oeconomicis.R;
import me.brian.oeconomicis.views.BaseActivity;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
