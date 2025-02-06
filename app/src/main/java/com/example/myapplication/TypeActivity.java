package com.example.myapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.List;

public class TypeActivity extends AppCompatActivity {
    private List<Type> typeList;
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // There are no request codes
                    Intent data = result.getData();
                    // handle the data
                }
            });

    private LinearLayout buttonType1;
    private LinearLayout buttonType2;
    private LinearLayout buttonType3;
    private LinearLayout buttonType4;
    private LinearLayout buttonType5;
    private ImageButton btnBackward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        QuizDbHelper dbHelper = new QuizDbHelper(this);
        typeList = dbHelper.getAllType();

        buttonType1 = findViewById(R.id.btn_type_1);
        buttonType2 = findViewById(R.id.btn_type_2);
        buttonType3 = findViewById(R.id.btn_type_3);
        buttonType4 = findViewById(R.id.btn_type_4);
        buttonType5 = findViewById(R.id.btn_type_5);
        btnBackward = findViewById(R.id.btn_backward);

        buttonType1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startType1();
            }
        });

        buttonType2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startType2();
            }
        });

        buttonType3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startType3();
            }
        });

        buttonType4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startType4();
            }
        });

        buttonType5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startType5();
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
        Intent intent = new Intent(TypeActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    private void startType1(){
//        Intent intent = new Intent(TypeActivity.this, Type1Activity.class);
//        startActivity(intent);

        Type currType = new Type();
        int btnTypeId = 0;
        currType = typeList.get(btnTypeId);
        int typeId = currType.getId();
        String typeName = currType.getName();
        Intent it = new Intent(TypeActivity.this, TypeQuizActivity.class);
        it.putExtra("idtype", typeId);
        it.putExtra("typename", typeName);
        mStartForResult.launch(it);;
    }
    private void startType2(){
        Type currType = new Type();
        int btnTypeId = 1;
        currType = typeList.get(btnTypeId);
        int typeId = currType.getId();
        String typeName = currType.getName();
        Intent it = new Intent(TypeActivity.this, TypeQuizActivity.class);
        it.putExtra("idtype", typeId);
        it.putExtra("typename", typeName);
        mStartForResult.launch(it);;
    }
    private void startType3(){
        Type currType = new Type();
        int btnTypeId = 2;
        currType = typeList.get(btnTypeId);
        int typeId = currType.getId();
        String typeName = currType.getName();
        Intent it = new Intent(TypeActivity.this, TypeQuizActivity.class);
        it.putExtra("idtype", typeId);
        it.putExtra("typename", typeName);
        mStartForResult.launch(it);;
    }
    private void startType4(){
        Type currType = new Type();
        int btnTypeId = 3;
        currType = typeList.get(btnTypeId);
        int typeId = currType.getId();
        String typeName = currType.getName();
        Intent it = new Intent(TypeActivity.this, TypeQuizActivity.class);
        it.putExtra("idtype", typeId);
        it.putExtra("typename", typeName);
        mStartForResult.launch(it);;
    }
    private void startType5(){
        Type currType = new Type();
        int btnTypeId = 4;
        currType = typeList.get(btnTypeId);
        int typeId = currType.getId();
        String typeName = currType.getName();
        Intent it = new Intent(TypeActivity.this, TypeQuizActivity.class);
        it.putExtra("idtype", typeId);
        it.putExtra("typename", typeName);
        mStartForResult.launch(it);;
    }
}