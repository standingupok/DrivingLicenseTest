package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SpecialQuestionActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private TextView textViewQuestionCount;
    private ImageView imgQuestion;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView textViewExplain;
    private Button btnForward;
    private ImageButton btnBackward;

    private CountDownTimer countDownTimer;
    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private FinalScoreDlg finalScoreDlg;
    private int count = 0;
    private Handler handler = new Handler();
    private boolean checkIncorrectSpecial = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_special);

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewExplain = findViewById(R.id.text_view_explain);
        imgQuestion = findViewById(R.id.img_question);
        tv1 = findViewById(R.id.text_view_choice_1);
        tv2 = findViewById(R.id.text_view_choice_2);
        tv3 = findViewById(R.id.text_view_choice_3);
        tv4 = findViewById(R.id.text_view_choice_4);
        btnForward = findViewById(R.id.btn_forward);
        btnBackward = findViewById(R.id.btn_backward);

        Intent it = getIntent();
        int categoryId = it.getIntExtra("idcategorise",0);
        String categoryName = it.getStringExtra("categorisename");

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllSpecialQuestions();
        questionCountTotal = questionList.size();

        showNextQuestion();

        //btn next
        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextQuestion();
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
        Intent intent = new Intent(SpecialQuestionActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    private void showNextQuestion(){
//        textViewExplain.setText(currentQuestion.getExplain());
        tv1.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_purple));
        tv2.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_purple));
        tv3.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_purple));
        tv4.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_purple));

        //nếu còn câu hỏi
        if (questionCounter < questionCountTotal){
            //Lay du lieu o vi tri counter
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            tv1.setText(currentQuestion.getOption1());
            tv2.setText(currentQuestion.getOption2());
            tv3.setText(currentQuestion.getOption3());
            tv4.setText(currentQuestion.getOption4());
            textViewExplain.setText(currentQuestion.getExplain());
            switch (currentQuestion.getAnswerNumber()){
                case 1:
                    tv1.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_success));
                    break;
                case 2:
                    tv2.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_success));
                    break;
                case 3:
                    tv3.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_success));
                    break;
                case 4:
                    tv4.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_success));
                    break;
            }
            if(currentQuestion.getExplain() != null){
                textViewExplain.setVisibility(View.VISIBLE);
            }

            tv3.setVisibility(View.VISIBLE);
            tv4.setVisibility(View.VISIBLE);

            imgQuestion.setImageResource(getResources().getIdentifier(currentQuestion.getImage()+"","drawable","com.example.myapplication"));
            if ( currentQuestion.getOption3() == null){
                tv3.setVisibility(View.GONE);
                tv4.setVisibility(View.GONE);
            } else if (currentQuestion.getOption4() == null) {
                tv4.setVisibility(View.GONE);
            }

            questionCounter++;
            //vị trí câu hỏi hiện tại
            textViewQuestionCount.setText(questionCounter + "/" + questionCountTotal);
        }
        //thoát ra giao diện chính
        else {
            switch (currentQuestion.getAnswerNumber()){
                case 1:
                    tv1.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_success));
                    break;
                case 2:
                    tv2.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_success));
                    break;
                case 3:
                    tv3.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_success));
                    break;
                case 4:
                    tv4.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_success));
                    break;
            }
            Toast.makeText(SpecialQuestionActivity.this,"ĐÂY LÀ CÂU CUỐI CÙNG",Toast.LENGTH_SHORT).show();
        }
    }
}