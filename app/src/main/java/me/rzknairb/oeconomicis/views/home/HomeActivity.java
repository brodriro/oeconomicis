package me.rzknairb.oeconomicis.views.home;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.rzknairb.domain.entities.Balance;
import me.rzknairb.domain.entities.History;
import me.rzknairb.domain.entities.User;
import me.rzknairb.oeconomicis.R;
import me.rzknairb.oeconomicis.views.BaseActivity;
import me.rzknairb.oeconomicis.views.category.CategoryActivity;
import me.rzknairb.oeconomicis.views.transaction.TransactionActivity;

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
    @BindView(R.id.total_amount)
    TextView totalAmount;
    @BindView(R.id.recyclerViewCategories)
    RecyclerView recyclerViewCategories;
    @BindView(R.id.recyclerViewHistory)
    RecyclerView recyclerViewHistory;

    private HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initMenu();
        setUpViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        homePresenter.start();
    }

    private void setUpViews() {
        adapter = new HistoryAdapter(getApplicationContext(), new ArrayList<>());
        recyclerViewHistory.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this);
        recyclerViewHistory.setLayoutManager(layoutManager);
        recyclerViewHistory.setHasFixedSize(true);
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
            startActivity(CategoryActivity.getCallIntent(this));
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

    @Override
    public void onBalanceIsReady(Balance balance) {
        totalAmount.setText(String.valueOf(balance.getTotal()));
    }

    @Override
    public void onError() {

    }

    @Override
    public void onHistoryComplete(List<History> histories) {
        Log.e("onHistoryComplete", histories.size()+ "");
        adapter.setList(histories);
    }
}
