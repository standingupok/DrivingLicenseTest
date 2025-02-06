package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class OtherSign extends AppCompatActivity {
    private ImageButton btnBackward;
    String signListShort[] = {"S.501", "S.502", "S.503a", "S.504", "S.505a", "S.506", "S.509a", "S.510", "S.G,11a", "S.G,12a", "S.G,12b"};
    int signImages[] = {
            R.drawable.s501, R.drawable.s502, R.drawable.s503a, R.drawable.s504, R.drawable.s505a,
            R.drawable.s506, R.drawable.s509a, R.drawable.s510, R.drawable.sg11a, R.drawable.sg12a,
            R.drawable.sg12b
    };
    String signListLong[] = {"Phạm vi tác dụng của biển","Khoảng cách đến đối tượng báo hiệu","Hướng tác dụng của biển","Làn đường","Loại xe","Hướng đường ưu tiên","Thuyết minh biển chính","Chú ý đường trơn có băng tuyết","Chỉ dẫn số lượng làn và hướng đi cho từng làn","Biển chỉ dẫn làn đường không lưu thông","biển chỉ dẫn làn đường không lưu thông"};
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
        Intent intent = new Intent(OtherSign.this, SignCategory.class);
        startActivity(intent);
    }
}