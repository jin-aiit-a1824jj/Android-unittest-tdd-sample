package a1824jj.jp.ac.aiit.androidunittesttdd_sample.test_doubles_fundamentals3;

public class Counter {

    private static Counter sInstance;

    private int mTotalCount;

    private Counter() {}

    public static Counter getInstance() {
        if (sInstance == null) {
            sInstance = new Counter();
        }
        return sInstance;
    }

    public void add() {
        mTotalCount++;
    }

    public void add(int count) {
        mTotalCount += count;
    }

    public int getTotal() {
        return mTotalCount;
    }
}
