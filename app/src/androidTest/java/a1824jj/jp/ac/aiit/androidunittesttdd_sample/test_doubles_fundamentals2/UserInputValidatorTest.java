package a1824jj.jp.ac.aiit.androidunittesttdd_sample.test_doubles_fundamentals2;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserInputValidatorTest {

    UserInputValidator SUT;

    @Before
    public void setup() {
        SUT = new UserInputValidator();
    }

    @Test
    public void isValidFullName_validFullName_trueReturned() {
        boolean result = SUT.isValidFullName("validFullName");
        assertThat(result, is(true));
    }

    @Test
    public void isValidFullName_invalidFullName_falseReturned() {
        boolean result = SUT.isValidFullName("");
        assertThat(result, is(false));
    }


    @Test
    public void isValidUserName_validFullName_trueReturned() {
        boolean result = SUT.isValidUsername("validFullName");
        assertThat(result, is(true));
    }

    @Test
    public void isValidUserName_invalidFullName_falseReturned() {
        boolean result = SUT.isValidUsername("");
        assertThat(result, is(false));
    }
}