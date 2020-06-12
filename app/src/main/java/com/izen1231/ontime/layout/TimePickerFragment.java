package com.izen1231.ontime.layout;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.izen1231.ontime.R;
import com.izen1231.ontime.domain.TimeDomain;
import com.izen1231.ontime.domain.UtilTimeDomain;

import org.w3c.dom.Text;

import java.util.Calendar;




public class TimePickerFragment extends DialogFragment
                            implements TimePickerDialog.OnTimeSetListener {

    private ViewGroup view1;
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String am_pm = getAmPm(hourOfDay);
        System.out.println("am pm -------> : "+am_pm);
        System.out.println("hourOfDay -------> : "+hourOfDay);
        System.out.println("minute -------> : "+minute);

        TimeDomain arrTimeParam = TimeDomain.getInstance();

        arrTimeParam.setAmORpm(am_pm);
        arrTimeParam.setHour(hourOfDay);
        arrTimeParam.setMin(minute);

        TextView ampm =  getActivity().findViewById(R.id.txt_arampm);
        TextView h =getActivity().findViewById(R.id.txt_arhour);
        TextView m =getActivity().findViewById(R.id.txt_armin);

        ampm.setText(am_pm);
        h.setText(Integer.toString(hourOfDay));
        m.setText(Integer.toString(minute));

        UtilTimeDomain u = UtilTimeDomain.getInstance();
        TimeDomain t= new TimeDomain();

        t.setHour(hourOfDay);
        t.setMin(minute);
        t.setAmORpm(am_pm);

        u.setArriveTime(t);

    }

    private String getAmPm(int hourOfDay){
        String AM_PM ;
        if(hourOfDay < 12) {
            AM_PM = "AM";
        } else {
            AM_PM = "PM";
        }
        return AM_PM;
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Calendar mCalendar = Calendar.getInstance();
        int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int min = mCalendar.get(Calendar.MINUTE);

        TimePickerDialog mTimePickerDialog = new TimePickerDialog(
                getContext(),this,hour,min, DateFormat.is24HourFormat(getContext())
        );

        return mTimePickerDialog;
    }

}
