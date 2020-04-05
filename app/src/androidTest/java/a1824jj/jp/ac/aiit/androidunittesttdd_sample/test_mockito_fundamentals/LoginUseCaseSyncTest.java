package a1824jj.jp.ac.aiit.androidunittesttdd_sample.test_mockito_fundamentals;

import android.accounts.NetworkErrorException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;


import java.util.List;

import a1824jj.jp.ac.aiit.androidunittesttdd_sample.test_doubles_fundamentals.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class LoginUseCaseSyncTest {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String AUTH_TOKEN = "AuthToken";

    LoginUseCaseSync SUT;

    LoginHttpEndpointSync mLoginHttpEndpointSyncMock;
    AuthTokenCache mAuthTokenCacheMock;
    EventBusPoster mEventBusPosterMock;

    @Before
    public void setup() throws Exception{
        mLoginHttpEndpointSyncMock = Mockito.mock(LoginHttpEndpointSync.class);
        mAuthTokenCacheMock = Mockito.mock(AuthTokenCache.class);
        mEventBusPosterMock = Mockito.mock(EventBusPoster.class);

        SUT = new LoginUseCaseSync(mLoginHttpEndpointSyncMock, mAuthTokenCacheMock, mEventBusPosterMock);

        when(mLoginHttpEndpointSyncMock.loginSync(any(String.class), any(String.class)))
                .thenReturn(new LoginHttpEndpointSync.EndpointResult(LoginHttpEndpointSync.EndpointResultStatus.SUCCESS, AUTH_TOKEN));
    }

    @Test
    public void loginSync_success_usernameAndPasswordPassedToEndPoint() throws NetworkErrorException {
        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
        SUT.loginSync(USERNAME, PASSWORD);
        verify(mLoginHttpEndpointSyncMock, times(1)).loginSync(ac.capture(), ac.capture());
        List<String> captures = ac.getAllValues();
        assertThat(captures.get(0), is(USERNAME));
        assertThat(captures.get(1), is(PASSWORD));
    }


    @Test
    public void loginSync_success_authTokenCached() {

    }


    @Test
    public void loginSync_generalError_authTokenNotCached() {

    }

    @Test
    public void loginSync_authError_authTokenNotCached() {

    }

    @Test
    public void loginSync_serverError_authTokenNotCached() {

    }

    @Test
    public void loginSync_success_loggedInEventPosted() {

    }

    @Test
    public void loginSync_generalError_noInteractionWithEventBusPoster() {

    }

    @Test
    public void loginSync_authError_noInteractionWithEventBusPoster() {

    }

    @Test
    public void loginSync_serverError_noInteractionWithEventBusPoster() {

    }

    @Test
    public void loginSync_success_successReturned() {

    }

    @Test
    public void loginSync_serverError_failureReturned() {

    }

    @Test
    public void loginSync_authError_failureReturned() {

    }

    @Test
    public void loginSync_generalError_failureReturned() {

    }

    @Test
    public void loginSync_networkError_networkErrorReturned() {

    }

}