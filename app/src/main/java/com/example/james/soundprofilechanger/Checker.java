package com.example.james.soundprofilechanger;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.Calendar;

public class Checker extends IntentService {
    SharedPreferences prefs;
    String prefName = "MyPref";
    public Checker()
    {
        super(null);
    }
    @Override
    protected void onHandleIntent(Intent check) {
        Calendar calendar = Calendar.getInstance();
        prefs =getSharedPreferences(prefName, MODE_PRIVATE);
        Intent start =new Intent(this,starter.class);
        PendingIntent pi=PendingIntent.getService(this,0,start,0);
        AlarmManager alarmstart = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        if(prefs.getBoolean("flag",false)) {
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                if(prefs.getInt("sun",0)>0)
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        calendar.set(Calendar.HOUR_OF_DAY, prefs.getInt("sunStaHr", 0));
                        calendar.set(Calendar.MINUTE, prefs.getInt("sunStaMin", 0));
                        alarmstart.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);

            }
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                if(prefs.getInt("mon",0)>0)
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        calendar.set(Calendar.HOUR_OF_DAY, prefs.getInt("monStaHr", 0));
                        calendar.set(Calendar.MINUTE, prefs.getInt("monStaMin", 0));
                        alarmstart.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);

            }
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                if(prefs.getInt("tue",0)>0)
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        calendar.set(Calendar.HOUR_OF_DAY, prefs.getInt("tueStaHr", 0));
                        calendar.set(Calendar.MINUTE, prefs.getInt("tueStaMin", 0));
                        alarmstart.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);

            }
            if (calendar.get(Calendar.DAY_OF_WEEK)== Calendar.WEDNESDAY) {
                if(prefs.getInt("wed",0)>0)
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        calendar.set(Calendar.HOUR_OF_DAY, prefs.getInt("wedStaHr", 0));
                        calendar.set(Calendar.MINUTE, prefs.getInt("wedStaMin", 0));
                        alarmstart.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);

            }
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                if(prefs.getInt("thu",0)>0)
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        calendar.set(Calendar.HOUR_OF_DAY, prefs.getInt("thuStaHr", 0));
                        calendar.set(Calendar.MINUTE, prefs.getInt("thuStaMin", 0));
                        alarmstart.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);

            }
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
                if(prefs.getInt("fri",0)>0)
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        calendar.set(Calendar.HOUR_OF_DAY, prefs.getInt("friStaHr", 0));
                        calendar.set(Calendar.MINUTE, prefs.getInt("friStaMin", 0));
                        alarmstart.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
         }
        if (calendar.get(Calendar.DAY_OF_WEEK)== Calendar.SATURDAY) {
            if(prefs.getInt("sat",0)>0)
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, prefs.getInt("satStaHr", 0));
                    calendar.set(Calendar.MINUTE, prefs.getInt("satStaMin", 0));
                    alarmstart.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);

        }
    }

}

