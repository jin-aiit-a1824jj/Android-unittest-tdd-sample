package a1824jj.jp.ac.aiit.androidunittesttdd_sample.test_doubles_fundamentals;

public interface AuthTokenCache {

    void cacheAuthToken(String authToken);

    String getAuthToken();
}