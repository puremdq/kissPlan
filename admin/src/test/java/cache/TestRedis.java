package cache;

import com.aojiaoo.modules.sys.entity.User;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;


public class TestRedis {

    @Test
    public void test1() {
        User user = new User();
        user.setUsername("test");
        Jedis jedis = new Jedis("192.168.80.21");
        byte[] suser = SerializationUtils.serialize(user);
        User usera = SerializationUtils.deserialize(suser);
        System.out.println(user);
        jedis.auth("luckluck");
//        System.out.println("连接成功");
        //查看服务是否运行
        jedis.set("user".getBytes(), suser);
        System.out.println("服务正在运行: " + SerializationUtils.deserialize(jedis.get("user".getBytes())));
        System.out.println( SerializationUtils.deserialize(jedis.get("user".getBytes()))==SerializationUtils.deserialize(jedis.get("user".getBytes())));

    }


}