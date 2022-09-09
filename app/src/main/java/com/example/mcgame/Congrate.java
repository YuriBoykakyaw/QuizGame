package com.example.mcgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Congrate extends AppCompatActivity {
TextView txtcongrate;
TextView txtscorenumber;
Button btnexit;
Button btnreturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrate);
        txtcongrate=(TextView) findViewById(R.id.idcongrate);
        txtscorenumber=(TextView) findViewById(R.id.idscorenumber);
        btnexit=(Button) findViewById(R.id.idexit);
        btnreturn=(Button) findViewById(R.id.idreturn);
        btnreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Congrate.this, Level.class);
            }
        });
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Congrate.this,MainActivity.class);
            }
        });
    }
}