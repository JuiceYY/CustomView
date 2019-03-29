package cn.istary.customview.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.print.PrinterId;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.lang.ref.WeakReference;

import cn.istary.customview.R;

/*
 * CREATED BY: Sinry
 * TIME: 2019/3/28 22:10
 * DESCRIPTION:
 */

public class CheckView extends View {

    private static final String TAG = "CheckView";

    private static final int ANIM_NULL = 0;         //动画状态-没有
    private static final int ANIM_CHECK = 1;        //动画状态-开启
    private static final int ANIM_UNCHECK = 2;      //动画状态-结束

    private Context mContext;           // 上下文
    private int mWidth, mHeight;        // 宽高

    private Paint mPaint;
    private Bitmap okBitmap;

    int animCurrentPage = -1;       // 当前页码
    int animMaxPage = 13;           // 总页数
    int animDuration = 500;         // 动画时长
    int animState = ANIM_NULL;      // 动画状态

    boolean isCheck = false;        // 是否只选中状态

    private final MyHandler mHandler = new MyHandler(this);           // handler

    public CheckView(Context context) {
        super(context, null);

    }

    public CheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化
     * @param context
     */
    private void init(Context context) {
        mContext = context;

        mPaint = new Paint();
        mPaint.setColor(0xffFF5317);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        okBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.checkmark);
    }


    /**
     * View大小确定
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    /**
     * 绘制内容
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 移动坐标系到画布中央
        canvas.translate(mWidth >> 1, mHeight >> 1);

        // 绘制背景圆形
        canvas.drawCircle(0, 0, 240, mPaint);

        // 得出图像边长
        int sideLength = okBitmap.getHeight();

        // 得到图像选区 和 实际绘制位置
        Rect src = new Rect(sideLength * animCurrentPage, 0, sideLength * (animCurrentPage + 1), sideLength);
        Rect dst = new Rect(-200, -200, 200, 200);

        // 绘制
        canvas.drawBitmap(okBitmap, src, dst, null);
    }


    /**
     * 选择
     */
    public void check() {
        if (animState != ANIM_NULL || isCheck)
            return;
        animState = ANIM_CHECK;
        animCurrentPage = 0;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        isCheck = true;
    }

    /**
     * 取消选择
     */
    public void unCheck() {
        if (animState != ANIM_NULL || (!isCheck))
            return;
        animState = ANIM_UNCHECK;
        animCurrentPage = animMaxPage - 1;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        isCheck = false;
    }

    /**
     * 设置动画时长
     * @param animDuration
     */
    public void setAnimDuration(int animDuration) {
        if (animDuration <= 0)
            return;
        this.animDuration = animDuration;
    }

    /**
     * 设置背景圆形颜色
     * @param color
     */
    public void setBackgroundColor(int color){
        mPaint.setColor(color);
    }

    private static class MyHandler extends Handler{
        WeakReference<CheckView> mCheckViewWeakReference;

        public MyHandler(CheckView checkView){
            this.mCheckViewWeakReference = new WeakReference<>(checkView);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            CheckView view = mCheckViewWeakReference.get();
            if (view.animCurrentPage < view.animMaxPage && view.animCurrentPage >= 0) {
                view.invalidate();
                if (view.animState == ANIM_NULL)
                    return;
                if (view.animState == ANIM_CHECK) {

                    view.animCurrentPage++;
                } else if (view.animState == ANIM_UNCHECK) {
                    view.animCurrentPage--;
                }

                this.sendEmptyMessageDelayed(0, view.animDuration / view.animMaxPage);
                Log.e(TAG, "Count=" + view.animCurrentPage);
            } else {
                if (view.isCheck) {
                    view.animCurrentPage = view.animMaxPage - 1;
                } else {
                    view.animCurrentPage = -1;
                }
                view.invalidate();
                view.animState = ANIM_NULL;
            }
        }
    }
}
