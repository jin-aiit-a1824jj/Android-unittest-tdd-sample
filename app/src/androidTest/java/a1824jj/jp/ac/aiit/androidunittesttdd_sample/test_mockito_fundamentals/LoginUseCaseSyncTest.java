package a1824jj.jp.ac.aiit.androidunittesttdd_sample.test_mockito_fundamentals;

import org.junit.Before;
import org.junit.Test;

import a1824jj.jp.ac.aiit.androidunittesttdd_sample.test_doubles_fundamentals.*;

public class LoginUseCaseSyncTest {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String AUTH_TOKEN = "AuthToken";

    LoginUseCaseSync SUT;

    @Before
    public void setup(){

    }

    @Test
    public void loginSync_success_usernameAndPasswordPassedToEndPoint(){

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