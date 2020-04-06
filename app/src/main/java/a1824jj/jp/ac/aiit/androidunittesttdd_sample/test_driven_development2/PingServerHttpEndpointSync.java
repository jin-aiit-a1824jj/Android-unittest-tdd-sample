package a1824jj.jp.ac.aiit.androidunittesttdd_sample.test_driven_development2;

public interface PingServerHttpEndpointSync {

    enum EndpointResult {
        SUCCESS,
        GENERAL_ERROR,
        NETWORK_ERROR
    }

    EndpointResult pingServerSync();

}
