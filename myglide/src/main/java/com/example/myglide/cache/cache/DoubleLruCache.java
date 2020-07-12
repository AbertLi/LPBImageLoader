package com.example.myglide.cache.cache;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.myglide.cache.base.BaseCache;

public class DoubleLruCache implements BaseCache {
    private DiskCache diskCache;
    private MemoryLruChache memoryLruChache;

    public static DoubleLruCache getInstance(Context context) {
        return new DoubleLruCache(context);
    }

    private DoubleLruCache(Context context) {
        diskCache = DiskCache.getInstance(context);
        memoryLruChache = MemoryLruChache.getInstance();
    }

    @Override
    public void put(String key, Bitmap value) {
        memoryLruChache.put(key, value);
        diskCache.put(key, value);
    }

    @Override
    public Bitmap get(String key) {
        Bitmap value = memoryLruChache.get(key);
        if (value != null) {
            return value;
        } else {
            value = diskCache.get(key);
        }
        return value;
    }

    @Override
    public void remove(String key) {
        memoryLruChache.remove(key);
        diskCache.remove(key);
    }

    public void onPause() {
        diskCache.onPause();
    }

    public void onDestroy() {
        diskCache.close();
    }
}
