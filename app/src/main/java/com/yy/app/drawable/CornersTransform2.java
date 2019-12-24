package com.yy.app.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * 图片预处理(圆角,高斯模糊等)
 * <p>
 * 其中transform方法里面能拿到bitmap对象,这里就是对图片做处理的地方
 * <p>
 * 举例(圆角处理)
 */
public class CornersTransform2 extends BitmapTransformation {

    private float radius;

    public CornersTransform2(Context context, float radius) {
        super(context);
        this.radius = radius;
    }

    public CornersTransform2(Context context) {
        super(context);
        radius = 10;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        //TODO
        return cornersCrop(pool, toTransform);
    }

    private Bitmap cornersCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP,
                BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);
        return result;
    }


    @Override
    public String getId() {
        //TODO
        return getClass().getName();
    }
}