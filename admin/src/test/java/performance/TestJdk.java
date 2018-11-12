package performance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestJdk {

    private long startTime;

    @Test
    public void test1() {

    }

    @Before
    public void before() {
        startTime = System.currentTimeMillis();
    }

    @After
    public void after() {
        System.out.println("总共花了:" + (System.currentTimeMillis() - startTime) + "毫秒");
    }
}
