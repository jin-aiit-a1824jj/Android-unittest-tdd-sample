package a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.screens;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.common.time.TimeProvider;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.questions.FetchLastActiveQuestionsUseCase;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.questions.Question;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.testdata.QuestionsTestData;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class QuestionsListControllerTest {

    // region constants
    private static final List<Question> QUESTIONS = QuestionsTestData.getQuestions();
    private static final Question QUESTION = QuestionsTestData.getQuestion();
    // endregion constants

    // region helper fields
    private UseCaseTd mUseCaseTd;
    @Mock ScreensNavigator mScreensNavigator;
    @Mock ToastsHelper mToastsHelper;
    @Mock TimeProvider mTimeProvider;

    @Mock QuestionsListViewMvc mQuestionsListViewMvc;

    // endregion helper fields

    QuestionsListController SUT;

    @Before
    public void setup() throws Exception {
        mUseCaseTd = new UseCaseTd();
        SUT = new QuestionsListController(mUseCaseTd, mScreensNavigator, mToastsHelper, mTimeProvider);
        SUT.bindView(mQuestionsListViewMvc);
    }

    @Test
    public void onStart_successfulResponse_questionsBoundToView() throws Exception {
        // Arrange
        success();
        // Act
        SUT.onStart();
        // Assert
        verify(mQuestionsListViewMvc).bindQuestions(QUESTIONS);
    }

    @Test
    public void onStart_failure_errorToastShown() throws Exception {
        // Arrange
        failure();
        // Act
        SUT.onStart();
        // Assert
        verify(mToastsHelper).showUseCaseError();
    }

    @Test
    public void onStart_failure_questionsNotBoundToView() throws Exception {
        // Arrange
        failure();
        // Act
        SUT.onStart();
        // Assert
        verify(mQuestionsListViewMvc, never()).bindQuestions(any(List.class));
    }

    @Test
    public void onStart_listenersRegistered() throws Exception {
        // Arrange
        // Act
        SUT.onStart();
        // Assert
        verify(mQuestionsListViewMvc).registerListener(SUT);
        mUseCaseTd.verifyListenerRegistered(SUT);
    }

    @Test
    public void onStop_listenersUnregistered() throws Exception {
        // Arrange
        // Act
        SUT.onStop();
        // Assert
        verify(mQuestionsListViewMvc).unregisterListener(SUT);
        mUseCaseTd.verifyListenerNotRegistered(SUT);
    }

    @Test
    public void onQuestionClicked_navigatedToQuestionDetailsScreen() throws Exception {
        // Arrange
        // Act
        SUT.onQuestionClicked(QUESTION);
        // Assert
        verify(mScreensNavigator).toQuestionDetails(QUESTION.getId());
    }

    @Test
    public void onStart_secondTimeAfterSuccessfulResponse_questionsNotBoundToViewFromCache() throws Exception {
        // Arrange
        success();
        // Act
        SUT.onStart();
        SUT.onStart();
        // Assert
        verify(mQuestionsListViewMvc, times(2)).bindQuestions(QUESTIONS);
        assertThat(mUseCaseTd.getCallCount(), is(1));
    }

    @Test
    public void onStart_progressIndicationShown() throws Exception {
        // Arrange
        // Act
        SUT.onStart();
        // Assert
        verify(mQuestionsListViewMvc).showProgressIndication();
    }

    @Test
    public void onStart_successfulResponse_progressIndicationHidden() throws Exception {
        // Arrange
        success();
        // Act
        SUT.onStart();
        // Assert
        verify(mQuestionsListViewMvc).hideProgressIndication();
    }

    @Test
    public void onStart_failure_progressIndicationHidden() throws Exception {
        // Arrange
        failure();
        // Act
        SUT.onStart();
        // Assert
        verify(mQuestionsListViewMvc).hideProgressIndication();
    }

    @Test
    public void onStart_secondTimeAfterCachingTimeout_questionsBoundToViewFromUseCase() throws Exception {
        // Arrange
        emptyQuestionsListOnFirstCall();
        when(mTimeProvider.getCurrentTimestamp()).thenReturn(0l);
        // Act
        SUT.onStart();
        SUT.onStop();
        when(mTimeProvider.getCurrentTimestamp()).thenReturn(10000l);
        SUT.onStart();
        // Assert
        verify(mQuestionsListViewMvc).bindQuestions(QUESTIONS);
    }

    @Test
    public void onStart_secondTimeRightBeforeCachingTimeout_questionsBoundToViewFromCache() throws Exception {
        // Arrange
        when(mTimeProvider.getCurrentTimestamp()).thenReturn(0l);
        // Act
        SUT.onStart();
        SUT.onStop();
        when(mTimeProvider.getCurrentTimestamp()).thenReturn(9999l);
        SUT.onStart();
        // Assert
        verify(mQuestionsListViewMvc, times(2)).bindQuestions(QUESTIONS);
        assertThat(mUseCaseTd.getCallCount(), is(1));
    }

     // region helper methods

    private void success() {
        //not use now
    }

    private void failure() {
        mUseCaseTd.mFailure = true;
    }

    private void emptyQuestionsListOnFirstCall() {

        mUseCaseTd.mEmptyListOnFirstCall = true;
    }

    // endregion helper methods

    // region helper classes

    private static class UseCaseTd extends FetchLastActiveQuestionsUseCase {
        public boolean mFailure;
        public boolean mEmptyListOnFirstCall;
        private int mCallCount;

        public UseCaseTd() {
            super(null);
        }

        @Override
        public void fetchLastActiveQuestionsAndNotify() {
            mCallCount++;
            for (FetchLastActiveQuestionsUseCase.Listener listener: getListeners()){
                if (mFailure)
                    listener.onLastActiveQuestionsFetchFailed();
                else{
                    if (mEmptyListOnFirstCall && mCallCount == 1){
                        listener.onLastActiveQuestionsFetched(new LinkedList<Question>());
                    } else {
                        listener.onLastActiveQuestionsFetched(QUESTIONS);
                    }
                }
            }
        }

        public void verifyListenerRegistered(QuestionsListController candidate) {
            for (FetchLastActiveQuestionsUseCase.Listener listener : getListeners()){
                if(listener == candidate){
                    return;
                }
            }
            throw new RuntimeException("listener not registered");
        }

        public void verifyListenerNotRegistered(QuestionsListController candidate) {
            for (FetchLastActiveQuestionsUseCase.Listener listener : getListeners()){
                if(listener == candidate){
                    throw new RuntimeException("listener not registered");
                }
            }
        }

        public int getCallCount() {
            return mCallCount;
        }
    }


    // endregion helper classes

}