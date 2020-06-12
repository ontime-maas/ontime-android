package com.izen1231.ontime.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.izen1231.ontime.model.Alarm;

import java.util.ArrayList;
import java.util.List;

public class AlarmDatabaseOpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "db_alarm";
    public static final String TABLE_NAME = "alarm";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "alarm_name";
    public static final String COL_HOUR = "hour";
    public static final String COL_MINUTE = "minute";
    public static final String COL_TOGGLE = "toggle";
    public static final String COL_META_ID = "meta_id";
    public static final String COL_GOAL_H = "goal_h";
    public static final String COL_GOAL_M = "goal_m";
    public static final String COL_MOVE_H = "move_h";
    public static final String COL_MOVE_M = "move_m";
    public static final String COL_READY_H = "ready_h";
    public static final String COL_READY_M = "ready_m";
    public static final String COL_START_H = "start_h";
    public static final String COL_START_M = "start_m";
    public static final String COL_UPTIME_H = "up_h";
    public static final String COL_UPTIME_M = "up_m";
    public static final String COL_STARTADDRESS = "start_address";
    public static final String COL_ENDADDRESS = "end_address";
    public static final String COL_STATION_ID = "totalPathStationId";
    public static final String COL_STATION_NAME = "totalPathStationName";
    public static final String COL_BUS_ID = "busId";
    public static final String COL_BUS_NO = "busNo";
    public static final String COL_RAN_H = "range_h";
    public static final String COL_RAN_M = "range_m";
    public static final String COL_WORK = "warkTime";

    private String CREATE_TABLE_ALARM = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + COL_ID + " INTEGER, "
            + COL_HOUR + " INTEGER, "
            + COL_MINUTE + " INTEGER, "
            + COL_NAME + " TEXT, "
            + COL_TOGGLE + " INTEGER, "
            + COL_GOAL_H + " INTEGER, "
            + COL_GOAL_M + " INTEGER, "
            + COL_MOVE_H + " INTEGER, "
            + COL_MOVE_M + " INTEGER, "
            + COL_READY_H + " INTEGER, "
            + COL_READY_M + " INTEGER, "
            + COL_START_H + " INTEGER, "
            + COL_START_M + " INTEGER, "
            + COL_UPTIME_H + " INTEGER, "
            + COL_UPTIME_M + " INTEGER, "
            + COL_STATION_ID + " INTEGER, "
            + COL_STATION_NAME + " TEXT, "
            + COL_STARTADDRESS + " TEXT, "
            + COL_BUS_ID + " INTEGER, "
            + COL_BUS_NO + " TEXT, "
            + COL_RAN_H + " INTEGER, "
            + COL_RAN_M + " INTEGER, "
            + COL_WORK + " INTEGER, "
            + COL_ENDADDRESS + " TEXT) ";

    public AlarmDatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void createTable(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_ALARM);
    }
    public void deleteTable(SQLiteDatabase db){
        db.execSQL("DELETE FROM alarm");
    }

    // TODO: insert alarm to database
    public void insert(Alarm alarm) {
        SQLiteDatabase db = null;

        System.out.println(alarm);

        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COL_ID, alarm.getId());
            values.put(COL_HOUR, alarm.getHour_x());
            values.put(COL_MINUTE, alarm.getMinute_x());
            values.put(COL_NAME, alarm.getAlarm_Name());
            values.put(COL_TOGGLE, alarm.getOnOff());
            values.put(COL_GOAL_H, alarm.getGoal_h());
            values.put(COL_GOAL_M, alarm.getGoal_m());
            values.put(COL_MOVE_H, alarm.getMove_h());
            values.put(COL_MOVE_M, alarm.getMove_m());
            values.put(COL_READY_H, alarm.getReady_h());
            values.put(COL_READY_M, alarm.getReady_m());
            values.put(COL_START_H, alarm.getStart_h());
            values.put(COL_START_M, alarm.getStart_m());
            values.put(COL_UPTIME_H, alarm.getUp_h());
            values.put(COL_UPTIME_M, alarm.getUp_m());
            values.put(COL_STARTADDRESS, alarm.getStart_address());
            values.put(COL_ENDADDRESS, alarm.getEnd_address());
            values.put(COL_STATION_ID, alarm.getTotalPathStationId());
            values.put(COL_STATION_NAME, alarm.getTotalPathStationName());
            values.put(COL_BUS_ID, alarm.getBusId());
            values.put(COL_BUS_NO, alarm.getBusNo());
            values.put(COL_RAN_H, alarm.getRange_h());
            values.put(COL_RAN_M, alarm.getRange_m());
            values.put(COL_WORK, alarm.getWarkTime());

            db.insert(TABLE_NAME, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public void updateOnoffAlarm(int toggle , Alarm alarm) {
        SQLiteDatabase db = null;

        System.out.println(alarm);

        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COL_TOGGLE, toggle);
            db.update(TABLE_NAME,values,"id="+alarm.getId(),null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public List<Alarm> queryAlarmTable(){
        Cursor cursor = null;
        List<Alarm> alarmList = new ArrayList<>();

        SQLiteDatabase db = null;
        try{
            db = this.getWritableDatabase();
            cursor = db.query(TABLE_NAME,null,null,null,null,null,null);

            if (cursor != null){
                while( cursor.moveToNext()){
                    Alarm alarm = new Alarm();

                    int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
                    int h = cursor.getInt(cursor.getColumnIndex(COL_HOUR));
                    int m = cursor.getInt(cursor.getColumnIndex(COL_MINUTE));
                    String n = cursor.getString(cursor.getColumnIndex(COL_NAME));
                    int toggle = cursor.getInt(cursor.getColumnIndex(COL_TOGGLE));
                    int goal_h = cursor.getInt(cursor.getColumnIndex(COL_GOAL_H));
                    int goal_m = cursor.getInt(cursor.getColumnIndex(COL_GOAL_M));
                    int move_h = cursor.getInt(cursor.getColumnIndex(COL_MOVE_H));
                    int move_m = cursor.getInt(cursor.getColumnIndex(COL_MOVE_M));
                    int ready_h = cursor.getInt(cursor.getColumnIndex(COL_READY_H));
                    int ready_m = cursor.getInt(cursor.getColumnIndex(COL_READY_M));
                    int start_h = cursor.getInt(cursor.getColumnIndex(COL_START_H));
                    int start_m = cursor.getInt(cursor.getColumnIndex(COL_START_M));
                    int up_h = cursor.getInt(cursor.getColumnIndex(COL_UPTIME_H));
                    int up_m = cursor.getInt(cursor.getColumnIndex(COL_UPTIME_M));
                    String start_address = cursor.getString(cursor.getColumnIndex(COL_STARTADDRESS));
                    String end_address = cursor.getString(cursor.getColumnIndex(COL_ENDADDRESS));
                    int stationId = cursor.getInt(cursor.getColumnIndex(COL_STATION_ID));
                    String stationName = cursor.getString(cursor.getColumnIndex(COL_STATION_NAME));
                    int busId = cursor.getInt(cursor.getColumnIndex(COL_BUS_ID));
                    String busNo = cursor.getString(cursor.getColumnIndex(COL_BUS_NO));
                    int range_h = cursor.getInt(cursor.getColumnIndex(COL_RAN_H));
                    int range_m = cursor.getInt(cursor.getColumnIndex(COL_RAN_M));
                    int warkTime = cursor.getInt(cursor.getColumnIndex(COL_WORK));

                    alarm.setId(id);
                    alarm.setHour_x(h);
                    alarm.setMinute_x(m);
                    alarm.setAlarm_Name(n);
                    alarm.setOnOff(toggle);

                    alarm.setGoal_h(goal_h);
                    alarm.setGoal_m(goal_m);
                    alarm.setMove_h(move_h);
                    alarm.setMove_m(move_m);
                    alarm.setReady_h(ready_h);
                    alarm.setReady_m(ready_m);
                    alarm.setStart_h(start_h);
                    alarm.setStart_m(start_m);
                    alarm.setUp_h(up_h);
                    alarm.setUp_m(up_m);
                    alarm.setStart_address(start_address);
                    alarm.setEnd_address(end_address);
                    alarm.setTotalPathStationId(stationId);
                    alarm.setTotalPathStationName(stationName);
                    alarm.setBusId(busId);
                    alarm.setBusNo(busNo);
                    alarm.setRange_h(range_h);
                    alarm.setRange_m(range_m);
                    alarm.setWarkTime(warkTime);

                    alarmList.add(alarm);
                }
            }
        } finally {
            if(cursor != null){
                cursor.close();
            }
            if(db != null){
                db.close();
            }
        }
        return alarmList;
    }
}
