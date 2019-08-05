package cn.com.emindsoft.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tianyu
 */
@Slf4j
public class CustomHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Cache<String, AtomicInteger> passwordRetryCache;

    public CustomHashedCredentialsMatcher(CacheManager cacheManager) {
        this.passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }


    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if(retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        /**
         * 当用户连续输入密码错误五次以上禁止用户登录一段时间
         */
        if(retryCount.incrementAndGet() > 5) {
            log.error("用户：{}登录失败次数超过5次", username);
            throw  new ExcessiveAttemptsException();
        }
        boolean match =  super.doCredentialsMatch(token, info);
        if(match) {
            passwordRetryCache.remove(username);
        }
        return match;
    }
}
