package cn.com.emindsoft.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

public class JedisCacheManager implements CacheManager {

    private String cacheKeyPrefix = "shiro_cache_";

    public String getCacheKeyPrefix() {
        return cacheKeyPrefix;
    }

    public void setCacheKeyPrefix(String cacheKeyPrefix) {
        this.cacheKeyPrefix = cacheKeyPrefix;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new JedisCache<K, V>(cacheKeyPrefix + name);
    }
}
