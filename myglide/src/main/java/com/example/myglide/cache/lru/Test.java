package com.example.myglide.cache.lru;

public class Test {
    public static LruCache<String,String> t(){
        LruCache<String,String> cache = new LruCache<String,String>(10000){
            @Override
            protected void entryRemoved(boolean evicted, String key, String oldValue, String newValue) {
                //第2,3个参数的意思，当内存满了的时候清除的key和value值。
                //或者曾经存了一个同样key的值进去。这个时候回被覆盖掉的key和value
                super.entryRemoved(evicted, key, oldValue, newValue);
            }

            @Override
            protected String create(String key) {
                return super.create(key);
            }

            @Override
            protected int sizeOf(String key, String value) {
                return super.sizeOf(key,value);
            }
        };
        return cache;
    }


    public static void main(String[] args) {
        t().put("put","value");
    }
}
