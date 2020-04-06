package a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android;

import android.content.Context;

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
public class StringRetrieverTest {

    // region constants
    final static int ID = 10;
    final static String STRING = "string";
    // endregion constants

    // region helper fields
    @Mock Context mContext;
    // endregion helper fields

    StringRetriever SUT;

    @Before
    public void setup() throws Exception {
        SUT = new StringRetriever(mContext);

    }

    @Test
    public void getString_correctParameterPassedToContext() throws Exception {
        // Arrange
        // Act
        SUT.getString(ID);
        // Assert
        verify(mContext).getString(ID);
    }

    @Test
    public void getString_correctResultReturned() throws Exception {
        // Arrange
        when(mContext.getString(ID)).thenReturn(STRING);
        // Act
        String result = SUT.getString(ID);
        // Assert
        assertThat(result, is(STRING));
    }


    // region helper methods

    // endregion helper methods

    // region helper classes

    // endregion helper classes

}