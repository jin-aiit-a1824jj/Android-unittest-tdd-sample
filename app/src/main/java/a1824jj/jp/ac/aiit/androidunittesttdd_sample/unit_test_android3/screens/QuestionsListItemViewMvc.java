package a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.screens;

import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.questions.Question;

public interface QuestionsListItemViewMvc extends ObservableViewMvc<QuestionsListItemViewMvc.Listener> {

    public interface Listener {
        void onQuestionClicked(Question question);
    }

    void bindQuestion(Question question);
}