package a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.screens;

import java.util.List;

import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.questions.Question;

public interface QuestionsListViewMvc extends ObservableViewMvc<QuestionsListViewMvc.Listener> {

    public interface Listener {
        void onQuestionClicked(Question question);
    }

    void bindQuestions(List<Question> questions);

    void showProgressIndication();

    void hideProgressIndication();

}
