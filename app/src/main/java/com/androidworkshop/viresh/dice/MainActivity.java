package com.androidworkshop.viresh.dice;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int userScore;
    int turnScore;
    int computerScore;
    int cturnScore;
    int curRoll;
    Random r;
    long startTime = 0;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public MainActivity(){
        userScore = 0;
        turnScore = 0;
        computerScore = 0;
        cturnScore = 0;
        curRoll=0;
        r = new Random();
    }

    private void update(){
        TextView t = (TextView)findViewById(R.id.score);
        t.setText("Your Score: "+userScore + " \nComputer Score: "+ computerScore + " \nYour Turn Score: "+turnScore + " \nComputer's Turn Score: "+cturnScore);
    }
    private void setImage(int n){
        ImageView i = (ImageView)findViewById(R.id.imageView);
        if(n==1){
            i.setImageResource(R.drawable.dice1);
        }
        else if(n==2){
            i.setImageResource(R.drawable.dice2);
        }
        else if(n==3){
            i.setImageResource(R.drawable.dice3);
        }
        else if(n==4){
            i.setImageResource(R.drawable.dice4);
        }
        else if(n==5){
            i.setImageResource(R.drawable.dice5);
        }
        else if(n==6) {
            i.setImageResource(R.drawable.dice6);
        }
    }
    private void animate(){
        for(int i=1; i<=6; i++){
            setImage(i);
        }
    }
    private int dieRoll(){
//        animate();
        int x = r.nextInt(6)+1;
        curRoll =x;
        return x;
    }

    public void Roll(View v){
        int n = dieRoll();
        setImage(n);
        if(n!=1){
            turnScore +=n;
        }
        else{
            turnScore = 0;
            computerTurn();
        }
        update();
    }

    public void reset(View V){
        computerScore = 0;
        userScore = 0;
        turnScore = 0;
        cturnScore = 0;
        update();
        setImage(1);
        findViewById(R.id.hold).setEnabled(true);
        findViewById(R.id.roll).setEnabled(true);
    }

    public void hold(View V){
        userScore += turnScore;
        turnScore = 0;
        update();
        computerTurn();

    }

    private void computerTurn(){
        findViewById(R.id.hold).setEnabled(false);
        findViewById(R.id.roll).setEnabled(false);
        cturnScore = 0;
        Log.d("Die Roll: ", "----------- Start of cTurn ----------");
        while(cturnScore < 20) {
            Log.d("Die Roll: ", "computerTurn: "+cturnScore);
            int n = dieRoll();
            setImage(n);
            if (n != 1) {
                cturnScore += n;
            } else {
                cturnScore = 0;
                break;
            }
            update();
//                    Toast.makeText(getApplicationContext(), "Hello Dear", Toast.LENGTH_SHORT).show();
        }
        computerScore+=cturnScore;
        Toast.makeText(getApplicationContext(), "Computer's turn score was " + cturnScore, Toast.LENGTH_SHORT).show();
        cturnScore = 0;
        update();
        findViewById(R.id.hold).setEnabled(true);
        findViewById(R.id.roll).setEnabled(true);
    }
}
