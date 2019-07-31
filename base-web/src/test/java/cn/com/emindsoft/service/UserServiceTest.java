package cn.com.emindsoft.service;

import cn.com.emindsoft.BaseWebApplicationTests;
import cn.com.emindsoft.entity.po.User;
import cn.com.emindsoft.enums.ActiveFlagEnum;
import cn.com.emindsoft.enums.DelFlagEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceTest extends BaseWebApplicationTests {

    @Autowired
    private UserService userService;

    /**
     * 测试保存
     */
    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setSalt("salt");
        user.setEmail("email");
        user.setPhone("phone");
        user.setActiveFlag(ActiveFlagEnum.ACTIVE.getCode());
        user.setDelFlag(DelFlagEnum.N.getCode());
        userService.save(user);

        List<User> userList = userService.findByExample(user);
        for(User item : userList) {
            Assert.assertEquals(item.getUsername(), user.getUsername());
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
