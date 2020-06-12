package com.izen1231.ontime.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.izen1231.ontime.R;

import org.jetbrains.annotations.Nullable;

public class ImageFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dev_adapter, container, false);

        TextView item_time = view.findViewById(R.id.item_time);
        com.suke.widget.SwitchButton main_toggle = view.findViewById(R.id.main_toggle);

        return view;
    }
    }