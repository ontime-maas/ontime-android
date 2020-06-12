package com.izen1231.ontime.layout;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.izen1231.ontime.R;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.ornach.nobobutton.NoboButton;

import org.w3c.dom.Text;

public class ThridFragment extends Fragment {
    NoboButton btn_setArTime;
    TextView txttest;

    public ThridFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.activity_input3, container, false);

        return layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_setArTime = (NoboButton)view.findViewById(R.id.btn_setArTime);

        btn_setArTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment mTimePickerFragment = new TimePickerFragment();
                mTimePickerFragment.show(getFragmentManager(),"test");
            }
        });
    }

    public TextView getTxttest() {
        return txttest;
    }

}
