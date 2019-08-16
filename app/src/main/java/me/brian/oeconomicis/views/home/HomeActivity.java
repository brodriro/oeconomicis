package me.brian.oeconomicis.views.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.brian.domain.entities.User;
import me.brian.oeconomicis.R;
import me.brian.oeconomicis.views.BaseActivity;
import me.brian.oeconomicis.views.transaction.TransactionActivity;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomePresenter.View {

    private static final String TAG = HomeActivity.class.getSimpleName();

    public static Intent getCallIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Inject
    HomePresenter homePresenter;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        homePresenter.start();
        initMenu();
    }

    private void initMenu() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_new_category) {

        } else if (id == R.id.nav_assignment) {
            startActivity(TransactionActivity.getCallIntent(this));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.btn_new_amount)
    public void clickNewAssignment(View v) {
        startActivity(TransactionActivity.getCallIntent(this));
    }

    @Override
    public void onSessionComplete(User user) {
        View headerLayout = navigationView.getHeaderView(0);
        TextView title = (TextView) headerLayout.findViewById(R.id.nav_view_username);
        title.setText(String.format("%s %s", user.getName(), user.getLastname()));
        Log.e(TAG, user.toString());
    }
}
