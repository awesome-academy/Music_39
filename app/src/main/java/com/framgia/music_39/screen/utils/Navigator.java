package com.framgia.music_39.screen.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import com.framgia.music_39.R;

public class Navigator {
    public void addFragment(FragmentActivity fragmentActivity, Fragment fragment, int idLayout) {
        switch (idLayout) {
            case R.id.frame_container:
                FragmentTransaction fragmentTransaction =
                        fragmentActivity.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(idLayout, fragment);
                fragmentTransaction.commit();
                break;
            case R.id.frame_content_list_home:
                FragmentTransaction fragmentTransaction1 =
                        fragmentActivity.getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(idLayout, fragment);
                fragmentTransaction1.addToBackStack(null);
                fragmentTransaction1.commit();
                break;
        }
    }
}
