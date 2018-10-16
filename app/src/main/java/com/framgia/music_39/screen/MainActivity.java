package com.framgia.music_39.screen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.framgia.music_39.R;
import com.framgia.music_39.screen.home.HomeFragment;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {
    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mActionBar = getSupportActionBar();
        assert mActionBar != null;
        mActionBar.hide();
        loadFragment(HomeFragment.newInstance());
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                mActionBar.setTitle(R.string.title_home);
                loadFragment(HomeFragment.newInstance());
                return true;
            case R.id.navigation_music:
                mActionBar.setTitle(R.string.title_music);
                return true;
            case R.id.navigation_search:
                mActionBar.setTitle(R.string.title_search);
                return true;
        }
        return false;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
