package cn.com.zenmaster.enums;

/**
 * @author tianyu
 * @param <E>
 * @param <T>
 */
public interface BaseCodeEnum<E extends Enum<?>, T> {
    /**
     * 该接口只有一个返回编码的方法，返回值将被存入数据库。
     *
     * @return
     */
    T getCode();

    /**
     * 获取描述信息
     *
     * @return
     */
    String getDesc();

}
