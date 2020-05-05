package cn.com.zenmaster.shiro;

import cn.com.zenmaster.utils.JedisUtils;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import redis.clients.jedis.Jedis;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * 使用redis来做缓存
 * @param <K>
 * @param <V>
 */
@Slf4j
public class JedisCache<K, V> implements Cache<K, V> {

    private String cacheKeyName;
    
    public JedisCache(String cacheName) {
        this.cacheKeyName = cacheName;
    }

    @Override
    public V get(K key) throws CacheException {
        if (key == null){
            return null;
        }

        V v = null;
        
        V value = null;
        Jedis jedis = null;
        try {
            jedis = JedisUtils.getResource();
            value = (V)JedisUtils.toObject(jedis.hget(JedisUtils.getBytesKey(cacheKeyName), JedisUtils.getBytesKey(key)));
        } catch (Exception e) {
            log.error("get cache [{} ]key: [{}] exception:[{}]", cacheKeyName, key,  e);
        } finally {
            JedisUtils.returnResource(jedis);
        }
        return value;
    }

    @Override
    public V put(K key, V value) throws CacheException {
        if (key == null){
            return null;
        }

        Jedis jedis = null;
        try {
            jedis = JedisUtils.getResource();
            jedis.hset(JedisUtils.getBytesKey(cacheKeyName), JedisUtils.getBytesKey(key), JedisUtils.toBytes(value));
            log.debug("put {} {} = {}", cacheKeyName, key, value);
        } catch (Exception e) {
            log.error("put cache {} key {} exception:{}", cacheKeyName, key, e);
        } finally {
            JedisUtils.returnResource(jedis);
        }
        return value;
    }

    @Override
    public V remove(K key) throws CacheException {
        V value = null;
        Jedis jedis = null;
        try {
            jedis = JedisUtils.getResource();
            value = (V)JedisUtils.toObject(jedis.hget(JedisUtils.getBytesKey(cacheKeyName), JedisUtils.getBytesKey(key)));
            jedis.hdel(JedisUtils.getBytesKey(cacheKeyName), JedisUtils.getBytesKey(key));
            log.debug("remove cache {} key {}", cacheKeyName, key);
        } catch (Exception e) {
            log.warn("remove cache {} key {} exception:{}", cacheKeyName, key, e);
        } finally {
            JedisUtils.returnResource(jedis);
        }
        return value;
    }

    @Override
    public void clear() throws CacheException {
        Jedis jedis = null;
        try {
            jedis = JedisUtils.getResource();
            jedis.hdel(JedisUtils.getBytesKey(cacheKeyName));
            log.debug("clear {}", cacheKeyName);
        } catch (Exception e) {
            log.error("clear {}", cacheKeyName, e);
        } finally {
            JedisUtils.returnResource(jedis);
        }
    }

    @Override
    public int size() {
        int size = 0;
        Jedis jedis = null;
        try {
            jedis = JedisUtils.getResource();
            size = jedis.hlen(JedisUtils.getBytesKey(cacheKeyName)).intValue();
            log.debug("size {} {} ", cacheKeyName, size);
            return size;
        } catch (Exception e) {
            log.error("clear {}",  cacheKeyName, e);
        } finally {
            JedisUtils.returnResource(jedis);
        }
        return size;
    }

    @Override
    public Set<K> keys() {
        Set<K> keys = Sets.newHashSet();
        Jedis jedis = null;
        try {
            jedis = JedisUtils.getResource();
            Set<byte[]> set = jedis.hkeys(JedisUtils.getBytesKey(cacheKeyName));
            for(byte[] key : set){
                Object obj = (K)JedisUtils.getObjectKey(key);
                if (obj != null){
                    keys.add((K) obj);
                }
            }
            log.debug("keys {} {} ", cacheKeyName, keys);
            return keys;
        } catch (Exception e) {
            log.error("keys {}", cacheKeyName, e);
        } finally {
            JedisUtils.returnResource(jedis);
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        Collection<V> vals = Collections.emptyList();;
        Jedis jedis = null;
        try {
            jedis = JedisUtils.getResource();
            Collection<byte[]> col = jedis.hvals(JedisUtils.getBytesKey(cacheKeyName));
            for(byte[] val : col){
                Object obj = JedisUtils.toObject(val);
                if (obj != null){
                    vals.add((V) obj);
                }
            }
            log.debug("values {} {} ", cacheKeyName, vals);
            return vals;
        } catch (Exception e) {
            log.error("values {}",  cacheKeyName, e);
        } finally {
            JedisUtils.returnResource(jedis);
        }
        return vals;
    }
}
