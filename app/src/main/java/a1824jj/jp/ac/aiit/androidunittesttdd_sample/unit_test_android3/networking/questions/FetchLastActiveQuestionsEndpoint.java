package a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.networking.questions;

import java.util.List;

import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.common.Constants;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.networking.StackoverflowApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchLastActiveQuestionsEndpoint {

    public interface Listener {
        void onQuestionsFetched(List<QuestionSchema> questions);
        void onQuestionsFetchFailed();
    }

    private  final StackoverflowApi mStackoverflowApi;

    public FetchLastActiveQuestionsEndpoint(StackoverflowApi stackoverflowApi) {
        mStackoverflowApi = stackoverflowApi;
    }

    public void fetchLastActiveQuestions(final Listener listener) {
        mStackoverflowApi.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
                .enqueue(new Callback<QuestionsListResponseSchema>() {
                             @Override
                             public void onResponse(Call<QuestionsListResponseSchema> call, Response<QuestionsListResponseSchema> response) {
                                 if (response.isSuccessful()) {
                                     listener.onQuestionsFetched(response.body().getQuestions());
                                 } else {
                                     listener.onQuestionsFetchFailed();
                                 }
                             }

                             @Override
                             public void onFailure(Call<QuestionsListResponseSchema> call, Throwable t) {
                                 listener.onQuestionsFetchFailed();
                             }
                         }
                );
    }

}
