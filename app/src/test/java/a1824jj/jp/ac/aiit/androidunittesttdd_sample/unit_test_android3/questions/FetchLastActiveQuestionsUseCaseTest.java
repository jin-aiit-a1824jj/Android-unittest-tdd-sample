package a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.questions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.networking.StackoverflowApi;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.networking.questions.FetchLastActiveQuestionsEndpoint;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.networking.questions.QuestionSchema;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class FetchLastActiveQuestionsUseCaseTest {

    // region constants

    // endregion constants

    // region helper fields
    private EndpointTd mEndpointTd;
    @Mock FetchLastActiveQuestionsUseCase.Listener mListener1;
    @Mock FetchLastActiveQuestionsUseCase.Listener mListener2;

    @Captor ArgumentCaptor<List<Question>> mQuestionsCaptor;
    // endregion helper fields

    FetchLastActiveQuestionsUseCase SUT;

    @Before
    public void setup() throws Exception {
        mEndpointTd = new EndpointTd();
        SUT = new FetchLastActiveQuestionsUseCase(mEndpointTd);

    }

    @Test
    public void fetchLastActiveQuestionsAndNotify_success_listenersNotifiedWithCorrectData() throws Exception {
        // Arrange
        success();
        SUT.registerListener(mListener1);
        SUT.registerListener(mListener2);
        // Act
        SUT.fetchLastActiveQuestionsAndNotify();
        // Assert
        verify(mListener1).onLastActiveQuestionsFetched(mQuestionsCaptor.capture());
        verify(mListener2).onLastActiveQuestionsFetched(mQuestionsCaptor.capture());

        List<List<Question>> questionLists = mQuestionsCaptor.getAllValues();
        assertThat(questionLists.get(0), is(getExceptedQuestions()));
        assertThat(questionLists.get(1), is(getExceptedQuestions()));
    }


    @Test
    public void fetchLastActiveQuestionsAndNotify_failure_listenersNotifiedOfFailure() throws Exception {
        // Arrange
        failure();
        SUT.registerListener(mListener1);
        SUT.registerListener(mListener2);
        // Act
        SUT.fetchLastActiveQuestionsAndNotify();
        // Assert
        verify(mListener1).onLastActiveQuestionsFetchFailed();
        verify(mListener2).onLastActiveQuestionsFetchFailed();
    }

    // region helper methods
    private void success() {
        //not use now
    }

    private List<Question> getExceptedQuestions() {
        List<Question> questionSchemas = new LinkedList<>();
        questionSchemas.add(new Question("id1","title1"));
        questionSchemas.add(new Question("id2","title2"));
        return questionSchemas;
    }


    private void failure() {
        mEndpointTd.failure = true;
    }

    // endregion helper methods

    // region helper classes
    private static class EndpointTd extends FetchLastActiveQuestionsEndpoint {

        public boolean failure;

        public EndpointTd() {
            super(null);
        }

        @Override
        public void fetchLastActiveQuestions(Listener listener) {
            if (failure){
                listener.onQuestionsFetchFailed();;
            }else{
                List<QuestionSchema> questionSchemas = new LinkedList<>();
                questionSchemas.add(new QuestionSchema("title1","id1","body1"));
                questionSchemas.add(new QuestionSchema("title2","id2","body2"));
                listener.onQuestionsFetched(questionSchemas);
            }
        }
    }
    // endregion helper classes

}