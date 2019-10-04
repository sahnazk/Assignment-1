package com.example.clock;



import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TimePicker alarmTimePicker;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //So I realised the picker wasn't the correct way for time. Using Java's timezone would
        //be the best way to get date/time/location. However when I tried here in android studio, I
        // - can't get it to work. So I'm going to comment the gmt timzezone stuff I could get working on netbeans
       // public void testLocalDateTime() {

            //get the current date and time

           // LocalDateTime currentTime = LocalDateTime.now();
            //System.out.println("Current DateTime: " + currentTime);

            //LocalDate date1 = currentTime.toLocalDate();
            //System.out.println("date1: " + date1 );

         //   Month month = currentTime.getMonth();
           // int day = currentTime.getDayOfMonth();
           // int seconds = currentTime.getSecond();

          //  System.out.println(day +"seconds: " + seconds ); //>



    }

    public void OnToggleClicked(View view)
    {
        long time;
        if (((ToggleButton)view)isChecked())
        {
            Toast.makeText(MainActivity.this, "ALARM ON", Toast.LENGTH_SHORT).show();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
            Intent intent = new Intent(this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
            time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
            if (System.currentTimeMillis()>time)
            {if (calendar.AM_PM == 0)
                time = time + (1000*60*60*12);
            else
                time = time + (1000*60*60*24);

        }
    }
}
