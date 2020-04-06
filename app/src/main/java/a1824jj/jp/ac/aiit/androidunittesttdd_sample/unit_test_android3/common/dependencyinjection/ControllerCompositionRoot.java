package a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.common.dependencyinjection;

import android.content.Context;
import android.view.LayoutInflater;

import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.screens.BackPressDispatcher;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.networking.questions.FetchLastActiveQuestionsEndpoint;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.questions.FetchLastActiveQuestionsUseCase;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.questions.FetchQuestionDetailsUseCase;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.screens.FragmentFrameHelper;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.screens.FragmentFrameWrapper;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.screens.NavDrawerHelper;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.screens.QuestionDetailsController;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.screens.QuestionsListController;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.screens.ScreensNavigator;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.networking.StackoverflowApi;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.screens.ToastsHelper;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.screens.ViewMvcFactory;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.common.time.TimeProvider;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class ControllerCompositionRoot {

    private final CompositionRoot mCompositionRoot;
    private final FragmentActivity mActivity;

    public ControllerCompositionRoot(CompositionRoot compositionRoot, FragmentActivity activity) {
        mCompositionRoot = compositionRoot;
        mActivity = activity;
    }

    private FragmentActivity getActivity() {
        return mActivity;
    }

    private Context getContext() {
        return mActivity;
    }

    private FragmentManager getFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }

    private StackoverflowApi getStackoverflowApi() {
        return mCompositionRoot.getStackoverflowApi();
    }

    private FetchLastActiveQuestionsEndpoint getFetchLastActiveQuestionsEndpoint() {
        return new FetchLastActiveQuestionsEndpoint(getStackoverflowApi());
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(getContext());
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(getLayoutInflater(), getNavDrawerHelper());
    }

    private NavDrawerHelper getNavDrawerHelper() {
        return (NavDrawerHelper) getActivity();
    }

    public FetchQuestionDetailsUseCase getFetchQuestionDetailsUseCase() {
        return mCompositionRoot.getFetchQuestionDetailsUseCase();
    }

    public FetchLastActiveQuestionsUseCase getFetchLastActiveQuestionsUseCase() {
        return new FetchLastActiveQuestionsUseCase(getFetchLastActiveQuestionsEndpoint());
    }

    public TimeProvider getTimeProvider() {
        return mCompositionRoot.getTimeProvider();
    }

    public QuestionsListController getQuestionsListController() {
        return new QuestionsListController(
                getFetchLastActiveQuestionsUseCase(),
                getScreensNavigator(),
                getToastsHelper(),
                getTimeProvider());
    }

    public ToastsHelper getToastsHelper() {
        return new ToastsHelper(getContext());
    }

    public ScreensNavigator getScreensNavigator() {
        return new ScreensNavigator(getFragmentFrameHelper());
    }

    private FragmentFrameHelper getFragmentFrameHelper() {
        return new FragmentFrameHelper(getActivity(), getFragmentFrameWrapper(), getFragmentManager());
    }

    private FragmentFrameWrapper getFragmentFrameWrapper() {
        return (FragmentFrameWrapper) getActivity();
    }

    public BackPressDispatcher getBackPressDispatcher() {
        return (BackPressDispatcher) getActivity();
    }

    public QuestionDetailsController getQuestionDetailsController() {
        return new QuestionDetailsController(getFetchQuestionDetailsUseCase(), getScreensNavigator(), getToastsHelper());
    }

}
