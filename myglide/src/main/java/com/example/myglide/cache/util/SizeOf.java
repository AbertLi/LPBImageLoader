package com.example.myglide.cache.util;

import android.graphics.Bitmap;

/**
 * 粗略计算对象大小。
 */
public class SizeOf {
    public static long getObjSize(Object o) {
        if (o instanceof Bitmap) {
            return ((Bitmap) o).getByteCount();
        } else {
            return 0L;
        }
    }
}
