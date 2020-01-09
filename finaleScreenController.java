package com.example.trombonetutor;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class finaleScreenController implements View.OnClickListener {
    private MainActivity mA;
    private TextView score_tv;
    private TextView procent_tv;
    private Button menu_btn;
    private Button again_btn;

    public void setmA(MainActivity mA) {
        this.mA = mA;
    }

    public void create(int finaleScore, int runden){
        mA.setContentView(R.layout.finalscreen);

        score_tv = mA.findViewById(R.id.score_tv);
        procent_tv = mA.findViewById(R.id.procent_tv);
        menu_btn = mA.findViewById(R.id.menu_btn);
        again_btn = mA.findViewById(R.id.again_btn);

        if(runden!=0){
            Double percent = 100.00/runden*finaleScore;
            if(percent.toString().indexOf(4)<5){
                if(percent>=100){
                    procent_tv.setText( percent.toString().substring(0,3)+"%");
                }else{
                    procent_tv.setText( percent.toString().substring(0,2)+"%");
                }
            }else {
                percent+=1;
                if(percent>=100){
                    procent_tv.setText( percent.toString().substring(0,3)+"%");
                }else{
                    procent_tv.setText( percent.toString().substring(0,2)+"%");
                }

            }
        }else{
            procent_tv.setText("0%");
        }

        menu_btn.setOnClickListener(this);
        again_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.again_btn){
            mA.startGame();
        }
        if(v.getId()==R.id.menu_btn){
            mA.create();
        }
    }
}
