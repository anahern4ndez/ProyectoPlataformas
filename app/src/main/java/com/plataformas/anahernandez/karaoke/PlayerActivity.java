package com.plataformas.anahernandez.karaoke;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class PlayerActivity extends AppCompatActivity implements Runnable{

    private ImageButton playButton;
    private ImageButton nextButton;
    private ImageButton pauseButton;
    private ImageButton previousButton;
    private SeekBar songProgress;
    private Thread soundThread;
    private MediaPlayer soundPlayer;
    private boolean restart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        playButton = (ImageButton) findViewById(R.id.playButton);
        pauseButton = (ImageButton) findViewById(R.id.pauseButton);
        previousButton = (ImageButton) findViewById(R.id.previousButton);
        nextButton = (ImageButton) findViewById(R.id.nextButton);
        songProgress = (SeekBar) findViewById(R.id.songProgress);
        soundPlayer = MediaPlayer.create(getBaseContext(), R.raw.all_the_stars);


        setUpListeners();

        soundThread = new Thread(this);
        soundThread.start();


    }

        /** metodo para el update del seekbar **/
    @Override
    public void run() {
        int currentPosition = 0;
        int soundTotal = soundPlayer.getDuration();
        songProgress.setMax(soundTotal);

        while (soundPlayer != null && currentPosition < soundTotal)
        {
            try
            {
                Thread.sleep(300);
                currentPosition = soundPlayer.getCurrentPosition();

            } catch (InterruptedException e) {
                return;
            }
            catch (Exception otherException)
            {
                return;
            }
            songProgress.setProgress(currentPosition);
        }

    }

    public void setUpListeners()
    {
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPlayer.start();
            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPlayer.pause();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (restart)
                {
                    soundPlayer.stop();
                    soundPlayer = MediaPlayer.create(getBaseContext(), R.raw.all_the_stars);
                    soundPlayer.start();
                }
                restart = !restart;

            }
        });

        songProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (fromUser)
                {
                    soundPlayer.seekTo(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
