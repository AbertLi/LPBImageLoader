package com.example.interviewquestions.useglide

import android.app.Activity
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.interviewquestions.R
import com.example.myglide.request.RequestListener

class GlideActivity : Activity() {
    val TAG = "GlideActivity"
    private var scrollLinerLayout: LinearLayout? = null
    private var arrayImageUrl: Array<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)
        scrollLinerLayout = findViewById(R.id.scroll_ll)
        initArray()
    }

    fun initArray() {
        arrayImageUrl = arrayOf(
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593458275061&di=53d78c5f86d0c0f2982be4737f6e2a89&imgtype=0&src=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D1153356592%2C2155849117%26fm%3D214%26gp%3D0.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593458274726&di=bcf1e811d2cbbd8b28a51f8e5e91a543&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fpic%2Fe%2Fb3%2F46d5317917.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593458274725&di=cee4a23c173f259f80351ca91b5b7130&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fbd1390b82e032452e04c854ffa37b36a0f8dc36612c85-6l6Kk6_fw658",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593458274724&di=826c03930e3850a8a6af662ed82ec5a2&imgtype=0&src=http%3A%2F%2Fpic.rmb.bdstatic.com%2F4d90e621f157f1839a804d1de1f5564b.jpeg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593458274724&di=bed5dfd55591754210c186aaf9c2fa5b&imgtype=0&src=http%3A%2F%2F01.minipic.eastday.com%2F20170410%2F20170410134411_cb5ffda36f5993e00b95c3a9fd7cca39_4.jpeg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593458274724&di=fb53900a279c57e606b4164d28155d04&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fpic%2Fe%2F08%2F94e5785952.jpg",
                "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2336015033,4191185335&fm=26&gp=0.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593458274723&di=60449f1b7d2b633dd8050b728ac4a9e4&imgtype=0&src=http%3A%2F%2Fimg2.juzituku.com%2F20170407%2F17%2F1709285af356.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593458368262&di=689037884be58fed6a0321992556f211&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fpic%2F3%2F89%2F71b61306731.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593458274723&di=5bcb4070a22e232bfad81c7dc85888e7&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fpic%2Fb%2Fd9%2Feb881507597.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593458274723&di=84cdeffeeaf38411df23ac670051bfa4&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fpic%2F8%2F80%2Ffd08370658.jpg",
                "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=131585496,3131937149&fm=26&gp=0.jpg",
                "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=339948989,3571598694&fm=26&gp=0.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593458456382&di=244c3bfb4ac5ac20fefe7e5083144bd6&imgtype=0&src=http%3A%2F%2Fztd00.photos.bdimg.com%2Fztd%2Fw%3D700%3Bq%3D50%2Fsign%3D4f855a33b10e7bec23da01e11f15c805%2Ff636afc379310a55c59d90c8be4543a982261054.jpg")
    }


    fun btn(view: View) {
        when (view.id) {
            R.id.btn_glide -> {
                glideImage()
            }
            R.id.btn_myglide -> {
                myGlideImage()
            }
        }
    }

    fun glideImage() {
        for(strUrl: String in arrayImageUrl!!){
            var imageView  = ImageView(this)
            scrollLinerLayout?.addView(imageView)
                Glide.with(this)
                .load(strUrl)
                .into(imageView);
        }
    }

    fun myGlideImage() {
        for(strUrl: String in arrayImageUrl!!){
            var imageView  = ImageView(this)
            scrollLinerLayout?.addView(imageView)
            com.example.myglide.request.Glide.with(this)
                    .load(strUrl)
                    .loading(R.drawable.loading)
                    .listener(object :RequestListener{
                        override fun onFile() {
                            Log.e(TAG,"onFile")
                        }

                        override fun onSucces(bitmap: Bitmap?) {
                            Log.e(TAG,"onSucces")
                        }

                    })
                    .into(imageView);
        }
    }
}
