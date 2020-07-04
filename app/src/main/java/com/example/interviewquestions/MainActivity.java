package com.example.interviewquestions;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.interviewquestions.kotlin.Singleton;
import com.example.interviewquestions.useglide.GlideActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String imageUri = "http://pic1.win4000.com/pic/7/00/8e25729063.jpg";
        Uri uri = Uri.parse(imageUri);
        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
        draweeView.setImageURI(uri);

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                //加载的图片URI地址
                .setUri(uri)
                //设置点击重试是否开启
                .setTapToRetryEnabled(true)
                //设置旧的Controller
                .setOldController(draweeView.getController())
                //构建
                .build();
        draweeView.setController(controller);
    }

    public void btn(View view) {
        switch (view.getId()) {
            case R.id.tv:
                SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
                ImageLoadUtil.loadImgByResources(draweeView, R.drawable.mn);
                break;

            case R.id.btn1:
                Log.e("MainActivity", Singleton.Companion.getInstance().getId());

                break;
            case R.id.btn2:
                Intent intent = new Intent(this, GlideActivity.class);
                startActivity(intent);
                break;
            case R.id.btn3:
                break;
            case R.id.btn11:
                break;
            case R.id.btn12:
                break;
        }

    }
}
