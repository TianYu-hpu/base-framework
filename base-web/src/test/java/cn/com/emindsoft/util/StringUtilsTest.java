package cn.com.emindsoft.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class StringUtilsTest {

    @Test
    public void generateSaltTest() {
        log.info(StringUtils.generateSalt());
    }

    @Test
    public void testPassword() {
        String password = "password";
        String salt = StringUtils.generateSalt();

    }

}
