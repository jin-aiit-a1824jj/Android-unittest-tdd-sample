package a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.screens;

import android.content.Context;
import android.widget.Toast;

import a1824jj.jp.ac.aiit.androidunittesttdd_sample.R;

public class ToastsHelper {

    private final Context mContext;

    public ToastsHelper(Context context) {
        mContext = context;
    }

    public void showUseCaseError() {
        Toast.makeText(mContext, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show();
    }

}
