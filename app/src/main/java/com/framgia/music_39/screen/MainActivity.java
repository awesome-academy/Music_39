package com.framgia.music_39.screen;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.framgia.music_39.R;
import com.framgia.music_39.screen.home.HomeFragment;
import com.framgia.music_39.screen.listmusic.ListMusicFragment;
import com.framgia.music_39.screen.utils.Navigator;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final int REQUEST_PERMISSION_STORAGE = 123;
    private ActionBar mActionBar;
    public static Navigator mNavigator;
    private Boolean mIsPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPermission();
    }

    private void initView() {
        mNavigator = new Navigator();
        mActionBar = getSupportActionBar();
        assert mActionBar != null;
        mActionBar.hide();
        mNavigator.addFragment(MainActivity.this, HomeFragment.newInstance(), R.id.frame_container);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                mActionBar.setTitle(R.string.title_home);
                mNavigator.addFragment(MainActivity.this, HomeFragment.newInstance(),
                        R.id.frame_container);
                return true;
            case R.id.navigation_music:
                mActionBar.setTitle(R.string.title_music);
                if (!mIsPermission) {
                    initPermission();
                } else {
                    mNavigator.addFragment(MainActivity.this,
                            ListMusicFragment.getListMusicFragment(null), R.id.frame_container);
                }
                return true;
            case R.id.navigation_search:
                mActionBar.setTitle(R.string.title_search);
                mNavigator.addFragment(MainActivity.this, HomeFragment.newInstance(),
                        R.id.frame_container);
                return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mIsPermission = true;
                } else {
                    mIsPermission = false;
                }
                break;
        }
    }

    public void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                        REQUEST_PERMISSION_STORAGE);
            } else {
                mIsPermission = true;
            }
        } else {
            mIsPermission = true;
        }
    }
}
