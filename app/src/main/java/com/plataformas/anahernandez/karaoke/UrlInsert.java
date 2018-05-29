package com.plataformas.anahernandez.karaoke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UrlInsert extends AppCompatActivity {
    private Button videodisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_insert);

        videodisplay = (Button)findViewById(R.id.gotovideo);
        videodisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(UrlInsert.this, VideoDisplay.class);
                next.putExtra("nombreurl", ((EditText)findViewById(R.id.urlName)).getText().toString());
                startActivity(next);

            }
        });
    }
}
