package com.izen1231.ontime.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.izen1231.ontime.layout.FirstFragment;
import com.izen1231.ontime.layout.FiveFragment;
import com.izen1231.ontime.layout.FourFragment;
import com.izen1231.ontime.layout.SecondFragment;
import com.izen1231.ontime.layout.SixFragment;
import com.izen1231.ontime.layout.ThridFragment;

public class pagerAdapter extends FragmentStatePagerAdapter {
    public pagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                return new FirstFragment();
            case 1:
                return new SecondFragment();
            case 2:
                return new ThridFragment();
            case 3:
                return new FourFragment();
            case 4:
                return new FiveFragment();
            case 5:
                return new SixFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 6;
    }
}
