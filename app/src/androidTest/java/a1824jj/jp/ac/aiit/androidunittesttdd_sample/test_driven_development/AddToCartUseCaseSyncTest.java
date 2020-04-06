package a1824jj.jp.ac.aiit.androidunittesttdd_sample.test_driven_development;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class AddToCartUseCaseSyncTest {

    private static final String OFFER_ID = "offerId";
    private static final int AMOUNT = 4;

    // region constants

    // endregion constants

    // region helper fields
    @Mock AddToCartHttpEndpointSync mAddToCartHttpEndpointsSyncMock;
    // endregion helper fields

    AddToCartUseCaseSync SUT;

    @Before
    public void setup() throws Exception {
        SUT = new AddToCartUseCaseSync(mAddToCartHttpEndpointsSyncMock);
        success();
    }

    @Test
    public void addToCartSync_currentParametersPassedToEndpoint() throws Exception {
        // Arrange
        ArgumentCaptor<CartItemScheme> ac = ArgumentCaptor.forClass(CartItemScheme.class);
        // Act
        SUT.addToCartSync(OFFER_ID, AMOUNT);
        // Assert
        verify(mAddToCartHttpEndpointsSyncMock).addToCartSync(ac.capture());
        assertThat(ac.getValue().getOfferId(), is(OFFER_ID));
        assertThat(ac.getValue().getAmount(), is(AMOUNT));
    }

    @Test
    public void addToCartSync_success_successReturned() throws Exception {
        // Arrange
        // Act
        AddToCartUseCaseSync.UseCaseResult result = SUT.addToCartSync(OFFER_ID, AMOUNT);
        // Assert
        assertThat(result, is(AddToCartUseCaseSync.UseCaseResult.SUCCESS));
    }

    @Test
    public void addToCartSync_authError_failureReturned() throws Exception {
        // Arrange
        authError();
        // Act
        AddToCartUseCaseSync.UseCaseResult result = SUT.addToCartSync(OFFER_ID, AMOUNT);
        // Assert
        assertThat(result, is(AddToCartUseCaseSync.UseCaseResult.FAILURE));
    }

    @Test
    public void addToCartSync_generalError_failureReturned() throws Exception {
        // Arrange
        generalError();
        // Act
        AddToCartUseCaseSync.UseCaseResult result = SUT.addToCartSync(OFFER_ID, AMOUNT);
        // Assert
        assertThat(result, is(AddToCartUseCaseSync.UseCaseResult.FAILURE));
    }

    @Test
    public void addToCartSync_networkError_networkErrorReturned() throws Exception {
        // Arrange
        networkError();
        // Act
        AddToCartUseCaseSync.UseCaseResult result = SUT.addToCartSync(OFFER_ID, AMOUNT);
        // Assert
        assertThat(result, is(AddToCartUseCaseSync.UseCaseResult.NETWORK_ERROR));
    }


    // region helper methods

    private void authError() throws Exception {
        when(mAddToCartHttpEndpointsSyncMock.addToCartSync(any(CartItemScheme.class)))
                .thenReturn(AddToCartHttpEndpointSync.EndpointResult.AUTH_ERROR);
    }


    private void generalError() throws Exception {
        when(mAddToCartHttpEndpointsSyncMock.addToCartSync(any(CartItemScheme.class)))
                .thenReturn(AddToCartHttpEndpointSync.EndpointResult.GENERAL_ERROR);
    }


    private void networkError() throws Exception {
        when(mAddToCartHttpEndpointsSyncMock.addToCartSync(any(CartItemScheme.class)))
                .thenThrow(new NetworkErrorException());
    }


    private void success() throws Exception {
        when(mAddToCartHttpEndpointsSyncMock.addToCartSync(any(CartItemScheme.class)))
                .thenReturn(AddToCartHttpEndpointSync.EndpointResult.SUCCESS);
    }

    // endregion helper methods

    // region helper classes

    // endregion helper classes

}