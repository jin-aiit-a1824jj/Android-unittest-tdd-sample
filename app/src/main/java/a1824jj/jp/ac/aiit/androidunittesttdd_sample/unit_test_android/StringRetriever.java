package a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android;

import android.content.Context;

public class StringRetriever {
    private final Context mContext;

    public StringRetriever(Context context) {
        mContext = context;
    }

    public String getString(int id) {
        return mContext.getString(id);
    }
}
