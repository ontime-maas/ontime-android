package com.izen1231.ontime.view;

import android.graphics.Color;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

import com.izen1231.ontime.R;
import com.izen1231.ontime.model.Alarm;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlarmAdapter extends RecyclerView.Adapter {

    private ArrayList<Alarm> mAlarms;
    private CallBack mCallBack;

    public AlarmAdapter(ArrayList<Alarm> mAlarms, CallBack mCallBack) {
        this.mAlarms = mAlarms;
        this.mCallBack = mCallBack;
    }


    public interface CallBack {

        void onMenuAction(Alarm object, MenuItem item, int position);

        void startAlarm(Alarm timeItem);

        void cancelAlarm(Alarm timeItem);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //TODO: Khởi tạo alarm_item thông qua inflate.
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.alarm_item, parent, false);
        return new TimeViewHolder(view, mCallBack);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TimeViewHolder) {
            TimeViewHolder timeViewHolder = (TimeViewHolder) holder;
            timeViewHolder.bindView(mAlarms.get(position));

        }

    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mAlarms.size();
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener,
            PopupMenu.OnMenuItemClickListener {

        @BindView(R.id.time_Alarm)
        TextView time;
        @BindView(R.id.alarm_Name)
        TextView title;
        @BindView(R.id.toggle_Alarm)
        ToggleButton toggleButton;

        private TimeViewHolder(View itemView, CallBack CallBack) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mCallBack = CallBack;
            itemView.setOnCreateContextMenuListener(this);

        }

        @OnClick(R.id.toggle_Alarm)
        public void onToggleClicked(View v) {
            boolean isChecked = toggleButton.isChecked();
            if (isChecked) {
                time.setTextColor(Color.rgb(155, 231, 174));
                mCallBack.startAlarm(mAlarms.get(getPosition()));
            } else {
                time.setTextColor(Color.rgb(155, 155, 155));
                mCallBack.cancelAlarm(mAlarms.get(getPosition()));
            }
        }



        private void bindView(Alarm alarm) {
            time.setText(getStringFromTime(alarm));
            title.setText(alarm.getAlarm_Name());
            int onOff = alarm.getOnOff();
            switch (onOff) {
                case 1:
                    toggleButton.setChecked(true);
                    time.setTextColor(Color.rgb(155, 231, 174));
                    break;
                case 0:
                    toggleButton.setChecked(false);
                    time.setTextColor(Color.rgb(155, 155, 155));
                    break;
            }
        }

        private String getStringFromTime(Alarm alarm) {

            int minute = alarm.getMinute_x();
            int hourSource = alarm.getHour_x();
            int hour;
            String hour_x;
            String minute_x;
            String format;

            if (hourSource == 0) {
                hour = hourSource + 12;
                format = "AM";
            } else if (hourSource == 12) {
                hour = hourSource;
                format = "PM";
            } else if (hourSource > 12) {
                hour = hourSource - 12;
                format = "PM";
            } else {
                hour = hourSource;
                format = "AM";
            }

            if (hour < 10) {
                hour_x = "0" + hour;
            } else {
                hour_x = "" + hour;
            }

            if (minute < 10) {
                minute_x = "0" + minute;
            } else {
                minute_x = "" + minute;
            }


            return hour_x + " : " + minute_x + "    " + format;
        }


        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view,
                                        ContextMenu.ContextMenuInfo contextMenuInfo) {
            PopupMenu popup = new PopupMenu(view.getContext(), view);
            popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());
            popup.setOnMenuItemClickListener(this);
            popup.show();

        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            int position = getPosition();
            mCallBack.onMenuAction(mAlarms.get(position), menuItem, position);
            return false;
        }
    }

    public void add(Alarm alarm) {
        mAlarms.add(alarm);
    }

    public void updateAlarm(Alarm alarm, int position) {
        mAlarms.remove(position);
        mAlarms.add(position, alarm);
    }

    public void removeAlarm(int position) {

        try {
            mAlarms.remove(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Alarm> getmAlarms() {
        return mAlarms;
    }
}