package cn.com.emindsoft.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ConfigConstantTest {

    @Autowired
    private ConfigConstant configConstant;

    @Test
    public void getConfig() {
        System.out.println(configConstant.getWebStaticFile());
    }
}
