package a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android2;

import android.app.Activity;

public class MyActivity extends Activity {

    private int mCount;

    @Override
    protected void onStart() {
        super.onStart();
        mCount++;
    }

    public int getCount() {
        return mCount;
    }
}
