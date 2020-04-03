package a1824jj.jp.ac.aiit.androidunittesttdd_sample.test_doubles_fundamentals3;

public class FitnessTracker {

    public static final int RUN_STEPS_FACTOR = 2;

    public void step() {
        Counter.getInstance().add();
    }

    public void runStep() {
        Counter.getInstance().add(RUN_STEPS_FACTOR);
    }

    public int getTotalSteps() {
        return Counter.getInstance().getTotal();
    }
}
