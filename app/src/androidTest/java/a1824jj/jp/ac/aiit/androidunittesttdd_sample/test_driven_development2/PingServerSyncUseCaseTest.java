package a1824jj.jp.ac.aiit.androidunittesttdd_sample.test_driven_development2;

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
public class PingServerSyncUseCaseTest {

    // region constants

    // endregion constants

    // region helper fields
    @Mock PingServerHttpEndpointSync mPingServerHttpEndpointSync;
    // endregion helper fields

    PingServerSyncUseCase SUT;

    @Before
    public void setup() throws Exception {
        SUT = new PingServerSyncUseCase(mPingServerHttpEndpointSync);
        success();
    }

    @Test
    public void pingServerSync_success_successReturned() throws Exception {
        // Arrange
        // Act
        PingServerSyncUseCase.UseCaseResult result = SUT.pingServerSync();
        // Assert
        assertThat(result, is(PingServerSyncUseCase.UseCaseResult.SUCCESS));
    }

    @Test
    public void pingServerSync_generalError_failureReturned() throws Exception {
        // Arrange
        generalError();
        // Act
        PingServerSyncUseCase.UseCaseResult result = SUT.pingServerSync();
        // Assert
        assertThat(result, is(PingServerSyncUseCase.UseCaseResult.FAILURE));
    }

    @Test
    public void pingServerSync_networkError_failureReturned() throws Exception {
        // Arrange
        networkError();
        // Act
        PingServerSyncUseCase.UseCaseResult result = SUT.pingServerSync();
        // Assert
        assertThat(result, is(PingServerSyncUseCase.UseCaseResult.FAILURE));
    }

    // region helper methods
    private void success() {
        when(mPingServerHttpEndpointSync.pingServerSync())
                .thenReturn(PingServerHttpEndpointSync.EndpointResult.SUCCESS);
    }


    private void generalError() {
        when(mPingServerHttpEndpointSync.pingServerSync())
                .thenReturn(PingServerHttpEndpointSync.EndpointResult.GENERAL_ERROR);
    }


    private void networkError() {
        when(mPingServerHttpEndpointSync.pingServerSync())
                .thenReturn(PingServerHttpEndpointSync.EndpointResult.NETWORK_ERROR);
    }

    // endregion helper methods

    // region helper classes

    // endregion helper classes

}