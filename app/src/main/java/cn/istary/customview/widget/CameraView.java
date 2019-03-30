package cn.istary.customview.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import cn.istary.customview.R;

/*
 * CREATED BY: Sinry
 * TIME: 2019/3/30 17:54
 * DESCRIPTION:
 */

public class CameraView extends View {

    public static final int CLIP = 0;
    public static final int DEFAULT = 1;
    public static final int CAMERA_ROTATEX = 2;
    public static final int CAMERA_LOCATION = 5;

    private int mWidth, mHeight;
    private Context mContext;
    private Bitmap mBitmap;
    private Paint mPaint;

    private int mMode;

    public CameraView(Context context) {
        super(context);
        init(context);
    }

    public CameraView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CameraView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.xuexiaoban);
        mPaint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        myDraw(canvas, mMode);
    }

    private void myDraw(Canvas canvas, int mode) {
        Camera camera = new Camera();
        switch (mode){
            case DEFAULT:
                canvas.save();
                canvas.translate(mWidth>>1, mHeight>>1);
                canvas.drawBitmap(mBitmap, -(mBitmap.getWidth()>>1), -(mBitmap.getHeight()>>1), null);
                canvas.restore();
                break;
            case CLIP:
                canvas.save();
                canvas.translate(mWidth>>1, mHeight>>1);
                canvas.clipRect(-100, -100, 100,100);
                canvas.drawBitmap(mBitmap, -(mBitmap.getWidth()>>1), -(mBitmap.getHeight()>>1), null);
                canvas.restore();
                break;
            case CAMERA_ROTATEX:
                canvas.save();
                camera.save();
                camera.rotateX(30);
                canvas.translate(mWidth>>1, mHeight>>1);
                camera.applyToCanvas(canvas);
                canvas.drawBitmap(mBitmap, -(mBitmap.getWidth()>>1), -(mBitmap.getHeight()>>1), null);
                camera.restore();
                canvas.restore();
                break;

            case CAMERA_LOCATION:
                canvas.save();
                camera.save();
                camera.setLocation(0, 0, -600);
                camera.rotateX(30);
                canvas.translate(mWidth>>1, mHeight>>1);
                camera.applyToCanvas(canvas);
                canvas.drawBitmap(mBitmap, -(mBitmap.getWidth()>>1), -(mBitmap.getHeight()>>1), null);
                camera.restore();
                canvas.restore();
                break;
        }
    }

    public void setMode(int mode){
        this.mMode = mode;
        invalidate();
    }
}
