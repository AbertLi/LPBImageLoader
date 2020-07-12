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
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593458456382&di=244c3bfb4ac5ac20fefe7e5083144bd6&imgtype=0&src=http%3A%2F%2Fztd00.photos.bdimg.com%2Fztd%2Fw%3D700%3Bq%3D50%2Fsign%3D4f855a33b10e7bec23da01e11f15c805%2Ff636afc379310a55c59d90c8be4543a982261054.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593971145809&di=cddb97a0243e42568ef274ee2ad13caf&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F9%2F518878b030252.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593971145809&di=d9a6998ec0e23ee752943192ff7fe1c4&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2017-11-24%2F5a178c850a0ba.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593971145808&di=46ce7a6d9f0374399de5b52ad17cdd60&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F7%2F58ca07b8bcc30.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593971145808&di=9375afa6ba824df1fa43a1337c69d500&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20190519%2F2d6a2ef055a64d109798f484f16ee53d.jpeg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593971204849&di=83d322c253e7eacfcf70850aca3bb2b8&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2017-11-13%2F5a090a5aab8b6.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593971145807&di=292f6489c5ac2118b27181a81f13a7b8&imgtype=0&src=http%3A%2F%2Fp0.qhmsg.com%2Ft01d745429df5532a04.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593971145806&di=ce30f5ab64b6dae163565f4a9f49d263&imgtype=0&src=http%3A%2F%2Fwww.33lc.com%2Farticle%2FUploadPic%2F2012-10%2F201210251614177768.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593971145800&di=6c5b77ca2923af59939829521f3fd898&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2Fb%2F538fe4398e4e0.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593971145797&di=81056ac386da18f5349b05e697c9f882&imgtype=0&src=http%3A%2F%2Fimage.hnol.net%2Fc%2F2016-11%2F11%2F22%2F20161111221941171-619336.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593971261363&di=c923276fdcf4bbd50ca9f12fc516c516&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fwallpaper%2F1301%2F04%2Fc1%2F17113690_1357280871286_800x800.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593971341617&di=4dd6d518f8ca46a6806b2ce5c8ade3dd&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2017-11-10%2F5a0555de6e54d.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593971261361&di=9c107a9315016dada0c4b0c9b27aeb9c&imgtype=0&src=http%3A%2F%2Fwww.gd188.cn%2Ffile%2Fupload%2F201412%2F28%2F15%2F15-54-52-29-194012.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593971321710&di=e540733b15e0bdab9a1cd07d58212150&imgtype=0&src=http%3A%2F%2Fgss0.baidu.com%2F7Po3dSag_xI4khGko9WTAnF6hhy%2Fzhidao%2Fpic%2Fitem%2Fe850352ac65c10386cedd91eb2119313b17e895e.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593971321709&di=726b9f55db92951792b753a19cf86419&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fd10fa29df8239ad733ac5d30d28064ee93d5efdd2a8d9-M96aTW_fw658",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593971321702&di=c8180aeaa97600f87776bae368aaa120&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fpic%2Fc%2F54%2Fc31f1159643.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593971452934&di=8c41716bc6baad6a1b7cbbd33190d022&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fpic%2F2%2F87%2Fcacb1377894.jpg%3Fdown",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593971511627&di=61ae665e618807e6d4cc51ac4b5d78c9&imgtype=0&src=http%3A%2F%2Fpic.feizl.com%2Fupload%2Fallimg%2F180104%2F181250kylfwpejn33.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593971419010&di=0dbee77708a7cabb4f395bdeec153eda&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fpic%2F2%2F4c%2Fa407803649.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593971496895&di=ee886df95c2d7872f90259c3db2df6f0&imgtype=0&src=http%3A%2F%2Ft7.baidu.com%2Fit%2Fu%3D1100447573%2C174656238%26fm%3D193",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1593971584487&di=5b695aded1901a9e71c0dd768a074ab7&imgtype=0&src=http%3A%2F%2Ft9.baidu.com%2Fit%2Fu%3D1526687803%2C1035093998%26fm%3D193"
        )

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
        for (strUrl: String in arrayImageUrl!!) {
            var imageView = ImageView(this)
            scrollLinerLayout?.addView(imageView)
            Glide.with(this)
                    .load(strUrl)
                    .into(imageView);
        }
    }

    fun myGlideImage() {
        for (strUrl: String in arrayImageUrl!!) {
            var imageView = ImageView(this)
            scrollLinerLayout?.addView(imageView)
            com.example.myglide.request.Glide.with(this)
                    .load(strUrl)
                    .loading(R.drawable.loading)
                    .listener(object : RequestListener {
                        override fun onFile() {
                            Log.e(TAG, "onFile")
                        }

                        override fun onSucces(bitmap: Bitmap?) {
                            Log.e(TAG, "onSucces")
                        }

                    })
                    .into(imageView);
        }
    }
}
