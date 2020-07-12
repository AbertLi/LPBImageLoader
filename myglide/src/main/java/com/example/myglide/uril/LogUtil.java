package com.example.myglide.uril;

import android.util.Log;

public class LogUtil {
    private static boolean IS_OPENLOG = true;

    public static void i(String tag, String str) {
        if (IS_OPENLOG) {
            Log.i(tag, str);
        }
    }

    public static void d(String tag, String str) {
        if (IS_OPENLOG) {
            Log.d(tag, str);
        }
    }

    public static void e(String tag, String str) {
        if (IS_OPENLOG) {
            Log.e(tag, str);
        }
    }
}
