package com.example.trombonetutor;

import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import java.util.Random;

import static com.example.trombonetutor.MainActivity.SAVE_Scores;
import static com.example.trombonetutor.MainActivity.SAVE_Settings;

public class Player  implements View.OnClickListener {
    private ImageButton noteImage;
    private TextView score_tv;
    private TextView accuracy_tv;
    private TextView timer_tv;
    private int correctSlide = 0;
    private int score = 0;
    private int rounds = 0;
    private MainActivity mA;
    private ImageView rightAnswer_img;
    private TextView rightAnswer_tv;
    private String drawableResource;


    public void setmA(MainActivity mA){
        this.mA =mA;
    }

    public void create(){
        mA.setContentView(R.layout.playfield);

        noteImage = mA.findViewById(R.id.noteButton);
        score_tv = mA.findViewById(R.id.score_tv);
        accuracy_tv = mA.findViewById(R.id.accuracy_tv);
        timer_tv = mA.findViewById(R.id.timer_tv);

        rightAnswer_img = mA.findViewById(R.id.rightAnswer_img);
        rightAnswer_tv =mA.findViewById(R.id.rightAnswer_tv);

        Button btn1 = mA.findViewById(R.id.btn_1);
        Button btn2 = mA.findViewById(R.id.btn_2);
        Button btn3 = mA.findViewById(R.id.btn_3);
        Button btn4 = mA.findViewById(R.id.btn_4);
        Button btn5 = mA.findViewById(R.id.btn_5);
        Button btn6 = mA.findViewById(R.id.btn_6);
        Button btn7 = mA.findViewById(R.id.btn_7);

        Button cont_btn = mA.findViewById(R.id.cont_btn);

        Toolbar toolbar = mA.findViewById(R.id.toolbar);
        mA.setSupportActionBar(toolbar);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);

        cont_btn.setOnClickListener(this);

        //Cleared die imageview und textview welche bei einer falschen Antwort angezeigt werden
        rightAnswer_img.setImageResource(0);
        rightAnswer_tv.setText("");
        //Ermittelt eine zufällige Note und zeigt diese an
        newNote();

        //Ruft die Spiellänge aus dem Savefile ab
        SharedPreferences gameLength = mA.getSharedPreferences(SAVE_Settings,0);
        String savedLength = gameLength.getString(("length"),"5");

        //Setzt Spiellänge bzw. erstellt Countdown
        if(savedLength.equals("- (unbegrenzt)")){
            timer_tv.setText("-");
        }else{
            int length= Integer.parseInt(savedLength);

            new CountDownTimer(length*60*1000, 1000) {
                public void onTick(long millisUntilFinished) {
                    timer_tv.setText("" + millisUntilFinished / 1000);
                }
                public void onFinish() {
                    finish();
                }
            }.start();
        }
    }

    private void finish(){
        //Beendet das Spiel, ruft das Scoreboard auf
        finaleScreenController fsc = new finaleScreenController();
        SharedPreferences savedScore = mA.getSharedPreferences(SAVE_Scores,0);
        int roundCounter = Integer.parseInt(savedScore.getString("roundCounter","0"))+1;
        fsc.setmA(mA);
        fsc.create(score,rounds);
    }

    private void showRight(){
        //Wurde eine falsche Antwort gegeben, wird das Bild und die richtige Antwort zum Bild angezeigt
        rightAnswer_img.setImageResource(mA.getResources().getIdentifier(drawableResource, "drawable", mA.getPackageName()));
        rightAnswer_tv.setText("Richtige Antwort: \n"+correctSlide);
    }

    private void clearRight(){
        //Wird eine richtige Antwort angegeben, wird die Ansicht geleert
        rightAnswer_tv.setText("");
        rightAnswer_img.setImageResource(0);
    }

    private void updateScore(){
        score_tv.setText(score+"");
    }

    public void calculateAccuracy(){
        /*
        Berechnet die prozentuale Richtigkeit von Score und Rounds
        (Noch nicht optimal)
         */
        if(score!=0){
            Double percent = 100.00/rounds*score;
            if(percent.toString().indexOf(4)<5){
                if(percent>=100){
                    accuracy_tv.setText( percent.toString().substring(0,3)+"%");
                }else{
                    accuracy_tv.setText( percent.toString().substring(0,2)+"%");
                }
            }else {
                percent+=1;
                if(percent>=100){
                    accuracy_tv.setText( percent.toString().substring(0,3)+"%");
                }else{
                    accuracy_tv.setText( percent.toString().substring(0,2)+"%");
                }

            }
        }else{
            accuracy_tv.setText("0%");
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.cont_btn){
            finish();
        }
        if(v.getId()==R.id.btn_1){
            if(correctSlide==1){
                correctSlide();
            }else{
                showRight();
            }
        }
        if(v.getId()==R.id.btn_2){
            if(correctSlide==2){
                correctSlide();
            }else{
                showRight();
            }
        }
        if(v.getId()==R.id.btn_3) {
            if(correctSlide==3){
                correctSlide();
            }else{
                showRight();
            }
        }
        if(v.getId()==R.id.btn_4) {
            if(correctSlide==4){
                correctSlide();
            }else{
                showRight();
            }
        }
        if(v.getId()==R.id.btn_5) {
            if(correctSlide==5){
                correctSlide();
            }else{
                showRight();
            }
        }
        if(v.getId()==R.id.btn_6) {
            if(correctSlide==6){
                correctSlide();
            }else{
                showRight();
            }
        }
        if(v.getId()==R.id.btn_7) {
            if(correctSlide==7){
                correctSlide();
            }else{
                showRight();
            }
        }
        updateScore();
        newNote();
        rounds++;
        calculateAccuracy();
    }

    private void correctSlide(){
        //Falls die korrekte Antwort angegeben wurde
        score+=1;
        clearRight();
    }

    public void newNote(){
        //Algorithmus um eine zufällige Note zu ermitteln
        Random r = new Random();
        int minSlide=1;
        int maxSlide=7;
        int minNote;
        int maxNote;
        drawableResource = "";

        int slideNumber = r.nextInt((maxSlide-minSlide)+1)+minSlide;
        correctSlide = slideNumber;

        switch(slideNumber){
            case 1:
                minNote=1;
                maxNote=8;
                determineNote(minNote,maxNote,"1",r);
                break;
            case 2:
                minNote=1;
                maxNote=14;
                determineNote(minNote,maxNote,"2",r);
                break;
            case 3:
                minNote=1;
                maxNote=11;
                determineNote(minNote,maxNote,"3",r);
                break;
            case 4:
                minNote=1;
                maxNote=5;
                determineNote(minNote,maxNote,"4",r);
                break;
            case 5:
                minNote=1;
                maxNote=6;
                determineNote(minNote,maxNote,"5",r);
                break;
            case 6:
                minNote=1;
                maxNote=3;
                determineNote(minNote,maxNote,"6",r);
                break;
            case 7:
                minNote=1;
                maxNote=4;
                determineNote(minNote,maxNote,"7",r);
                break;
            default:

                break;
        }
    }

    private void determineNote(int minNote, int maxNote, String slide, Random r){
        int noteNumber;
        noteNumber = r.nextInt((maxNote-minNote)+1)+minNote;
        drawableResource="s"+slide+"_"+noteNumber;
        noteImage.setImageResource(mA.getResources().getIdentifier(drawableResource, "drawable", mA.getPackageName()));
    }

}
