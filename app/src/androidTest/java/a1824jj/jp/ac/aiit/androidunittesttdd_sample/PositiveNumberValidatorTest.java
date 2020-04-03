package a1824jj.jp.ac.aiit.androidunittesttdd_sample;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PositiveNumberValidatorTest {
    PositiveNumberValidator SUT;

    @Before
    public void setup(){
        SUT = new PositiveNumberValidator();
    }

    @Test
    public void test1(){
        boolean result = SUT.isPositive(-1);
        Assert.assertThat(result, is(false));
    }

    @Test
    public void test2(){
        boolean result = SUT.isPositive(0);
        Assert.assertThat(result, is(false));
    }

    @Test
    public void test3(){
        boolean result = SUT.isPositive(1);
        Assert.assertThat(result, is(true));
    }
}