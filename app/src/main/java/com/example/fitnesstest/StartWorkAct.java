package com.example.fitnesstest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class StartWorkAct extends AppCompatActivity {

    TextView intropage, subintropage, fitonetitle, fitonedesc, timerValue, btnexercise;
    View divpage, bgprogress;
    LinearLayout fitone;
    ImageView imgTimer;
    Button continueButton;
    ImageView fitoneImage;

    private static long START_TIME_IN_MILLIS = 40000;
    private static long START_BREAK_TIME_IN_MILLIS = 20000;
    private CountDownTimer countDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private long breakTimeLeftInMillis = START_BREAK_TIME_IN_MILLIS;
    private boolean stateBreakButton=false;
    private int page=2;
    /*private boolean atBottom=true;
    private boolean atTop=false;
    private int lastdown=0;
    private int lastup=0;*/
    private int durchlauf=1;
    private int workout=0;
    private boolean timerStarted=false;


    Animation btthree, bttfour, ttbone, ttbtwo, alphago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_work);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.clickeffect);
        boolean soundOn=true;
        int workoutTime = 40;
        int breakTime=20;

        Bundle extras=getIntent().getExtras();
        if (extras != null)
        {
            soundOn=extras.getBoolean("soundOn");
            if (extras.getInt("workoutTime")!=0)
            workoutTime=extras.getInt("workoutTime");
            if (extras.getInt("breakTime")!=0)
            breakTime=extras.getInt("breakTime");
        }

        Log.d("Worktime",""+workoutTime);
        Log.d("break", ""+breakTime);

        START_TIME_IN_MILLIS=(long) workoutTime*1000;
        START_BREAK_TIME_IN_MILLIS=(long) breakTime*1000;
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        breakTimeLeftInMillis = START_BREAK_TIME_IN_MILLIS;


        continueButton=findViewById(R.id.continue_Button);
        continueButton.setText("START ROUTINE: "+durchlauf);
        continueButton.setEnabled(true);

        fitoneImage=findViewById(R.id.fitoneImage);

        //Load Animations
        btthree = AnimationUtils.loadAnimation(this, R.anim.btthree);
        bttfour = AnimationUtils.loadAnimation(this, R.anim.bttfour);
        ttbone = AnimationUtils.loadAnimation(this, R.anim.ttbone);
        ttbtwo = AnimationUtils.loadAnimation(this, R.anim.ttbtwo);
        alphago = AnimationUtils.loadAnimation(this, R.anim.alphago);

        intropage = (TextView) findViewById(R.id.intropage);
        subintropage = (TextView) findViewById(R.id.subintropage);
        fitonetitle = (TextView) findViewById(R.id.fitonetitle);
        fitonedesc = (TextView) findViewById(R.id.fitonedesc);
        timerValue = (TextView) findViewById(R.id.timerValue);
        btnexercise = (TextView) findViewById(R.id.btnexercise);

        divpage = (View) findViewById(R.id.divpage);
        bgprogress = (View) findViewById(R.id.bgprogress);

        fitone = (LinearLayout) findViewById(R.id.fitone);

        imgTimer = (ImageView) findViewById(R.id.imgtimer);

        //assign animation
        btnexercise.startAnimation(bttfour);
        bgprogress.startAnimation(btthree);
        fitone.startAnimation(ttbone);
        intropage.startAnimation(ttbtwo);
        subintropage.startAnimation(ttbtwo);
        divpage.startAnimation(ttbtwo);
        timerValue.startAnimation(alphago);
        imgTimer.startAnimation(alphago);
        continueButton.startAnimation(alphago);


        if (workout==0){
            fitoneImage.setImageResource(R.drawable.chest);
            subintropage.setText("1. Barbell Bench Press\n Grasp the bar just outside shoulder-width and arch your back so there’s space between your lower back and the bench. Pull the bar out of the rack and lower it to your sternum, tucking your elbows about 45° to your sides. When the bar touches your body, drive your feet hard into the floor and press the bar back up.");

        }else if (workout==1){
            fitonetitle.setText("Bicep Workout");


        }

        boolean finalSoundOn = soundOn;
        Log.d("Sound: ",""+finalSoundOn);


        btnexercise.setOnClickListener(v -> {

            if (workout==0) {

                if (page==1) {
                    //Toast toast = Toast.makeText(this, "Workout Finished", Toast.LENGTH_LONG);
                    //toast.show();
                    fitoneImage.setImageResource(R.drawable.chest);
                    subintropage.setText("1. Barbell Bench Press\n Grasp the bar just outside shoulder-width and arch your back so there’s space between your lower back and the bench. Pull the bar out of the rack and lower it to your sternum, tucking your elbows about 45° to your sides. When the bar touches your body, drive your feet hard into the floor and press the bar back up.");
                    page = 2;
                    continueButton.setText("NEXT ROUTINE: " + durchlauf);
                    continueButton.setEnabled(true);

                    countDownTimer.cancel();
                    timerValue.setText("00:00");
                    mTimeLeftInMillis = START_TIME_IN_MILLIS;
                    breakTimeLeftInMillis = START_BREAK_TIME_IN_MILLIS;
                    stateBreakButton = true;

                    if (finalSoundOn == true) {
                        mediaPlayer.start();
                    }


                } else if (page  == 2) {
                    fitoneImage.setImageResource(R.drawable.inclinedumbbell);
                    subintropage.setText("2. Incline Dumbbell Flye\nSet an adjustable bench to a 30°-45° angle, and lie back on it with a dumbbell in each hand. Turn your wrists so your palms face each other. Press the weights straight over your chest, then, keeping a slight bend in your elbows, spread your arms open as if you were going for a big bear hug.");
                    page = 3;

                    if (finalSoundOn == true) {
                        mediaPlayer.start();
                    }

                    startNewWorkout();


                } else if (page  == 3) {
                    fitoneImage.setImageResource(R.drawable.cablecrossover);
                    subintropage.setText("3. Cable Crossover\nStand between two facing cable stations with both pulleys set midway between the top and bottom of the station. Attach a D-handle to each pulley and hold one in each hand. Keep your elbows slightly bent, and step forward so there’s tension on the cables.");
                    page = 4;

                    if (finalSoundOn == true) {
                        mediaPlayer.start();
                    }
                    startNewWorkout();
                } else if (page == 4) {
                    fitoneImage.setImageResource(R.drawable.landmine_press);
                    subintropage.setText("4. Landmine Press\nWedge the end of the barbell into a corner of the room (to avoid damage to the walls, you may have to wrap a towel around it). Load the opposite end with weight and grasp it toward the end of the barbell sleeve with your right hand. Stagger your stance so your left leg is in front. Press the bar straight overhead.");
                    page = 1;
                    if (finalSoundOn == true) {
                        mediaPlayer.start();
                    }
                    startNewWorkout();
                }
            }else if (workout==1){
                // Do Other Workout
            }

        });


    }

    public void resetTime(){

    }
    public void startDurchlauf_onClick(View view) {
        /*if (!stateBreakButton) {
            startTimerBreak();
            continueButton.setText("Break");
            continueButton.setEnabled(false);
            stateBreakButton=true;
        }
        else {
            startTimer();
            continueButton.setEnabled(false);
            stateBreakButton=false;
        }*/
        durchlauf++;
        startTimer();
        continueButton.setEnabled(false);
        continueButton.setText("Do Workout");

    }
    private void startTimer(){
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.clickeffect);
        boolean soundOn=true;

        Bundle extras=getIntent().getExtras();
        if (extras != null)
        {
            soundOn=extras.getBoolean("soundOn");
        }


        timerStarted=true;
        boolean finalSoundOn = soundOn;
        countDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimeLeftInMillis = START_TIME_IN_MILLIS;
                breakTimeLeftInMillis = START_BREAK_TIME_IN_MILLIS;
                continueButton.setText("Break");
                continueButton.setEnabled(false);

                if(finalSoundOn ==true) {
                    mediaPlayer.start();
                }

                startTimerBreak();
            }
        }.start();
        mTimerRunning = true;
    }
    private void startTimerBreak(){


        countDownTimer = new CountDownTimer(breakTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                btnexercise.performClick();
                mTimeLeftInMillis = START_TIME_IN_MILLIS;
                breakTimeLeftInMillis = START_BREAK_TIME_IN_MILLIS;

            }
        }.start();
        mTimerRunning = true;
    }

    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeft = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds) ;
        timerValue.setText(timeLeft);
    }

    /* Workout Back button
    public void backToPreviousWorkout(View view) {

        if (atBottom){
            Toast toast = Toast.makeText(this, "You are on your first excerise", Toast.LENGTH_LONG);
            toast.show();
        }
        if (page-1-lastup<=1){
            fitoneImage.setImageResource(R.drawable.chest);
            subintropage.setText("1. Barbell Bench Press\n Grasp the bar just outside shoulder-width and arch your back so there’s space between your lower back and the bench. Pull the bar out of the rack and lower it to your sternum, tucking your elbows about 45° to your sides. When the bar touches your body, drive your feet hard into the floor and press the bar back up.");
            atBottom=true;
            atTop=false;
            page=1;


        } else if (page-1-lastup==2){
            fitoneImage.setImageResource(R.drawable.inclinedumbbell);
            subintropage.setText("2. Incline Dumbbell Flye\nSet an adjustable bench to a 30°-45° angle, and lie back on it with a dumbbell in each hand. Turn your wrists so your palms face each other. Press the weights straight over your chest, then, keeping a slight bend in your elbows, spread your arms open as if you were going for a big bear hug.");
            atBottom=false;
            atTop=false;
            page=2;
            lastdown=1;
            lastup=0;


        }else if (page-1-lastup>=3){
            fitoneImage.setImageResource(R.drawable.cablecrossover);
            subintropage.setText("3. Cable Crossover\nStand between two facing cable stations with both pulleys set midway between the top and bottom of the station. Attach a D-handle to each pulley and hold one in each hand. Keep your elbows slightly bent, and step forward so there’s tension on the cables.");
            page=3;
            atBottom=false;
            atTop=false;
            lastdown=1;
            lastup=0;
        }


        continueButton.setText("Do Workout");
        continueButton.setEnabled(false);

        countDownTimer.cancel();
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        breakTimeLeftInMillis = START_BREAK_TIME_IN_MILLIS;
        stateBreakButton=false;
        startTimer();
    }*/
    public void startNewWorkout(){
        continueButton.setText("Do Workout");
        continueButton.setEnabled(false);

        if (timerStarted) {
            countDownTimer.cancel();
        }
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        breakTimeLeftInMillis = START_BREAK_TIME_IN_MILLIS;
        stateBreakButton=false;
        startTimer();
    }


}