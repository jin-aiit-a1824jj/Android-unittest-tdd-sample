package a1824jj.jp.ac.aiit.androidunittesttdd_sample.test_driven_development3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class FetchCartItemsUseCaseTest {


    // region constants
    private static final int LIMIT = 10;
    private static final int PRICE = 5;
    private static final String DESCRIPTION = "description";
    private static final String TITLE = "title";
    private static final String ID = "id";

    // endregion constants

    // region helper fields
    @Mock GetCartItemsHttpEndpoint mGetCartItemsHttpEndpoint;
    @Mock FetchCartItemsUseCase.Listener mListener1;
    @Mock FetchCartItemsUseCase.Listener mListener2;

    @Captor ArgumentCaptor<List<CartItem>> mAcListCartItem;
    // endregion helper fields

    FetchCartItemsUseCase SUT;

    @Before
    public void setup() throws Exception {
        SUT = new FetchCartItemsUseCase(mGetCartItemsHttpEndpoint);
        success();
    }



    @Test
    public void fetchCartItems_correctLimitPassedToEndpoint() throws Exception {
        // Arrange
        ArgumentCaptor<Integer> acInt = ArgumentCaptor.forClass(Integer.class);
        // Act
        SUT.fetchCartItemsAndNotify(LIMIT);
        // Assert
        verify(mGetCartItemsHttpEndpoint).getCartItems(acInt.capture(), any(GetCartItemsHttpEndpoint.Callback.class));
        assertThat(acInt.getValue(), is(LIMIT));
    }

    @Test
    public void fetchCartItems_success_observersNotifiedWithCorrectData() throws Exception {
        // Arrange
        // Act
        SUT.registerListener(mListener1);
        SUT.registerListener(mListener2);
        SUT.fetchCartItemsAndNotify(LIMIT);
        // Assert
        verify(mListener1).onCartItemsFetched(mAcListCartItem.capture());
        verify(mListener2).onCartItemsFetched(mAcListCartItem.capture());

        List<List<CartItem>> captures = mAcListCartItem.getAllValues();
        List<CartItem> capture1 = captures.get(0);
        List<CartItem> capture2 = captures.get(1);

        assertThat(capture1, is(getCartItem()));
        assertThat(capture2, is(getCartItem()));
    }

    @Test
    public void fetchCartItems_success_unsubscribedObserversNotNotified() throws Exception {
        // Arrange
        // Act
        SUT.registerListener(mListener1);
        SUT.registerListener(mListener2);
        SUT.unregisterListener(mListener2);
        SUT.fetchCartItemsAndNotify(LIMIT);
        // Assert
        verify(mListener1).onCartItemsFetched(mAcListCartItem.capture());
        verifyZeroInteractions(mListener2);
    }

    @Test
    public void fetchCartItems_generalError_observersNotNotifiedOfError() throws Exception {
        // Arrange
        generalError();
        // Act
        SUT.registerListener(mListener1);
        SUT.registerListener(mListener2);
        SUT.fetchCartItemsAndNotify(LIMIT);
        // Assert
        verify(mListener1).onFetchCartItemsFailed();
        verify(mListener2).onFetchCartItemsFailed();
    }

    @Test
    public void fetchCartItems_networkError_observersNotNotifiedOfError() throws Exception {
        // Arrange
        networkError();
        // Act
        SUT.registerListener(mListener1);
        SUT.registerListener(mListener2);
        SUT.fetchCartItemsAndNotify(LIMIT);
        // Assert
        verify(mListener1).onFetchCartItemsFailed();
        verify(mListener2).onFetchCartItemsFailed();
    }

    // region helper methods
    private void success() {
        doAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                GetCartItemsHttpEndpoint.Callback callback = (GetCartItemsHttpEndpoint.Callback) args[1];
                callback.onGetCartItemsSucceeded(getCartItemsSchemes());
                return null;
            }

        }).when(mGetCartItemsHttpEndpoint).getCartItems(anyInt(), any(GetCartItemsHttpEndpoint.Callback.class));
    }

    private List<CartItemSchema> getCartItemsSchemes() {
        List<CartItemSchema> schemas = new ArrayList<>();
        schemas.add(new CartItemSchema(ID, TITLE, DESCRIPTION, PRICE));
        return schemas;
    }

    private List<CartItem> getCartItem() {
        List<CartItem> cartItem = new ArrayList<>();
        cartItem.add(new CartItem(ID, TITLE, DESCRIPTION, PRICE));
        return cartItem;
    }


    private void generalError() {
        doAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                GetCartItemsHttpEndpoint.Callback callback = (GetCartItemsHttpEndpoint.Callback) args[1];
                callback.onGetCartItemsFailed(GetCartItemsHttpEndpoint.FailReason.GENERAL_ERROR);
                return null;
            }

        }).when(mGetCartItemsHttpEndpoint).getCartItems(anyInt(), any(GetCartItemsHttpEndpoint.Callback.class));
    }



    private void networkError() {
        doAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                GetCartItemsHttpEndpoint.Callback callback = (GetCartItemsHttpEndpoint.Callback) args[1];
                callback.onGetCartItemsFailed(GetCartItemsHttpEndpoint.FailReason.NETWORK_ERROR);
                return null;
            }

        }).when(mGetCartItemsHttpEndpoint).getCartItems(anyInt(), any(GetCartItemsHttpEndpoint.Callback.class));
    }

    // endregion helper methods

    // region helper classes

    // endregion helper classes

}