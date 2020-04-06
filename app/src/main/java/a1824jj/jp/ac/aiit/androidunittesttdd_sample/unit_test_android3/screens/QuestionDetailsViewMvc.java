package a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.screens;

import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.questions.QuestionDetails;

public interface QuestionDetailsViewMvc extends ObservableViewMvc<QuestionDetailsViewMvc.Listener> {

    public interface Listener {
        void onNavigateUpClicked();
    }

    void bindQuestion(QuestionDetails question);

    void showProgressIndication();

    void hideProgressIndication();
}
