package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class RegulationSign extends AppCompatActivity {
    private ImageButton btnBackward;
    private TextView textViewSignCategory;
    String signListShort[] = {"P.124a","P.105","P.101","P.102","P.103a","P.103b","P.103c","P.104","P.124b","P.106a","P.106b","P.106c","P.107","P.107a","P.107b","P.109","P.110a","P.112","DP.133","DP.135"};
    String signListLong[] = {"Cấm quay đầu xe","Cấm xe ôtô và xe máy","Đường cấm","Cấm đi ngược chiều","Cấm xe ôtô","Cấm xe ôtô rẽ phải","Cấm xe ôtô rẽ trái","Cấm xe máy","Cấm quay đầu xe","Cấm xe ôtô tải","Cấm xe ôtô tải trên 2,5t","Cấm xe chở hàng nguy hiểm","Cấm xe ôtô khách và xe ôtô tải","Cấm xe ôtô khách","Cấm xê ôtô taxi","Cấm máy kéo","Cấm xe đạp","Cấm người đi bộ","Hết cấm vượt","Hết tất cả lệnh cấm"};
    int signImages[] = {R.drawable.p124a,R.drawable.p105,R.drawable.p101,R.drawable.p102,R.drawable.p103a,R.drawable.p103b,R.drawable.p103c,R.drawable.p104,R.drawable.p124b,R.drawable.p106a,R.drawable.p106b,R.drawable.p106c,R.drawable.p107,R.drawable.p107a,R.drawable.p107b,R.drawable.p109,R.drawable.p110a,R.drawable.p112,R.drawable.dp133,R.drawable.dp135};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_list_view);
        listView = (ListView) findViewById(R.id.custom_list_view);
        btnBackward = findViewById(R.id.btn_backward);
        textViewSignCategory = findViewById(R.id.text_view_sign_category);
        textViewSignCategory.setText("Biển báo cấm");

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
        Intent intent = new Intent(RegulationSign.this, SignCategory.class);
        startActivity(intent);
    }
}