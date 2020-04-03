package a1824jj.jp.ac.aiit.androidunittesttdd_sample.test_doubles_fundamentals2;

public class UserInputValidator {

    public boolean isValidFullName(String fullName) {
        return FullNameValidator.isValidFullName(fullName);
    }

    public boolean isValidUsername(String username) {
        return ServerUsernameValidator.isValidUsername(username);
    }

}
