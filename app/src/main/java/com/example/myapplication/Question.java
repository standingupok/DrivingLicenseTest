package com.example.myapplication;

import android.graphics.Picture;

public class Question {
    private int id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int answerNumber;
    private String imageQuest;
    private String explain;
    private int special;
    private int type;
    private int categoryId;

    public Question(){}
    public Question( String question, String option1, String option2, String option3, String option4, int answerNumber, String imageQuest, String explain, int special, int type, int categoryId) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answerNumber = answerNumber;
        this.imageQuest = imageQuest;
        this.explain = explain;
        this.special = special;
        this.type = type;
        this.categoryId = categoryId;
    }
    public int getQuestionId() {
        return id;
    }
    public void setQuestionId(int id){this.id = id;}

    public String getQuestion() {
        return question;
    }


    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }


    public void setOption3(String option3) {
        this.option3 = option3;
    }
    public String getOption4() {
        return option4;
    }
    public void setOption4(String option4) {
        this.option4 = option4;
    }
    public int getAnswerNumber() {
        return answerNumber;
    }
    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }
    public String getImage(){return imageQuest;}
    public void setImage(String imageQuest){this.imageQuest = imageQuest;}
    public String getExplain(){return explain;}
    public void setExplain(String explain){this.explain = explain;}
    public int getSpecial(){return special;}
    public void setSpecial(int special){this.special = special;}
    public int getType(){return type;}
    public void setType(int type){this.type = type;}
    public int getCategory(){return categoryId;}
    public void setCategory(int categoryId){this.categoryId = categoryId;}
}
