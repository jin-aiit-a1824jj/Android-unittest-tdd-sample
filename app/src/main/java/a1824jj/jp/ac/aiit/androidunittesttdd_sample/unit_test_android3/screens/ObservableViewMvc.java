package a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.screens;

public interface ObservableViewMvc<ListenerType> extends ViewMvc {

    void registerListener(ListenerType listener);

    void unregisterListener(ListenerType listener);
}
