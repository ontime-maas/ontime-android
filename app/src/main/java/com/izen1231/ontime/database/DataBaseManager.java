package com.izen1231.ontime.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.izen1231.ontime.model.Alarm;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class DataBaseManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "db_alarm";
    private static final String TABLE_NAME = "alarm";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "alarm_name";
    private static final String COL_HOUR = "hour";
    private static final String COL_MINUTE = "minute";
    private static final String COL_TOGGLE = "toggle";

    private String CREATE_TABLE_ALARM = "CREATE TABLE " + TABLE_NAME + " ("
            + COL_ID + " INTEGER, "
            + COL_HOUR + " INTEGER, "
            + COL_MINUTE + " INTEGER, "
            + COL_NAME + " TEXT, "
            + COL_TOGGLE + " INTEGER) ";

    public DataBaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ALARM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format(" DROP TABLE IF EXISTS %s", CREATE_TABLE_ALARM));
        onCreate(db);
    }

    public void insert(Alarm alarm) {
        SQLiteDatabase db = null;

        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COL_ID, alarm.getId());
            values.put(COL_HOUR, alarm.getHour_x());
            values.put(COL_MINUTE, alarm.getMinute_x());
            values.put(COL_NAME, alarm.getAlarm_Name());
            values.put(COL_TOGGLE, alarm.getOnOff());
            db.insert(TABLE_NAME, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public void update(Alarm alarm) {
        SQLiteDatabase db = null;
        String where = COL_ID + " = " + alarm.getId();
        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COL_ID, alarm.getId());
            values.put(COL_HOUR, alarm.getHour_x());
            values.put(COL_MINUTE, alarm.getMinute_x());
            values.put(COL_NAME, alarm.getAlarm_Name());
            values.put(COL_TOGGLE, alarm.getOnOff());
            db.update(TABLE_NAME, values, where, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public void delete(int alarmId) {
        SQLiteDatabase db = null;
        String where = COL_ID + " = " + alarmId;

        try {
            db = this.getWritableDatabase();
            db.delete(TABLE_NAME, where, null);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }


    }

    public ArrayList<Alarm> getAlarmList() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Alarm> alarmArrayList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Alarm alarm = new Alarm(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getInt(4));
                    alarmArrayList.add(alarm);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "getAlarmList: exception cause " + e.getCause() + " message " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return alarmArrayList;
    }
}
