package cache.ehcahe;

import com.aojiaoo.utils.CacheUntil;
import org.junit.Test;

public class TestEhcache {

    @Test
    public void test1() {
        CacheUntil.load("classpath:ehcache.xml");
        System.out.println(CacheUntil.getCache("aaa1"));
    }


}
