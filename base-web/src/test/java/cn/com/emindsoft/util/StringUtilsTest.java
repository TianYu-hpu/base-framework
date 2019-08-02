package cn.com.emindsoft.util;

import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void generateSaltTest() {
        System.out.println(StringUtils.generateSalt());
    }

    @Test
    public void testPassword() {
        String password = "password";
        String salt = StringUtils.generateSalt();

    }

}
