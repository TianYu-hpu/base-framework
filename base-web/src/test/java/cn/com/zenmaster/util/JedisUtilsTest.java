package cn.com.zenmaster.util;

import cn.com.zenmaster.BaseWebApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

public class JedisUtilsTest extends BaseWebApplicationTests {

    @Autowired
    private JedisUtils jedisUtils;

    @Test
    public void getValueTest() {
        Jedis client = jedisUtils.getResource();
    }

}
