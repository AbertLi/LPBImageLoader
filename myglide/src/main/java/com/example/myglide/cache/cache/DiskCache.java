package com.example.myglide.cache.cache;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.example.myglide.cache.base.BaseCache;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 图片存入到SD卡中
 * DiskLruCache 解决方案使用详解
 * https://www.tuicool.com/articles/JB7RNj
 */
public class DiskCache implements BaseCache {
    private final int MAX_SIZ = 50 * 1024 * 1024;//LruCache最大存50M的图片
    private static Context mContext;
    private int appVersion;
    private File mCacheDirFile;
    private String pathName = "bitmap";
    private DiskLruCache mDiskLruCache;


    private static class Holder {
        private static DiskCache diskCache = new DiskCache();
    }

    public static DiskCache getInstance(Context context) {
        mContext = context.getApplicationContext();
        return Holder.diskCache;
    }

    private DiskCache() {
        mCacheDirFile = getDiskCacheDir(mContext, pathName);
        appVersion = getVersionCode(mContext);
        try {
            mDiskLruCache = DiskLruCache.open(mCacheDirFile, appVersion, 1, MAX_SIZ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void put(String key, Bitmap value) {
        if (key == null) {
            throw new NullPointerException("key is not null");
        }
        // index与valueCount对应，分别为0,1,2...valueCount-1
        try {
            DiskLruCache.Editor editor = mDiskLruCache.edit(key);
            if (editor != null) {
                OutputStream outputStream = editor.newOutputStream(0);
                if (inOut(bitmapToInputStream(value), outputStream)) {
                    editor.commit();
                } else {
                    editor.abort();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bitmap get(String key) {
        try {
            DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);
            if (snapShot != null) {
                InputStream is = snapShot.getInputStream(0);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(String key) {
        try {
            mDiskLruCache.remove(String.valueOf(key));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onPause(){
        try {
            mDiskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            mDiskLruCache.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static synchronized int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    public File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    private boolean inOut(InputStream inputStream, OutputStream outputStream) {
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(inputStream, 8 * 1024);
            out = new BufferedOutputStream(outputStream, 8 * 1024);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public InputStream bitmapToInputStream(Bitmap myBitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        ByteArrayInputStream byteArrInputStream = new ByteArrayInputStream(
                baos.toByteArray());
        return byteArrInputStream;
    }
}
