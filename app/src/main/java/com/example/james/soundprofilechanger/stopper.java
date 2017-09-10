package com.example.james.soundprofilechanger;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;


public class stopper extends IntentService {
    AudioManager myAudio;
    public stopper(){
        super(null);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        myAudio = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        myAudio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    }
}
