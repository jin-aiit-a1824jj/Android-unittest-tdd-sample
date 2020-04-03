package a1824jj.jp.ac.aiit.androidunittesttdd_sample.test_doubles_fundamentals;

import android.accounts.NetworkErrorException;

import org.junit.Before;

import static org.junit.Assert.*;

public class LoginUseCaseSyncTest {

    LoginUseCaseSync SUT;

    @Before
    public void setup(){
        SUT = new LoginUseCaseSync(new LoginHttpEndpointSyncTd(), new AuthTokenCacheTd(), new EventBusPosterTd());
    }

    private static class LoginHttpEndpointSyncTd implements LoginHttpEndpointSync {

        @Override
        public EndpointResult loginSync(String username, String password) throws NetworkErrorException {
            return null;
        }
    }

    private static class AuthTokenCacheTd implements AuthTokenCache {

        @Override
        public void cacheAuthToken(String authToken) {

        }

        @Override
        public String getAuthToken() {
            return null;
        }
    }

    private static class EventBusPosterTd implements EventBusPoster {

        @Override
        public void postEvent(Object event) {

        }
    }
}