package com.plataformas.anahernandez.karaoke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SongList extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        listView = (ListView)findViewById(R.id.listaCanciones);

        List<SongBundle> canciones = new ArrayList<>();

        SongBundle s1 = new SongBundle("all_the_stars", "all_the_stars", "All The Stars", "Kendrick Lamar ft SZA");
        SongBundle s2 = new SongBundle("my_my_my", "my_my_my", "My! My! My!", "Troye Sivan");
        SongBundle s3 = new SongBundle("nerd", "nerd", "Lemon", "N.E.R.D ft. Rihanna");
        SongBundle s4 = new SongBundle("nice_for_what", "nice_for_what", "Nice For What", "Drake");
        canciones.add(s1);
        canciones.add(s2);
        canciones.add(s3);
        canciones.add(s4);


        ArrayAdapter<SongBundle> adapter = new ArrayAdapter<SongBundle>(this,android.R.layout.simple_list_item_1,canciones);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        //Abre la activity 2, mandando como objeto el hospital
                        Intent test = new Intent(view.getContext(), PlayerActivity.class);
                        test.putExtra("Cancion", (SongBundle)listView.getItemAtPosition(i));
                        startActivity(test);

                    }
                }
        );


    }
}
