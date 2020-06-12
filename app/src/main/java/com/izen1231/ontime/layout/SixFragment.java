package com.izen1231.ontime.layout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.izen1231.ontime.R;

public class SixFragment extends Fragment {


    public SixFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {


        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.activity_input6, container, false);

        return layout;
    }

}
