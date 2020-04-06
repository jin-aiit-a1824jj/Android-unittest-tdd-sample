package a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.networking.questions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionsListResponseSchema {

    @SerializedName("items")
    private final List<QuestionSchema> mQuestions;

    public QuestionsListResponseSchema(List<QuestionSchema> questions) {
        mQuestions = questions;
    }

    public List<QuestionSchema> getQuestions() {
        return mQuestions;
    }

}
