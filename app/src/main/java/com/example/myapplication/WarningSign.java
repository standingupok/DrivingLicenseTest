package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class WarningSign extends AppCompatActivity {
    private ImageButton btnBackward;
    String signListShort[] = {"W.201a", "W.201c", "W.202a", "W.203a", "W.204", "W.205a", "W.206", "W.207a", "W.208", "W.209", "W.210", "W.211a", "W.212", "W.213", "W.219", "W.221a", "W.222a", "W.223a", "W.224", "W.225"};
    int signImages[] = { R.drawable.w201a, R.drawable.w201c, R.drawable.w202a, R.drawable.w203a, R.drawable.w204,
            R.drawable.w205a, R.drawable.w206, R.drawable.w207a, R.drawable.w208, R.drawable.w209,
            R.drawable.w210, R.drawable.w211a, R.drawable.w212, R.drawable.w213, R.drawable.w219,
            R.drawable.w221a, R.drawable.w222a, R.drawable.w223a, R.drawable.w224, R.drawable.w225};
    String signListLong[] = {"Chỗ ngoặt nguy hiểm","Chỗ ngoặt nguy hiểm có nguy cơ lật xe","Nhiều chỗ ngoặt nguy hiểm liên tiếp","Đường bị thu hẹp","Đường hai chiều","Đường giao nhau","Giao nhau chạy theo vòng xuyến","Giao nhau với đường không ưu tiên","Giao nhau với đường ưu tiên","Giao nhau có tín hiệu đèn","Giao nhau với đường sắt có rào chắn","Giao nhau với đường sắt không có rào chắn","Cầu hẹp","Cầu tạm","Dốc xuống nguy hiểm","Đường không bằng phẳng","Đường trơn","Vách núi nguy hiểm","Đường người đi bộ cắt ngang","Trẻ em"};
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
        Intent intent = new Intent(WarningSign.this, SignCategory.class);
        startActivity(intent);
    }
}