package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {
    private TextView textViewCategory;
    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountdown;
    private TextView textViewExplain;
    private ImageView imgQuestion;

    private RadioGroup rbGroup;
    private RadioButton btn1;
    private RadioButton btn2;
    private RadioButton btn3;
    private RadioButton btn4;
    private Button btnForward;
    private ImageButton btnBackward;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
//    private ColorStateList textColorDefaultRb;
    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private FinalScoreDlg finalScoreDlg;
    private boolean answered;
    private int count = 0;
    private Handler handler = new Handler();
    private boolean checkIncorrectSpecial = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewCategory = findViewById(R.id.text_view_category);
        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountdown = findViewById(R.id.text_view_countdown);
        textViewExplain = findViewById(R.id.text_view_explain);
        imgQuestion = findViewById(R.id.img_question);
        rbGroup = findViewById(R.id.radio_group);
        btn1 = findViewById(R.id.btn_choice_1);
        btn2 = findViewById(R.id.btn_choice_2);
        btn3 = findViewById(R.id.btn_choice_3);
        btn4 = findViewById(R.id.btn_choice_4);
        btnForward = findViewById(R.id.btn_forward);
        btnBackward = findViewById(R.id.btn_backward);

//        textColorDefaultRb = btn1.getTextColors();


//        Collections.shuffle(questionList);

        timeLeftInMillis = 19 * 60 * 1000;

        startCountDown();

        Intent it = getIntent();
        int categoryId = it.getIntExtra("idcategorise",0);
        String categoryName = it.getStringExtra("categorisename");
        textViewCategory.setText(categoryName);

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions(categoryId);
        questionCountTotal = questionList.size();

        finalScoreDlg = new FinalScoreDlg(this);

        showNextQuestion();

        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.btn_choice_1) {
                    btn1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_selected_option));
                    btn2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_purple));
                    btn3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_purple));
                    btn4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_purple));
                }
                else if(checkedId == R.id.btn_choice_2) {
                    btn2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_selected_option));
                    btn3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_purple));
                    btn4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_purple));
                    btn1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_purple));
                }
                else if(checkedId == R.id.btn_choice_3) {
                    btn3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_selected_option));
                    btn4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_purple));
                    btn1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_purple));
                    btn2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_purple));
                }
                else if(checkedId == R.id.btn_choice_4) {
                        btn4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_selected_option));
                        btn1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_purple));
                        btn2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_purple));
                        btn3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_purple));
                }
            }
        });

        //btn next
        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check chọn câu trả lời hay chưa
                if (!answered){
                    //Nếu chọn đáp án
                    if(btn1.isChecked() || btn2.isChecked() || btn3.isChecked() || btn4.isChecked()){
                        // kiểm tra
                        checkAnswer();
                    }

                    else {
                        Toast.makeText(QuizActivity.this,"Hãy chọn đáp án",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
//                    rbGroup.clearCheck();
                    showNextQuestion();
                }
            }
        });

        btnBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBackwardConfirmationDialog();
            }
        });
    }

    private void showBackwardConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("XÁC NHẬN");
        builder.setMessage("Bạn có chắc chắn là muốn thoát?");

        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(QuizActivity.this, CategoryActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked "No," do nothing or handle as needed
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showNextQuestion(){
        textViewExplain.setVisibility(View.GONE);
        // xóa check
        rbGroup.clearCheck();
        //set màu đen
        btn1.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_purple));
        btn2.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_purple));
        btn3.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_purple));
        btn4.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_purple));

        btnForward.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_next_question));
        btnForward.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        btnForward.setText("KIỂM TRA ĐÁP ÁN");
        btn1.setEnabled(true);
        btn1.setClickable(true);
        btn2.setEnabled(true);
        btn2.setClickable(true);
        btn3.setEnabled(true);
        btn3.setClickable(true);
        btn4.setEnabled(true);
        btn4.setClickable(true);

        //nếu còn câu hỏi
        if (questionCounter < questionCountTotal){
            //Lay du lieu o vi tri counter
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            btn1.setText(currentQuestion.getOption1());
            btn2.setText(currentQuestion.getOption2());
            btn3.setText(currentQuestion.getOption3());
            btn4.setText(currentQuestion.getOption4());
//            textViewExplain.setText(currentQuestion.getExplain());

            btn3.setVisibility(View.VISIBLE);
            btn4.setVisibility(View.VISIBLE);

            imgQuestion.setImageResource(getResources().getIdentifier(currentQuestion.getImage()+"","drawable","com.example.myapplication"));

            if ( currentQuestion.getOption3() == null){
                btn3.setVisibility(View.GONE);
                btn4.setVisibility(View.GONE);
            } else if (currentQuestion.getOption4() == null) {
                btn4.setVisibility(View.GONE);
            }

            questionCounter++;
            //vị trí câu hỏi hiện tại
            textViewQuestionCount.setText(questionCounter + "/" + questionCountTotal);
            //giá trị false chưa trả lời
            answered = false;
        }
        //thoát ra giao diện chính
        else {
            finalScoreDlg.finalScoredlg(score, checkIncorrectSpecial,questionCountTotal);
        }
    }

    // Phương thức đếm ngược
    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                //Hết giờ
                timeLeftInMillis = 0;
                updateCountDownText();
                //Phương thức kiểm tra thời gian
                checkAnswer();
            }
        }.start();
    }

    private void checkAnswer()
    {
        answered = true;
        //Trả về nút đã chọn
        RadioButton rdbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answer = rbGroup.indexOfChild(rdbSelected) + 1;

        if (currentQuestion.getSpecial() == 1 && answer != currentQuestion.getAnswerNumber()) {
            checkIncorrectSpecial = true;
        } else {
            if (answer == currentQuestion.getAnswerNumber()) {
                score += 1;
                textViewScore.setText("Điểm: " + score);
            }

        }
        showSolution(answer, rdbSelected);
    }

    private void showSolution(int answer, RadioButton rdbSelected){
        textViewExplain.setText(currentQuestion.getExplain());
        Drawable forwardIcon = ContextCompat.getDrawable(this, R.drawable.ic_next_question_arrow);
        btnForward.setCompoundDrawablesWithIntrinsicBounds(null, null, forwardIcon, null);

        if(currentQuestion.getExplain() != null){
            textViewExplain.setVisibility(View.VISIBLE);
        }



        if (questionCounter < questionCountTotal){
            btnForward.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_next_question));
            btnForward.setText("CÂU TIẾP THEO");
        }
        else {
            btnForward.setText("HOÀN THÀNH");
            btnForward.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_next_question));
            btnForward.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }

        btn1.setEnabled(false);
        btn1.setClickable(false);
        btn2.setEnabled(false);
        btn2.setClickable(false);
        btn3.setEnabled(false);
        btn3.setClickable(false);
        btn4.setEnabled(false);
        btn4.setClickable(false);

        rdbSelected.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_red));
        switch (currentQuestion.getAnswerNumber()){
            case 1:
                btn1.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_success));
                break;
            case 2:
                btn2.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_success));
                break;
            case 3:
                btn3.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_success));
                break;
            case 4:
                btn4.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_success));
                break;
        }
//        countDownTimer.cancel();
    }
    private void updateCountDownText(){
        int minutes = (int) ((timeLeftInMillis/1000)/60);
        int seconds = (int) ((timeLeftInMillis/1000)%60);
        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        textViewCountdown.setText(timeFormatted);
        if(timeLeftInMillis < 1000){
            textViewCountdown.setTextColor(Color.RED);
        }
//        else{
//            textViewCountdown.setTextColor(Color.BLACK);
//        }
    }
    private void finishQuiz(){
        finish();
    }
}