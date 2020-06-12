package com.izen1231.ontime.adapter;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.izen1231.ontime.R;
import com.izen1231.ontime.activity.AlarmListActivity;
import com.izen1231.ontime.activity.MainActivity;
import com.izen1231.ontime.activity.ShowAlarm;
import com.izen1231.ontime.model.Alarm;

import java.util.ArrayList;
import java.util.List;

public class AlarmListAdapter extends RecyclerView.Adapter<AlarmListAdapter.myViewHolder> {

    private Activity activity;
    private List<Alarm> mData;
    private AlarmListActivity ac;

    public static final int REQUEST_CODE = 0;

    public AlarmListAdapter(Activity activity, List<Alarm> mData) {
        this.activity = activity;
        this.mData = mData;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView number;
        Button btn_onOffAlarm;
        public myViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_user);
            number = (TextView) itemView.findViewById(R.id.number_user);
            btn_onOffAlarm = (Button) itemView.findViewById(R.id.btn_onOffAlarm);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(activity, "click " +
                            getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Toast.makeText(activity, "remove " +
                            getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

            // 이버튼 누르면 알람 진행하는 함수가 실행된다
            btn_onOffAlarm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // alarm on 컬럼 db 업데이트

                    Intent intent = new Intent(activity, ShowAlarm.class);
                    intent.setAction(Intent.ACTION_MAIN);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                    PendingIntent pendingIntent = PendingIntent.getActivity(activity, REQUEST_CODE,
                            intent, 0);

                    int alarmType = AlarmManager.ELAPSED_REALTIME;
                    final int FIFTEEN_SEC_MILLIS = 2000;

                    AlarmManager alarmManager = (AlarmManager)
                            activity.getSystemService(activity.ALARM_SERVICE);

                    alarmManager.set(alarmType, SystemClock.elapsedRealtime() + FIFTEEN_SEC_MILLIS,pendingIntent);

                    Toast.makeText(activity, "알람이 셋팅 되었습니다... "
                            , Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_alarm_list_item, parent, false);
        myViewHolder viewHolder = new myViewHolder(view);
        return viewHolder;
    }

    // 재활용 되는 View가 호출, Adapter가 해당 position에 해당하는 데이터를 결합
    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {
        Alarm data = mData.get(position);

        System.out.println("바인드 데이터 : " +data);

        holder.name.setText(data.getAlarm_Name());
    }


}
