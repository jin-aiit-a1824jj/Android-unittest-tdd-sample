package a1824jj.jp.ac.aiit.androidunittesttdd_sample.test_doubles_fundamentals;

import android.accounts.NetworkErrorException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LoginUseCaseSyncTest {

    LoginUseCaseSync SUT;

    LoginHttpEndpointSyncTd mLoginHttpEndpointSyncTd;
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String AUTH_TOKEN = "AuthToken";
    AuthTokenCacheTd mAuthTokenCacheTd;

    @Before
    public void setup(){
        mLoginHttpEndpointSyncTd = new LoginHttpEndpointSyncTd();
        mAuthTokenCacheTd = new AuthTokenCacheTd();
        SUT = new LoginUseCaseSync(mLoginHttpEndpointSyncTd, mAuthTokenCacheTd, new EventBusPosterTd());
    }

    private static class LoginHttpEndpointSyncTd implements LoginHttpEndpointSync {

        public String mUsername;
        public String mPassword;
        public boolean mIsGeneralError;
        public boolean mIsAuthError;
        public boolean mIsServerError;

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
            }else{
                return new EndpointResult(EndpointResultStatus.SUCCESS, AUTH_TOKEN);
            }

        }
    }

    private static class AuthTokenCacheTd implements AuthTokenCache {

        String mAuthToken;

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

        @Override
        public void postEvent(Object event) {

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
}