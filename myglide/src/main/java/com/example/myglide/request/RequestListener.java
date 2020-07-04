package com.example.myglide.request;

import android.graphics.Bitmap;

public interface RequestListener {
    void onSucces(Bitmap bitmap);
    void onFile();
}
