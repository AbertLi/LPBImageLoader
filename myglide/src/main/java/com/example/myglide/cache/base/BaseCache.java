package com.example.myglide.cache.base;

public interface BaseCache<T, M> {

    /**
     * 存入
     *
     * @param key
     * @param value
     */
    void put(T key, M value);

    /**
     * 获取
     *
     * @return
     */
    M get();


    /**
     * 清除缓存
     *
     * @param key
     */
    void remove(T key);
}
