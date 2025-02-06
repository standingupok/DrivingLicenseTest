package com.example.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalScoreDlg {
    private Context context;
    private Dialog finalScoreDlg;
    private TextView textViewFinalScore;
    private TextView textViewResult;
    public FinalScoreDlg(Context context){
        this.context = context;
    }
    public void finalScoredlg(int score,boolean checkIncorrectSpecial, int questionCountTotal){
        finalScoreDlg = new Dialog(context);
        finalScoreDlg.setContentView(R.layout.activity_score_dialogue);

        final Button btnFinalScore = (Button) finalScoreDlg.findViewById(R.id.btn_score_dialogue);

        finalScoreCal(score,checkIncorrectSpecial,questionCountTotal);

        btnFinalScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalScoreDlg.dismiss();
                Intent it;
                if(questionCountTotal == 25){
                     it = new Intent(context,CategoryActivity.class);
                }
                else{
                     it = new Intent(context, TypeActivity.class);
                }
                context.startActivity(it);
            }
        });

        finalScoreDlg.show();
        finalScoreDlg.setCancelable(false);
        finalScoreDlg.setCanceledOnTouchOutside(false);
    }

    private void finalScoreCal(int score, boolean checkIncorrectSpecial,int questionCountTotal){
        textViewFinalScore = (TextView) finalScoreDlg.findViewById(R.id.text_view_final_score);
        textViewResult = (TextView) finalScoreDlg.findViewById(R.id.text_view_result);
        textViewFinalScore.setText("Điểm: "  +String.valueOf(score) + "/" + questionCountTotal);
        if(questionCountTotal == 25){
            if (score < 21 || checkIncorrectSpecial ){
                textViewResult.setText("BẠN ĐÃ RỚT");
            }
        }
        else{
            textViewResult.setVisibility(View.INVISIBLE);
        }
    }
}
