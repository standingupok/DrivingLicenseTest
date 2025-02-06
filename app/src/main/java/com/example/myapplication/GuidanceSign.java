package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class GuidanceSign extends AppCompatActivity {
    private ImageButton btnBackward;
    String signListShort[] = {"R.122","R.301a","R.303","R.304","R.305","R.306","R.307","R.309","R.403a","R.403b","R.412b","R.412d","R.415","R.420","R.421","R.E,9a","R.E,9b","R.E,9c","R.E,11a","R.E,11b"};
    int signImages[] = {R.drawable.r122,R.drawable.r301a,R.drawable.r303,R.drawable.r304,R.drawable.r305,R.drawable.r306,R.drawable.r307,R.drawable.r309,R.drawable.r403a,R.drawable.r403b,R.drawable.r412b,R.drawable.r412d,R.drawable.r415,R.drawable.r420,R.drawable.r421,R.drawable.re9a,R.drawable.re9b,R.drawable.re9c,R.drawable.re11a,R.drawable.re11b};
    String signListLong[] = {"Dừng lại","Hướng đi phải theo","Nơi giao nhau chạy theo vòng xuyến","Đường dành cho xe thô sơ","Đường dành cho người đi bộ","Tốc độ tối thiểu cho phép","Hết hạn chế tốc độ tối thiểu","Ấn còi","Đường dành cho xe ôtô","Đường dành cho xe ôtô, xe máy","Làn đường dành cho xe ôtô con","Làn đường dành cho xe máy","Biển gộp làn đường theo phương tiện","Bắt đầu khu đông dân cư","Hết khu đông dân cư","Cấm đỗ xe trong khu vực","Cấm đỗ xe theo giờ trong khu vực","Khu vực đỗ xe","Đường hầm","Kết thúc đường hầm"};
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
        Intent intent = new Intent(GuidanceSign.this, SignCategory.class);
        startActivity(intent);
    }
}