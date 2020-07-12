package com.example.myglide.cache.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.example.myglide.cache.base.BaseCache;
import com.example.myglide.cache.util.SizeOf;
import com.example.myglide.uril.LogUtil;

public class MemoryLruChache implements BaseCache {
    private static String TAG = "MemoryLruChache";
    private final int MAX_SIZ = 20 * 1024 * 1024;//LruCache最大存10M的图片
    private LruCache lruCache;

    private static class Holder {
        private static MemoryLruChache memoryLruChache = new MemoryLruChache();
    }

    public static MemoryLruChache getInstance() {
        return Holder.memoryLruChache;
    }

    private MemoryLruChache() {
        int maxSize = (int) ((Runtime.getRuntime().maxMemory()) / 16);
        if (maxSize <= 0) {
            maxSize = MAX_SIZ;
        }
        lruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return (int) SizeOf.getObjSize(value);
            }
        };
    }

    @Override
    public void put(String key, Bitmap value) {
        if (value != null) {
            lruCache.put(key, value);
        }
    }

    @Override
    public Bitmap get(String key) {
        LogUtil.e(TAG, "lruCache.size()=" + lruCache.size());
        LogUtil.e(TAG, "lruCache.maxSize()=" + lruCache.maxSize());
        return (Bitmap) lruCache.get(key);
    }

    @Override
    public void remove(String key) {
        lruCache.remove(key);
    }
}
