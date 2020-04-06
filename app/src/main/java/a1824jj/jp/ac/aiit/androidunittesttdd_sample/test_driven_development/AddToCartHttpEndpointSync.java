package a1824jj.jp.ac.aiit.androidunittesttdd_sample.test_driven_development;

public interface AddToCartHttpEndpointSync {

    EndpointResult addToCartSync(CartItemScheme cartItemScheme) throws NetworkErrorException;

    enum EndpointResult {
        SUCCESS,
        AUTH_ERROR,
        GENERAL_ERROR
    }

}
