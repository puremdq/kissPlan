package cache.ehcahe;

import com.aojiaoo.utils.CacheUtils;
import org.junit.Test;

public class TestEhcache {

    @Test
    public void test1() {
        CacheUtils.load("classpath:ehcache.xml");
        System.out.println(CacheUtils.getCache("aaa1"));
    }


}
