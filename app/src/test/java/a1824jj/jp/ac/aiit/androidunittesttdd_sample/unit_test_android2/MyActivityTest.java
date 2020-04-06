package a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MyActivityTest {

    // region constants

    // endregion constants

    // region helper fields

    // endregion helper fields

    MyActivity SUT;

    @Before
    public void setup() throws Exception {
        SUT = new MyActivity();
    }

    @Test
    public void onStart_incrementsCountByOne() throws Exception {
        // Arrange
        // Act
        SUT.onStart();
        int result = SUT.getCount();
        // Assert
        assertThat(result, is(1));
    }

    // region helper methods

    // endregion helper methods

    // region helper classes

    // endregion helper classes

}