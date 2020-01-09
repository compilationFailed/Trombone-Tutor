package com.example.trombonetutor;

import android.content.*;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import static com.example.trombonetutor.MainActivity.SAVE_Settings;

public class optionController implements View.OnClickListener{
    private MainActivity mA;
    private Spinner roundlength_spin;
    private String[] timeChoices = {"1","2","5","10","- (unbegrenzt)"};

    private Button back_btn;

    public void setmA(MainActivity mA){
        this.mA = mA;
    }

    public void create(){
        mA.setContentView(R.layout.options);
        roundlength_spin = mA.findViewById(R.id.roundlength_spin);
        back_btn = mA.findViewById(R.id.back_btn);

        //Mit dem ArrayAdapter werden die Auswahlmöglichkeiten des Spinners gesezt
        ArrayAdapter<String> choiceAdapter = new ArrayAdapter<String>(mA , android.R.layout.simple_spinner_dropdown_item, timeChoices);
        roundlength_spin.setAdapter(choiceAdapter);
        back_btn.setOnClickListener(this);
        recoverOptions();
    }

    @Override
    public void onClick(View v){
        if(v.getId()==R.id.back_btn){
            SharedPreferences gameLength = mA.getSharedPreferences(SAVE_Settings,0);
            SharedPreferences.Editor editor = gameLength.edit();
            editor.putString("length",roundlength_spin.getSelectedItem().toString());
            int index = roundlength_spin.getSelectedItemPosition();
            editor.putString("selectedIndex", index+"");
            editor.commit();
            mA.create();
        }
    }



    private void recoverOptions(){
        /*
        Diese Klasse setzt die angezeigten Werte beim öffnen der Optionen auf die gespeicherten
         */
        SharedPreferences gameLength = mA.getSharedPreferences(SAVE_Settings,0);
        int savedLengthIndex = Integer.parseInt(gameLength.getString(("selectedIndex"),"0"));

        roundlength_spin.setSelection(savedLengthIndex);

    }
}
