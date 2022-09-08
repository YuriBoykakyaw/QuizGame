package com.example.mcgame;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class Level extends AppCompatActivity {
    TextView txtlevel;
    TextView txtTimer;
    TextView txtScore;
    TextView txtQuestionnumber;
    TextView txtQuestion;
    RadioGroup rg;
    RadioButton choice1;
    RadioButton choice2;
    RadioButton choice3;
    RadioButton choice4;
    Button btnnext, btnsumit;
    String level;
    CountDownTimer cdTimer;
    int Score = 0;
    int questionIndex = 1;
    int num1 = 0;
    int num2 = 0;
    int operatorNum = 0;
    int Answer = 0;
    String operator = "";
    ArrayList<Integer> optionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        level = getIntent().getStringExtra("Level");

        txtlevel = (TextView) findViewById(R.id.idlevel);
        txtlevel.setText("Level" + level);
        txtTimer = (TextView) findViewById(R.id.idtimer);
        txtScore = (TextView) findViewById(R.id.idscore);
        txtQuestion = (TextView) findViewById(R.id.idquestionnumber);
        txtQuestionnumber = (TextView) findViewById(R.id.idquestionnumber);
        rg = (RadioGroup) findViewById(R.id.idrg);
        choice1 = (RadioButton) findViewById(R.id.idchoice1);
        choice2 = (RadioButton) findViewById(R.id.idchoice2);
        choice3 = (RadioButton) findViewById(R.id.idchoice3);
        choice4 = (RadioButton) findViewById(R.id.idchoice4);
        btnnext = (Button) findViewById(R.id.idnext);
        btnsumit = (Button) findViewById(R.id.idbtnsumit);
        txtScore.setText("Score"+Score);
        btnnext.setVisibility(View.INVISIBLE);
        btnsumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionIndex<=10){
                    setUpQuestion();
                }
                else{
                    Toast.makeText(Level.this,"Finished",Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (level.equals("0")) {
            txtTimer.setVisibility(View.INVISIBLE);
        }
        setUpQuestion();
    }

    public void Timer() {
        long miliSec = 0;
        if (level.equals("1")) {
            miliSec = 21000;
        } else if (level.equals("2")) {
            miliSec = 11000;
        }
        cdTimer = new CountDownTimer(miliSec, 1000) {
            @Override
            public void onTick(long l) {
                txtTimer.setText("Timer:00:" + l / 1000);
            }

            @Override
            public void onFinish() {
                if(questionIndex<=10){
                    setUpQuestion();
                }
                else{
                    Toast.makeText(Level.this,"Finished",Toast.LENGTH_SHORT).show();
                }
            }
        }.start();
    }

    public int randomNumber(int max, int min) {
        int range = max - min + 1;
        int rand = (int) (Math.random() * range) + min;
        return rand;
    }

    public void setUpQuestion() {
        rg.clearCheck();
        if (level.equals("1")) {
            txtTimer.setVisibility(View.VISIBLE);
            Timer();
        } else if (level.equals("2")) {
            txtTimer.setVisibility(View.VISIBLE);
            Timer();
        }
        num1 = randomNumber(12, 1);
        num2 = randomNumber(12, 1);
        operatorNum = randomNumber(4, 1);
        if (operatorNum == 1) {
            Answer = num1 + num2;
            operator = "+";
        } else if (operatorNum == 2) {
            if (num1 > num2) {
                Answer = num1 - num2;
                operator = "-";
            } else {
                setUpQuestion();
            }
        } else if (operatorNum == 3) {
            Answer = num1 * num2;
            operator = "*";
        } else if (operatorNum == 4) {
            Answer = num1 / num2;
            operator = "/";
        }
        setUpOptions();
        txtQuestion.setText(num1+" "+operator+" "+num2+" will be");
        btnnext.setVisibility(View.INVISIBLE);
        btnsumit.setVisibility(View.VISIBLE);
    }

    public void setUpOptions() {
        optionList = new ArrayList<Integer>();
        optionList.add(Answer);
        optionList.add(randomNumber(12, 1));
        optionList.add(randomNumber(12, 1));
        optionList.add(randomNumber(12, 1));
        Collections.shuffle(optionList);
        choice1.setText(String.valueOf(optionList.get(0)));
        choice2.setText(String.valueOf(optionList.get(1)));
        choice3.setText(String.valueOf(optionList.get(2)));
        choice4.setText(String.valueOf(optionList.get(3)));

    }

    public void checkAnswer() {
        if (choice1.isChecked()||choice2.isChecked()||choice3.isChecked()||choice4.isChecked()){
            btnnext.setVisibility(View.VISIBLE);
            btnsumit.setVisibility(View.INVISIBLE);
            RadioButton rdChecked = findViewById(rg.getCheckedRadioButtonId());
            if (Answer == Integer.parseInt(rdChecked.getText().toString())) {
                Score++;
                txtScore.setText("Score"+Score);
                Toast.makeText(Level.this,"correct",Toast.LENGTH_SHORT).show();
                MediaPlayer ring= MediaPlayer.create(Level.this,R.raw.correct);
                ring.start();
            }
            else {
                Toast.makeText(Level.this,"Fail",Toast.LENGTH_SHORT).show();
                MediaPlayer ring= MediaPlayer.create(Level.this,R.raw.wrong);
                ring.start();
            }
            questionIndex++;
            if(!level.equals("0")){
                cdTimer.cancel();
            }


        }
        else{
            Toast.makeText(Level.this,"choice1",Toast.LENGTH_SHORT).show();
        }
    }
}