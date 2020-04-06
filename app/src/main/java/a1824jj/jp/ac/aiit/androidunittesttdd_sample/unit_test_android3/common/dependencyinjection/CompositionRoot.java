package a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.common.dependencyinjection;

import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.networking.questions.FetchQuestionDetailsEndpoint;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.questions.FetchQuestionDetailsUseCase;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.networking.StackoverflowApi;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.common.Constants;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.common.time.TimeProvider;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompositionRoot {

    private Retrofit mRetrofit;
    private FetchQuestionDetailsUseCase mFetchQuestionDetailsUseCase;

    private Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    public StackoverflowApi getStackoverflowApi() {
        return getRetrofit().create(StackoverflowApi.class);
    }

    public TimeProvider getTimeProvider() {
        return new TimeProvider();
    }

    private FetchQuestionDetailsEndpoint getFetchQuestionDetailsEndpoint() {
        return new FetchQuestionDetailsEndpoint(getStackoverflowApi());
    }

    public FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase() {
        if (mFetchQuestionDetailsUseCase == null) {
            mFetchQuestionDetailsUseCase = new FetchQuestionDetailsUseCase(getFetchQuestionDetailsEndpoint(), getTimeProvider());
        }
        return mFetchQuestionDetailsUseCase;
    }

}
