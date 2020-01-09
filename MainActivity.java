package com.example.trombonetutor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button start_btn;
    private Button statistic_btn;
    private Button options_btn;

    /*Speicherort der Einstellungen
    Enthält:
    "length", kann in den Optionen der App angepasst werden, definiert die Länge des Spiels
    "selectedIndex", speichert den Index der gespeicherten länge im Spinner
     */
    public static final String SAVE_Settings = "TromboneTutorSettings";
    /*
    Speicherort der vergangenen Spiele
    "roundCounter", Speichert wie viele Spiele bereits gespielt wurden
     */
    public static final String SAVE_Scores = "PastScoreSave";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);
        create();
    }


    public void create(){
        setContentView(R.layout.activity_main);

        start_btn = findViewById(R.id.start_btn);
        statistic_btn = findViewById(R.id.statistic_btn);
        options_btn = findViewById(R.id.options_btn);

        start_btn.setOnClickListener(this);
        statistic_btn.setOnClickListener(this);
        options_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.start_btn){
           startGame();
        }
        if(v.getId()==R.id.statistic_btn){

        }
        if(v.getId()==R.id.options_btn){
            optionController optCt = new optionController();
            optCt.setmA(this);
            optCt.create();
        }
    }

    public void startGame(){
        Player p = new Player();
        p.setmA(this);
        p.create();
    }
}
