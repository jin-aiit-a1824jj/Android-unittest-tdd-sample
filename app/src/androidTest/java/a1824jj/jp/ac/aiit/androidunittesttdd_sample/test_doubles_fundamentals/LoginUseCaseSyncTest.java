package a1824jj.jp.ac.aiit.androidunittesttdd_sample.test_doubles_fundamentals;

import a1824jj.jp.ac.aiit.androidunittesttdd_sample.test_doubles_fundamentals.LoginUseCaseSync.UseCaseResult;

import android.accounts.NetworkErrorException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LoginUseCaseSyncTest {

    LoginUseCaseSync SUT;

    LoginHttpEndpointSyncTd mLoginHttpEndpointSyncTd;
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String AUTH_TOKEN = "AuthToken";
    AuthTokenCacheTd mAuthTokenCacheTd;
    EventBusPosterTd mEventBusPosterTd;

    @Before
    public void setup(){
        mLoginHttpEndpointSyncTd = new LoginHttpEndpointSyncTd();
        mAuthTokenCacheTd = new AuthTokenCacheTd();
        mEventBusPosterTd = new EventBusPosterTd();
        SUT = new LoginUseCaseSync(mLoginHttpEndpointSyncTd, mAuthTokenCacheTd, mEventBusPosterTd);
    }

    private static class LoginHttpEndpointSyncTd implements LoginHttpEndpointSync {

        public String mUsername = "";
        public String mPassword = "";
        public boolean mIsGeneralError = false;
        public boolean mIsAuthError = false;
        public boolean mIsServerError = false;
        public boolean mIsNetworkError = false;

        @Override
        public EndpointResult loginSync(String username, String password) throws NetworkErrorException {
            mUsername = username;
            mPassword = password;
            if (mIsGeneralError){
                return new EndpointResult(EndpointResultStatus.GENERAL_ERROR, "");
            }else if(mIsAuthError) {
                return new EndpointResult(EndpointResultStatus.AUTH_ERROR, "");
            }else if(mIsServerError){
                return new EndpointResult(EndpointResultStatus.SERVER_ERROR, "");
            }else if (mIsNetworkError) {
                throw new NetworkErrorException();
            }else{
                return new EndpointResult(EndpointResultStatus.SUCCESS, AUTH_TOKEN);
            }

        }
    }

    private static class AuthTokenCacheTd implements AuthTokenCache {

        String mAuthToken = "";

        @Override
        public void cacheAuthToken(String authToken) {
            mAuthToken = authToken;
        }

        @Override
        public String getAuthToken() {
            return mAuthToken;
        }
    }

    private static class EventBusPosterTd implements EventBusPoster {
        public Object mEvent;
        public int mInteractionsCount;

        @Override
        public void postEvent(Object event) {
            mInteractionsCount++;
            mEvent = event;
        }
    }


    @Test
    public void loginSync_success_usernameAndPasswordPassedToEndPoint(){
        SUT.loginSync(USERNAME,PASSWORD);
        Assert.assertThat(mLoginHttpEndpointSyncTd.mUsername, is(USERNAME));
        Assert.assertThat(mLoginHttpEndpointSyncTd.mPassword, is(PASSWORD));
    }


    @Test
    public void loginSync_success_authTokenCached() {
        SUT.loginSync(USERNAME,PASSWORD);
        assertThat(mAuthTokenCacheTd.getAuthToken(), is(AUTH_TOKEN));
    }


    @Test
    public void loginSync_generalError_authTokenNotCached() {
        mLoginHttpEndpointSyncTd.mIsGeneralError = true;
        SUT.loginSync(USERNAME, PASSWORD);
        assertThat(mAuthTokenCacheTd.getAuthToken(), is(""));
    }

    @Test
    public void loginSync_authError_authTokenNotCached() {
        mLoginHttpEndpointSyncTd.mIsAuthError = true;
        SUT.loginSync(USERNAME, PASSWORD);
        assertThat(mAuthTokenCacheTd.getAuthToken(), is(""));
    }

    @Test
    public void loginSync_serverError_authTokenNotCached() {
        mLoginHttpEndpointSyncTd.mIsServerError = true;
        SUT.loginSync(USERNAME, PASSWORD);
        assertThat(mAuthTokenCacheTd.getAuthToken(), is(""));
    }

    @Test
    public void loginSync_success_loggedInEventPosted() {
        SUT.loginSync(USERNAME, PASSWORD);
        assertThat(mEventBusPosterTd.mEvent, is(instanceOf(LoggedInEvent.class)));
    }

    @Test
    public void loginSync_generalError_noInteractionWithEventBusPoster() {
        mLoginHttpEndpointSyncTd.mIsGeneralError = true;
        SUT.loginSync(USERNAME, PASSWORD);
        assertThat(mEventBusPosterTd.mInteractionsCount, is(0));
    }

    @Test
    public void loginSync_authError_noInteractionWithEventBusPoster() {
        mLoginHttpEndpointSyncTd.mIsAuthError = true;
        SUT.loginSync(USERNAME, PASSWORD);
        assertThat(mEventBusPosterTd.mInteractionsCount, is(0));
    }

    @Test
    public void loginSync_serverError_noInteractionWithEventBusPoster() {
        mLoginHttpEndpointSyncTd.mIsServerError = true;
        SUT.loginSync(USERNAME, PASSWORD);
        assertThat(mEventBusPosterTd.mInteractionsCount, is(0));
    }

    @Test
    public void loginSync_success_successReturned() {
        UseCaseResult result = SUT.loginSync(USERNAME, PASSWORD);
        assertThat(result, is(UseCaseResult.SUCCESS));
    }

    @Test
    public void loginSync_serverError_failureReturned() {
        mLoginHttpEndpointSyncTd.mIsServerError = true;
        UseCaseResult result = SUT.loginSync(USERNAME, PASSWORD);
        assertThat(result, is(UseCaseResult.FAILURE));
    }

    @Test
    public void loginSync_authError_failureReturned() {
        mLoginHttpEndpointSyncTd.mIsAuthError = true;
        UseCaseResult result = SUT.loginSync(USERNAME, PASSWORD);
        assertThat(result, is(UseCaseResult.FAILURE));
    }

    @Test
    public void loginSync_generalError_failureReturned() {
        mLoginHttpEndpointSyncTd.mIsGeneralError = true;
        UseCaseResult result = SUT.loginSync(USERNAME, PASSWORD);
        assertThat(result, is(UseCaseResult.FAILURE));
    }

    @Test
    public void loginSync_networkError_networkErrorReturned() {
        mLoginHttpEndpointSyncTd.mIsNetworkError = true;
        UseCaseResult result = SUT.loginSync(USERNAME, PASSWORD);
        assertThat(result, is(UseCaseResult.NETWORK_ERROR));
    }
}