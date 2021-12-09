package com.example.fitnesstest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class OptionsActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    Calendar calendar=Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.clickeffect);

        Switch toggleNoti=(Switch) findViewById(R.id.toggleNoti);
        Switch toggleSound=(Switch) findViewById(R.id.toggleSound);



        //set start Notification time to current time
        calendar.setTimeInMillis(System.currentTimeMillis());

        //Button for time Picker
        Button button= (Button) findViewById(R.id.timePickerButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getSupportFragmentManager(),"time picker");
            }
        });

        TextView btnexercise = (TextView) findViewById(R.id.btnexercise);

        btnexercise.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boolean soundOn = true;

                if (toggleSound.isChecked()==false)
                {
                    soundOn=false;
                }
                else if(toggleSound.isChecked()==true)
                {
                    soundOn=true;
                }

                if (toggleNoti.isChecked())
                {
                    //Actiavte Notification
                    //TODO not working
                    /*Intent intent=new Intent(OptionsActivity.this,Reminder.class);
                    PendingIntent pendingIntent=PendingIntent.getBroadcast(OptionsActivity.this,0,intent,0);

                    AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);

                    long timeAtButtonClick=System.currentTimeMillis();
                    long tenSecondsinMillis=1000*10;

                    alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonClick+tenSecondsinMillis, pendingIntent);
                    //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

                     */
                }

                if(soundOn == true)
                {
                    mediaPlayer.start();
                }

                Intent a = new Intent(OptionsActivity.this,WorkoutAct.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                a.putExtra("soundOn",soundOn);
                startActivity(a);
            }
        });
    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minuteOfDay) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minuteOfDay);
        TextView textTimePicker=(TextView) findViewById(R.id.textTimePicker);
        textTimePicker.setText("current notification time: "+hourOfDay+":"+minuteOfDay);

    }


}
