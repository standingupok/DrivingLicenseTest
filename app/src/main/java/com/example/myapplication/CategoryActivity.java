package com.example.myapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private Spinner spinner;
    private Button btnStart;
    private TextView btnBackward;
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
        setContentView(R.layout.activity_category);

        btnStart = findViewById(R.id.btn_start);
        spinner = findViewById(R.id.spinner_category);
        btnBackward = findViewById(R.id.btn_backward);

        //load đề
        showCategory();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuestion();
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
        Intent intent = new Intent(CategoryActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    // hàm bắt đầu câu hỏi qua acitity question
    private void startQuestion(){
        Category category = (Category) spinner.getSelectedItem();
        int categoryId = category.getId();
        String categoryName = category.getName();
        Intent it = new Intent(CategoryActivity.this, QuizActivity.class);
        it.putExtra("idcategorise", categoryId);
        it.putExtra("categorisename", categoryName);
        mStartForResult.launch(it);
    }


    private void showCategory(){

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        // lấy dữ liệu danh sách đề thi
        List<Category> categoryList = dbHelper.getAllCategory();
        //tạo adapter
        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryList);

        //bố cục hiển thị chủ đề
        categoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //gán đề thi lên spinner
        spinner.setAdapter(categoryArrayAdapter);
    }
}