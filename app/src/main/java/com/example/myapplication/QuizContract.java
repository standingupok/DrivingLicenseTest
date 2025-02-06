package com.example.myapplication;

import android.provider.BaseColumns;

public final class QuizContract {
    private QuizContract() {}
    public static class CategoryTable implements BaseColumns{
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME = "name";
    }

    public static class TypeTable implements BaseColumns{
        public static final String TABLE_NAME = "type";
        public static final String COLUMN_NAME = "name";
    }


    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_ANSWER_NUMBER = "answer_number";
        public static final String COLUMN_IMG = "image";
        public static final String COLUMN_EXPLAIN = "explain";
        public static final String COLUMN_SPECIAL = "special";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_CATEGORY_ID = "id_category";
    }
}
