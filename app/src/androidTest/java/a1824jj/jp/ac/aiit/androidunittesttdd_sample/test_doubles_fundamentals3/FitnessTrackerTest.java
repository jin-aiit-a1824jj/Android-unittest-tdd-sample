package a1824jj.jp.ac.aiit.androidunittesttdd_sample.test_doubles_fundamentals3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FitnessTrackerTest {

    FitnessTracker SUT;

    @Before
    public void setup() {
        SUT = new FitnessTracker();
    }

    @Test
    public void step_totalIncremented() {
        SUT.step();
        Assert.assertThat(SUT.getTotalSteps(), is(1));
    }

    @Test
    public void step_totalIncrementedByCorrectRation() {
        SUT.runStep();
        Assert.assertThat(SUT.getTotalSteps(), is(2));
    }

}