package cn.com.zenmaster.shiro;

import cn.com.zenmaster.entity.po.SysPermission;
import cn.com.zenmaster.entity.po.SysRole;
import cn.com.zenmaster.entity.po.User;
import cn.com.zenmaster.entity.po.UserRole;
import cn.com.zenmaster.service.SysPermissionService;
import cn.com.zenmaster.service.SysRoleService;
import cn.com.zenmaster.service.UserRoleService;
import cn.com.zenmaster.service.UserService;
import cn.com.zenmaster.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author tianyu
 */
@Component
@Slf4j
public class CustomShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysPermissionService sysPermissionService;
    @Resource
    private UserRoleService userRoleService;

    /**
     * 权限信息，包括角色以及权限
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //如果身份认证的时候没有传入User对象，这里只能取到userName
        //也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
        User user  = (User)principals.getPrimaryPrincipal();
        User userFindByUsername = userService.findByUserName(user.getUsername());
        if(userFindByUsername == null) {
            return null;
        }
        UserRole queryParam = new UserRole();
        queryParam.setUserId(user.getId());

        List<UserRole> userRoleList = userRoleService.findByExample(queryParam);
        for(UserRole userRole : userRoleList) {
            SysRole sysRoleQueryParam = new SysRole();
            sysRoleQueryParam.setId(userRole.getRoleId());
            List<SysRole> roleList = sysRoleService.findByExample(sysRoleQueryParam);
            for (SysRole role : roleList) {
                authorizationInfo.addRole(role.getRole());
                SysPermission sysPermissionQueryParam = new SysPermission();
                sysPermissionQueryParam.setRoleId(role.getId());
                List<SysPermission> sysPermissionList = sysPermissionService.findByExample(sysPermissionQueryParam);
                for (SysPermission p : sysPermissionList) {
                    authorizationInfo.addStringPermission(p.getPermission());
                }
            }
        }
        return authorizationInfo;
    }

    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        log.info("username:{]", username);
        if(StringUtils.isEmpty(username)) {
            throw new UnknownAccountException("账户不存在");
        }
        log.info("credential:{}", token.getCredentials());
        String password = new String((char[]) token.getCredentials());
        //通过username从数据库中查找 User对象.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = userService.findByUserName(username);
        log.info("----->>user="+ JSON.toJSONString(user));
        if(user == null){
            throw new UnknownAccountException("账户不存在");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
                user,
                //密码
                user.getPassword(),
                //salt
                ByteSource.Util.bytes(user.getSalt()),
                //realm name
                getName()
        );
        return authenticationInfo;
    }

}
