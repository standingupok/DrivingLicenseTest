package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class InstructionSign extends AppCompatActivity {
    private ImageButton btnBackward;
    String signListShort[] = {"I.401","I.402","I.405a","I.407a","I.408","I.409","I.409a","I.423a","I.423c","I.424a","I.424d","I.425","I.426","I.439","I.440"};
    int signImages[] = {R.drawable.i401,R.drawable.i402,R.drawable.i405a,R.drawable.i407a,R.drawable.i408,R.drawable.i409,R.drawable.i409a,R.drawable.i423a,R.drawable.i423c,R.drawable.i424a,R.drawable.i424d,R.drawable.i425,R.drawable.i426,R.drawable.i439,R.drawable.i440};
    String signListLong[] = {"Bắt đầu đường ưu tiên","Hết đoạn đường ưu tiên","Đường cụt","Đường một chiều","Nơi đỗ xe","Chỗ quay xe","Chỉ dẫn địa giới","Vị trí người đi bộ sang ngang","Điểm bắt đầu đường đi bộ","Cầu vượt qua đường cho người đi bộ","Hầm chui qua đường cho người đi bộ","Bệnh viện","Trạm cấp cứu","Tên cầu","Đường đang thi công"};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_list_view);
        listView = (ListView) findViewById(R.id.custom_list_view);
        btnBackward = findViewById(R.id.btn_backward);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(),signListShort,signListLong,signImages);
        listView.setAdapter(customBaseAdapter);

        btnBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBackward();
            }
        });
    }

    private void showBackward() {
        Intent intent = new Intent(InstructionSign.this, SignCategory.class);
        startActivity(intent);
    }
}