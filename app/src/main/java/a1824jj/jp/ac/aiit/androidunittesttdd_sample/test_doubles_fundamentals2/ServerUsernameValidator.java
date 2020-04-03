package a1824jj.jp.ac.aiit.androidunittesttdd_sample.test_doubles_fundamentals2;

public class ServerUsernameValidator {

    public static boolean isValidUsername(String username) {

        try {
            Thread.sleep(1000);
            throw new RuntimeException("no network connection");
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

}
