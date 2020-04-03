package a1824jj.jp.ac.aiit.androidunittesttdd_sample;

import org.junit.Before;
import org.junit.Test;

import a1824jj.jp.ac.aiit.androidunittesttdd_sample.IntervalsOverlapDetector.Interval;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class IntervalsOverlapDetectorTest {

    IntervalsOverlapDetector SUT;

    @Before
    public void setup(){
        SUT = new IntervalsOverlapDetector();
    }

    @Test
    public void isOverlap_interval1BeforeInterval2_falseReturned() throws Exception {
        Interval interval1 = new Interval(-1, 15);
        Interval interval2 = new Interval(8, 12);

        boolean result = SUT.isOverlap(interval1, interval2);
        assertThat(result, is(false));
    }

    @Test
    public void isOverlap_intervalOverInterval2OnStart_trueReturned() throws Exception {
        Interval interval1 = new Interval(-1, 15);
        Interval interval2 = new Interval(3, 15);

        boolean result = SUT.isOverlap(interval1, interval2);
        assertThat(result, is(true));
    }

    @Test
    public void isOverlap_interval1ContainsInterval2_trueReturned() throws Exception {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(0, 3);

        boolean result = SUT.isOverlap(interval1, interval2);
        assertThat(result, is(true));
    }

    @Test
    public void isOverlap_interval1OverlapsInterval2OnEnd_trueReturned() throws Exception {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(-4, 4);

        boolean result = SUT.isOverlap(interval1, interval2);
        assertThat(result, is(true));
    }

    @Test
    public void isOverlap_interval1AfterInterval2_falseReturned() throws Exception{
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(-10, -3);

        boolean result = SUT.isOverlap(interval1, interval2);
        assertThat(result, is(false));
    }

    @Test
    public void isOverlap_interval1BeforeAdjacentInterval2_falseReturned() throws Exception{
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(5, 8);

        boolean result = SUT.isOverlap(interval1, interval2);
        assertThat(result, is(false));
    }

    @Test
    public void isOverlap_interval1AfterAdjacentInterval2_falseReturned() throws Exception{
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(-3, -1);

        boolean result = SUT.isOverlap(interval1, interval2);
        assertThat(result, is(false));
    }
}