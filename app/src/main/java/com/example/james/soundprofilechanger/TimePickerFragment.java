package com.example.james.soundprofilechanger;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;


public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        Integer hour=0,minute=0;
        if(((MainActivity)getActivity()).id==1) {
            hour = ((MainActivity) getActivity()).staHr;
            minute=((MainActivity)getActivity()).staMin;
        }
        else if(((MainActivity)getActivity()).id==2){
            hour = ((MainActivity) getActivity()).endHr;
            minute=((MainActivity)getActivity()).endMin;
        }

        return new TimePickerDialog(getActivity(),this,hour,minute ,
                DateFormat.is24HourFormat(getActivity()));
    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if(((MainActivity)getActivity()).id==1){
            ((MainActivity)getActivity()).staHr=hourOfDay;
            ((MainActivity)getActivity()).staMin=minute;
        }
        else if(((MainActivity)getActivity()).id==2) {
            ((MainActivity) getActivity()).endHr = hourOfDay;
            ((MainActivity) getActivity()).endMin = minute;
        }
    }
}
