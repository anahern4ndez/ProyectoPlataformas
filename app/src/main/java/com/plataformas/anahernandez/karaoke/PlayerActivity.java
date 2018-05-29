package com.plataformas.anahernandez.karaoke;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PlayerActivity extends AppCompatActivity implements Runnable{

    private ImageButton playButton;
    private ImageButton nextButton;
    private ImageButton pauseButton;
    private ImageButton previousButton;
    private SeekBar songProgress;
    private Thread soundThread;
    private MediaPlayer soundPlayer;
    private boolean restart = false;
    private ImageView songpic;
    private Uri song;
    private List<SongBundle> canciones;
    private SongBundle cancion;
    private TextView nombre, artista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Bundle receiver = getIntent().getExtras();
        cancion = receiver.getParcelable("Cancion");
        nombre = findViewById(R.id.recordName);
        artista = findViewById(R.id.songArtist);
        nombre.setText(cancion.getSongName());
        artista.setText(cancion.getSongArtist());


        playButton = (ImageButton) findViewById(R.id.playButton);
        pauseButton = (ImageButton) findViewById(R.id.pauseButton);
        previousButton = (ImageButton) findViewById(R.id.previousButton);
        nextButton = (ImageButton) findViewById(R.id.nextButton);
        songProgress = (SeekBar) findViewById(R.id.songProgress);
        songpic = (ImageView) findViewById(R.id.songpic);

        setResources();
        //temporal
        canciones = new ArrayList<>();

        SongBundle s1 = new SongBundle("all_the_stars", "all_the_stars", "All The Stars", "Kendrick Lamar ft SZA");
        SongBundle s2 = new SongBundle("my_my_my", "my_my_my", "My! My! My!", "Troye Sivan");
        SongBundle s3 = new SongBundle("nerd", "nerd", "Lemon", "N.E.R.D ft. Rihanna");
        SongBundle s4 = new SongBundle("nice_for_what", "nice_for_what", "Nice For What", "Drake");
        canciones.add(s1);
        canciones.add(s2);
        canciones.add(s3);
        canciones.add(s4);

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
                    soundPlayer = MediaPlayer.create(getBaseContext(), song);
                    soundPlayer.start();
                }
                else if (!restart)
                {
                    SongBundle prevsong = null;
                    for (SongBundle song : canciones)
                    {
                        if (song.getSongRaw().equals(cancion.getSongRaw()))
                        {
                            int index = canciones.indexOf(cancion);
                            if (index == 0)
                            {
                                prevsong = canciones.get(canciones.size()-1);
                            }
                            else
                            {
                                prevsong = canciones.get(index-1);
                            }

                        }

                    }
                    cancion = prevsong;
                    soundPlayer.stop();
                    setResources();
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
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SongBundle nextsong = null;
                for (SongBundle song : canciones)
                {
                    if (song.getSongRaw().equals(cancion.getSongRaw()))
                    {
                        int index = canciones.indexOf(cancion);
                        if (index == (canciones.size()-1))
                        {
                            nextsong = canciones.get(0);
                        }
                        else
                        {
                            nextsong = canciones.get(index+1);
                        }

                    }

                }
                cancion = nextsong;
                soundPlayer.stop();
                setResources();

            }
        });
    }
    public void setResources()
    {
        song = Uri.parse("android.resource://com.plataformas.anahernandez.karaoke/raw/"+cancion.getSongRaw());
        soundPlayer = MediaPlayer.create(getBaseContext(),song);
        int resId = this.getResources().getIdentifier(cancion.getImageRaw(), "drawable", this.getPackageName());
        songpic.setImageResource(resId);
        nombre.setText(cancion.getSongName());
        artista.setText(cancion.getSongArtist());
        soundPlayer.start();

    }
}
