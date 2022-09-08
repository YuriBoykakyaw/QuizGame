package com.example.mcgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    Button btnlevel0, btnlevel1, btnlevel2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btnlevel0 = (Button) findViewById(R.id.idlevel0);
        btnlevel1 = (Button) findViewById(R.id.idlevel1);
        btnlevel2 = (Button) findViewById(R.id.idstart);
        btnlevel0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callNewIntent("0");
            }
        });
        btnlevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callNewIntent("1");
            }
        });

        btnlevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callNewIntent("2");
            }
        });
    }
    public void callNewIntent (String _level){
        Intent intent = new Intent(SecondActivity.this, Level.class);
        intent.putExtra("Level", _level);
        startActivity(intent);

    }
}


