package com.plataformas.anahernandez.karaoke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecordingsShower extends AppCompatActivity {
    private ListView listView;
    private FirebaseDatabase storage = null;
    private ValueEventListener mDBListener;
    ArrayList<String> lista;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_shower);
        listView = (ListView)findViewById(R.id.listaGrabaciones);

        storage = FirebaseDatabase.getInstance();
        DatabaseReference myRef = storage.getReference("Recordings");
        lista = new ArrayList<>();


        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, lista);


        mDBListener = myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lista.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren())
                {
                    File archivo = postSnapshot.getValue(File.class);
                    //archivo.setKey(postSnapshot.getKey());
                    lista.add(archivo.getName());
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(RecordingsShower.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        //Abre la activity 2, mandando como objeto el hospital
                       // Intent test = new Intent(view.getContext(), SecondActivity.class);
                       // test.putExtra("Hospital", (Hospital)listView.getItemAtPosition(i));
                       // startActivity(test);

                    }
                }
        );
    }
}
