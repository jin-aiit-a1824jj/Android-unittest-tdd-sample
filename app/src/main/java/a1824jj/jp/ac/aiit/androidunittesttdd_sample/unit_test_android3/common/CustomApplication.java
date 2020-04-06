package a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.common;

import android.app.Application;

import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.common.dependencyinjection.CompositionRoot;

public class CustomApplication extends Application {

    private CompositionRoot mCompositionRoot;

    @Override
    public void onCreate() {
        super.onCreate();
        mCompositionRoot = new CompositionRoot();
    }

    public CompositionRoot getCompositionRoot() {
        return mCompositionRoot;
    }
}
