package a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.screens;

public interface BackPressDispatcher {
    void registerListener(BackPressedListener listener);
    void unregisterListener(BackPressedListener listener);
}
