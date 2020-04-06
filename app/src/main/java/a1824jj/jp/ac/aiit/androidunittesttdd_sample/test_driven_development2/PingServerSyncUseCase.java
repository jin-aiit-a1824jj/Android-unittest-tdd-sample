package a1824jj.jp.ac.aiit.androidunittesttdd_sample.test_driven_development2;

public class PingServerSyncUseCase {

    public enum UseCaseResult {
        FAILURE,
        SUCCESS
    }

    private final PingServerHttpEndpointSync mPingServerHttpEndpointSync;

    public PingServerSyncUseCase(PingServerHttpEndpointSync pingServerHttpEndpointSync) {
        mPingServerHttpEndpointSync = pingServerHttpEndpointSync;
    }

    public UseCaseResult pingServerSync() {
        PingServerHttpEndpointSync.EndpointResult result = mPingServerHttpEndpointSync.pingServerSync();
        switch (result) {
            case GENERAL_ERROR:
            case NETWORK_ERROR:
                return UseCaseResult.FAILURE;
            case SUCCESS:
                return UseCaseResult.SUCCESS;
            default:
                throw new RuntimeException("invalid result: " + result);
        }
    }

}
