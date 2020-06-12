package com.izen1231.ontime.layout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.izen1231.ontime.R;
import com.izen1231.ontime.activity.Input5Activity;
import com.izen1231.ontime.domain.AddressDomain;
import com.izen1231.ontime.domain.TimeDomain;
import com.izen1231.ontime.domain.UtilTimeDomain;
import com.ornach.nobobutton.NoboButton;

public class FourFragment extends Fragment {

    String param1;

    public FourFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.activity_input4, container, false);
        return layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NoboButton alarm_finish = (NoboButton)view.findViewById(R.id.btn_setReadyTime);

        final NumberPicker set_readytime = (NumberPicker)view.findViewById(R.id.set_readytime);
        set_readytime.setMinValue(0);
        set_readytime.setMaxValue(60);
        set_readytime.setWrapSelectorWheel(false);
        set_readytime.setOnLongPressUpdateInterval(100);

        alarm_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UtilTimeDomain utilTimeDomain = UtilTimeDomain.getInstance();

                TimeDomain t = new TimeDomain();

               int readyTime = set_readytime.getValue();

                if((readyTime) >=60){
                    t.setHour( (readyTime / 60));
                    t.setMin(  (readyTime % 60) );
                } else{
                    t.setHour(0);
                    t.setMin(readyTime);
                }
                utilTimeDomain.setReadyTime(t);

                getActivity().finish();

                Intent intent =new Intent(getActivity(), Input5Activity.class);

                UtilTimeDomain u = UtilTimeDomain.getInstance();
                System.out.println("도착시간 " + u.getArriveTime().getMin());
                System.out.println("준비시간 " + u.getReadyTime().getMin());

                AddressDomain a = AddressDomain.getInstance();
                System.out.println("출발지 " + a.getSaddress());
                System.out.println("도착지 " + a.getEaddress());

                System.out.println("두번째 인텐트!");

                /* 출발지  도착지 */
                intent.putExtra("startAddress",a.getSaddress());
                intent.putExtra("endAddress",a.getEaddress());

                // 도
                intent.putExtra("arrive_ampm",u.getArriveTime().getAmORpm());
                intent.putExtra("arrive_h",u.getArriveTime().getHour());
                intent.putExtra("arrive_m",u.getArriveTime().getMin());

                intent.putExtra("ready_h",u.getReadyTime().getHour());
                intent.putExtra("ready_m",u.getReadyTime().getMin());

                startActivity(intent);
            }
        });
    }
}
