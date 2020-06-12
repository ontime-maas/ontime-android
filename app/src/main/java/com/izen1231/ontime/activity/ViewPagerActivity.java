package com.izen1231.ontime.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2;
import com.izen1231.ontime.layout.FirstFragment;
import com.izen1231.ontime.layout.FiveFragment;
import com.izen1231.ontime.layout.FourFragment;
import com.izen1231.ontime.layout.SecondFragment;
import com.izen1231.ontime.layout.SixFragment;
import com.izen1231.ontime.layout.ThridFragment;

public class ViewPagerActivity extends AppIntro2 {
    ViewPager vp;
    FirstFragment firstFragment;
    SecondFragment secondFragment;
    ThridFragment thridFragment;
    FourFragment fourFragment;
    FiveFragment fiveFragment;
    SixFragment sixFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
        thridFragment = new ThridFragment();
        fourFragment = new FourFragment();
        fiveFragment = new FiveFragment();
        sixFragment = new SixFragment();

        addSlide(firstFragment);
        addSlide(secondFragment);
        addSlide(thridFragment);
        addSlide(fourFragment);

        showSkipButton(false);
        setBarColor(Color.parseColor("#FFFFFF"));
        setDepthAnimation();
        setProgressIndicator();

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            AppFinish();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(this, "one more", Toast.LENGTH_SHORT).show();
        }
    }

    public void AppFinish(){
        finish();
        System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
