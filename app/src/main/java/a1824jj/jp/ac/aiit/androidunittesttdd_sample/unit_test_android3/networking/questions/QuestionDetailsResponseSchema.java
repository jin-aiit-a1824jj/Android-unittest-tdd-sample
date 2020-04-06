package a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.networking.questions;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class QuestionDetailsResponseSchema {

    @SerializedName("items")
    private final List<QuestionSchema> mQuestions;

    public QuestionDetailsResponseSchema(QuestionSchema question) {
        mQuestions = Collections.singletonList(question);
    }

    public QuestionSchema getQuestion() {
        return mQuestions.get(0);
    }

}
