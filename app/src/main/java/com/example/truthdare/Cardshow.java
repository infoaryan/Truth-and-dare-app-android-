package com.example.truthdare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cardshow extends AppCompatActivity {

    String a[];
    TextView texttoshow;
    Button backbutton;
    int c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardshow);

        texttoshow = findViewById(R.id.texttoshow);
        backbutton = findViewById(R.id.backbutton);


        // The setting up for the list of statements to be told for truth and dare

        if(getIntent().getStringExtra("decide").equals("truth")){
            a = getResources().getStringArray(R.array.truth);

            //The initiation of the random integer for the picking of statement
             c = new Random().nextInt(a.length/2) + new Random().nextInt(a.length/2 -1);
        }
        else{
            a = getResources().getStringArray(R.array.dares);

            //The initiation of the random integer for the picking of statement
            c = new Random().nextInt(a.length/2) + new Random().nextInt(a.length/2 -1);        }

        texttoshow.setText(a[c]);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
