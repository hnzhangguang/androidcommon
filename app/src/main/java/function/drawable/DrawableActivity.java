package function.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.yy.app.R;

public class DrawableActivity extends AppCompatActivity {

    ImageView imageView = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // xml
        //        <?xml version="1.0" encoding="utf-8"?>
        //           <color xmlns:android="http://schemas.android.com/apk/res/android"
        //        android:color="#FF0000" />
        // java
        ColorDrawable drawable = new ColorDrawable(0xffff0000);


        // BitmapDrawable
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.banana);
        BitmapDrawable mBitmapDrawable = new BitmapDrawable(mBitmap);
        mBitmapDrawable.setTileModeXY(Shader.TileMode.MIRROR,
                Shader.TileMode.MIRROR);
        mBitmapDrawable.setAntiAlias(true);
        mBitmapDrawable.setDither(true);

        imageView.setImageBitmap(mBitmap);
        imageView.setBackgroundDrawable(mBitmapDrawable);


        //InsetDrawable
        //        <!--InsetDrawable 表示一个drawable嵌入到另外一个drawable内部，并且在内部留一些间距，这一点很像drawable的padding属性，
        //        区别在于 padding表示drawable的内容与drawable本身的边距，insetDrawable表示两个drawable和容器之间的边距。
        //        当控件需要的背景比实际的边框小的时候比较适合使用InsetDrawable。
        //        -- >
        //        <inset xmlns:android = "http://schemas.android.com/apk/res/android"
        //            android:
        //            drawable = "@drawable/banana_pic"
        //            android:
        //            insetLeft = "20dp"
        //            android:
        //            insetRight = "20dp"
        //            android:
        //            insetTop = "20dp"
        //            android:
        //            insetBottom = "20dp" ></inset >

    }


}
