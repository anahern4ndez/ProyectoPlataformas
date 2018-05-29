package com.plataformas.anahernandez.karaoke;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private StorageReference db;
    private Button nextvideo = null;
    private Button nextsong = null;
    private Button nextrecording = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        //db = FirebaseStorage.getInstance().getReference();


        nextvideo = findViewById(R.id.gotovideos);
        nextsong = findViewById(R.id.gotomusic);
        nextrecording = findViewById(R.id.gotorecordings);

        nextvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, UrlInsert.class);
                startActivity(i);

            }
        });

        nextsong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SongList.class);
                startActivity(i);
            }
        });
        nextrecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AudioRecord.class);
                startActivity(i);
            }
        });

    }

    /**
     * Created by anahernandez on 5/22/18.


    public static class CustomMusicAdapter extends BaseAdapter {

        private Context context;
        private int layout;
        private ArrayList<SongBundle> musicList;

        public CustomMusicAdapter(Context context, int layout, ArrayList<SongBundle> musicList) {
            this.context = context;
            this.layout = layout;
            this.musicList = musicList;
        }

        @Override
        public int getCount() {
            return musicList.size();
        }

        private class ViewHolder
        {

        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }**/
}
