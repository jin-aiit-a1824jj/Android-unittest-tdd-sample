package a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.screens;

import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.common.CustomApplication;
import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.common.dependencyinjection.ControllerCompositionRoot;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    private ControllerCompositionRoot mControllerCompositionRoot;

    protected ControllerCompositionRoot getCompositionRoot() {
        if (mControllerCompositionRoot == null) {
            mControllerCompositionRoot = new ControllerCompositionRoot(
                    ((CustomApplication) requireActivity().getApplication()).getCompositionRoot(),
                    requireActivity()
            );
        }
        return mControllerCompositionRoot;
    }
}
