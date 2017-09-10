package com.example.james.soundprofilechanger;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;

import java.util.Calendar;


public class starter extends IntentService {
    AudioManager myAudio2;
    SharedPreferences prefs;
    String prefName = "MyPref";
    public starter(){
        super(null);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        Calendar cal=Calendar.getInstance();
        prefs =getSharedPreferences(prefName, MODE_PRIVATE);
        Intent stop =new Intent(this,stopper.class);
        PendingIntent pi2=PendingIntent.getService(this,0,stop,0);
        AlarmManager alarmstop = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if(prefs.getBoolean("flag",false)) {
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)

                cal.setTimeInMillis(System.currentTimeMillis());
            cal.set(Calendar.HOUR_OF_DAY, prefs.getInt("sunSpHr", 0));
            cal.set(Calendar.MINUTE, prefs.getInt("sunSpMin", 0));
            alarmstop.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi2);

        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {

            cal.setTimeInMillis(System.currentTimeMillis());
            cal.set(Calendar.HOUR_OF_DAY, prefs.getInt("monSpHr", 0));
            cal.set(Calendar.MINUTE, prefs.getInt("monSpMin", 0));
            alarmstop.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi2);
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {

            cal.setTimeInMillis(System.currentTimeMillis());
            cal.set(Calendar.HOUR_OF_DAY, prefs.getInt("tueSpHr", 0));
            cal.set(Calendar.MINUTE, prefs.getInt("tueSpMin", 0));
            alarmstop.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi2);
        }
        if (cal.get(Calendar.DAY_OF_WEEK)== Calendar.WEDNESDAY) {
            cal.setTimeInMillis(System.currentTimeMillis());
            cal.set(Calendar.HOUR_OF_DAY, prefs.getInt("wedSpHr", 0));
            cal.set(Calendar.MINUTE, prefs.getInt("wedSpMin", 0));
            alarmstop.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi2);
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {

            cal.setTimeInMillis(System.currentTimeMillis());
            cal.set(Calendar.HOUR_OF_DAY, prefs.getInt("thuSpHr", 0));
            cal.set(Calendar.MINUTE, prefs.getInt("thuSpMin", 0));
            alarmstop.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi2);
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {

            cal.setTimeInMillis(System.currentTimeMillis());
            cal.set(Calendar.HOUR_OF_DAY, prefs.getInt("friSpHr", 0));
            cal.set(Calendar.MINUTE, prefs.getInt("friSpMin", 0));
            alarmstop.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi2);
        }
        if (cal.get(Calendar.DAY_OF_WEEK)== Calendar.SATURDAY) {
            cal.setTimeInMillis(System.currentTimeMillis());
            cal.set(Calendar.HOUR_OF_DAY, prefs.getInt("satSpHr", 0));
            cal.set(Calendar.MINUTE, prefs.getInt("satSpMin", 0));
            alarmstop.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi2);

        }
        myAudio2 = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        myAudio2.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    }
}

