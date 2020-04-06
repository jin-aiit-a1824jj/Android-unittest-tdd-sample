package a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.screens;

import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.common.CustomApplication;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.common.dependencyinjection.ControllerCompositionRoot;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private ControllerCompositionRoot mControllerCompositionRoot;

    protected ControllerCompositionRoot getCompositionRoot() {
        if (mControllerCompositionRoot == null) {
            mControllerCompositionRoot = new ControllerCompositionRoot(
                    ((CustomApplication) getApplication()).getCompositionRoot(),
                    this
            );
        }
        return mControllerCompositionRoot;
    }

}
