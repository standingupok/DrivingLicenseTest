package com.example.myapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.Random;

public class MenuActivity extends AppCompatActivity {

    private List<Category> categoryList;
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // There are no request codes
                    Intent data = result.getData();
                    // handle the data
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        // lấy dữ liệu danh sách đề thi
        categoryList = dbHelper.getAllCategory();

        Button buttonRandTest = findViewById(R.id.btn_rand_test_fnc);
        Button buttonTest = findViewById(R.id.btn_test_fnc);
        Button buttonImportant = findViewById(R.id.btn_impq);
        Button buttonSigns = findViewById(R.id.btn_signs);
        Button buttonType = findViewById(R.id.btn_type_fnc);
        Button buttonTips = findViewById(R.id.btn_tips_fnc);



        buttonRandTest.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_orange));
        buttonTest.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_red));
        buttonImportant.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_violet));
        buttonSigns.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_success));
        buttonType.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_dark_pink));
        buttonTips.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_pink));

        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCategory();
            }
        });

        buttonRandTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuestion();
            }
        });

        buttonImportant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpecialQuestion();
            }
        });

        buttonSigns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignCategory();
            }
        });

        buttonType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startType();
            }
        });

        buttonTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTips();
            }
        });
    }
    private void startSignCategory(){
        Intent intent = new Intent(MenuActivity.this, SignCategory.class);
        startActivity(intent);
    }

    private void startCategory(){
        Intent intent = new Intent(MenuActivity.this, CategoryActivity.class);
        startActivity(intent);
    }

    private void startQuestion(){


        Category currCategory = new Category();
        Random random = new Random();
        int randId = random.nextInt(8);
        currCategory = categoryList.get(randId);
        int categoryId = currCategory.getId();
        String categoryName = currCategory.getName();
        Intent it = new Intent(MenuActivity.this, RandomQuizActivity.class);
        it.putExtra("idcategorise", categoryId);
        it.putExtra("categorisename", categoryName);
        mStartForResult.launch(it);;
    }

    private void startSpecialQuestion(){
        Intent intent = new Intent(MenuActivity.this, SpecialQuestionActivity.class);
        startActivity(intent);
    }
    private void startType(){
        Intent intent = new Intent(MenuActivity.this, TypeActivity.class);
        startActivity(intent);
    }

    private void startTips(){
        Intent intent = new Intent(MenuActivity.this, TipsActivity.class);
        startActivity(intent);
    }

}