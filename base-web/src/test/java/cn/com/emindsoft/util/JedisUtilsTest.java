package cn.com.emindsoft.util;

import cn.com.emindsoft.BaseWebApplicationTests;
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
