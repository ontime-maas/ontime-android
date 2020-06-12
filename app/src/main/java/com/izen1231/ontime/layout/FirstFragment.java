package com.izen1231.ontime.layout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.izen1231.ontime.R;
import com.izen1231.ontime.activity.ShowAlarm;
import com.izen1231.ontime.example.Example01Activity;

public class FirstFragment extends Fragment {


    public FirstFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.activity_input, container, false);
        return layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//
//        Button btn_example = (Button)view.findViewById(R.id.btn_example);
//        btn_example.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // 예제 레이아웃으로 이동
//
//                Intent intent = new Intent(getActivity(), Example01Activity.class);
//                startActivity(intent);
//                getActivity().finish();
//            }
//        });
    }
}
