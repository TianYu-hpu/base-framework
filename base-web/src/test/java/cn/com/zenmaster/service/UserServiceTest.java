package cn.com.zenmaster.service;

import cn.com.zenmaster.BaseWebApplicationTests;
import cn.com.zenmaster.entity.po.User;
import cn.com.zenmaster.enums.ActiveFlagEnum;
import cn.com.zenmaster.enums.DelFlagEnum;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class UserServiceTest extends BaseWebApplicationTests {

    @Autowired
    private UserService userService;

    /**
     * 测试保存
     */
    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("root");
        user.setPassword("zenmaster@123");
        user.setEmail("base_framework@zenmaster.com.cn");
        user.setPhone("13552450292");
        user.setActiveFlag(ActiveFlagEnum.ACTIVE.getCode());
        user.setDelFlag(DelFlagEnum.N.getCode());
        userService.save(user);

        List<User> userList = userService.findByExample(user);
        for(User item : userList) {
            Assert.assertEquals(item.getUsername(), user.getUsername());
            log.info("userFindByExample:{}", JSON.toJSONString(item));
        }
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setSalt("salt");
        user.setEmail("email");
        user.setPhone("phone");
        user.setActiveFlag(ActiveFlagEnum.ACTIVE.getCode());
        user.setDelFlag(DelFlagEnum.N.getCode());

        List<User> userList = userService.findByExample(user);
        for (User item : userList) {
            item.setUsername("tianyu");
            userService.update(item);
            break;
        }

    }

    /**
     * 测试查询
     */
    @Test
    public void testFindByExample() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setSalt("salt");
        user.setEmail("email");
        user.setPhone("phone");
        user.setActiveFlag(ActiveFlagEnum.ACTIVE.getCode());
        user.setDelFlag(DelFlagEnum.N.getCode());

        List<User> userList = userService.findByExample(user);
        for(User item : userList) {
            Assert.assertEquals(item.getUsername(), user.getUsername());
        }
    }

}
