package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignCategory extends AppCompatActivity {
    private Button buttonSign1;
    private Button buttonSign2;
    private Button buttonSign3;
    private Button buttonSign4;
    private Button buttonSign5;
    private TextView btnBackward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_category);
        buttonSign1 = findViewById(R.id.button_sign1);
        buttonSign2 = findViewById(R.id.button_sign2);
        buttonSign3 = findViewById(R.id.button_sign3);
        buttonSign4 = findViewById(R.id.button_sign4);
        buttonSign5 = findViewById(R.id.button_sign5);
        btnBackward = findViewById(R.id.btn_backward);

        buttonSign1.setBackground(ContextCompat.getDrawable(this, R.drawable.sign_regulation));
        buttonSign2.setBackground(ContextCompat.getDrawable(this, R.drawable.sign_guidance));
        buttonSign3.setBackground(ContextCompat.getDrawable(this, R.drawable.sign_instruction));
        buttonSign4.setBackground(ContextCompat.getDrawable(this, R.drawable.sign_warning));
        buttonSign5.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_pink));

        buttonSign1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(SignCategory.this, RegulationSign.class);
                startActivity(it);
            }
        });
        buttonSign2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(SignCategory.this, GuidanceSign.class);
                startActivity(it);
            }
        });
        buttonSign3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(SignCategory.this, InstructionSign.class);
                startActivity(it);
            }
        });
        buttonSign4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SignCategory.this, WarningSign.class);
                startActivity(it);
            }
        });

        buttonSign5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SignCategory.this, OtherSign.class);
                startActivity(it);
            }
        });

        btnBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBackward();
            }
        });
    }

    private void showBackward() {
        Intent intent = new Intent(SignCategory.this, MenuActivity.class);
        startActivity(intent);
    }
}