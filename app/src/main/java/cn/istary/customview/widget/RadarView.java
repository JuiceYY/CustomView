package cn.istary.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/*
 * CREATED BY: Sinry
 * TIME: 2019/3/29 17:39
 * DESCRIPTION:
 */

public class RadarView extends View {

    private static final String TAG = "RadarView";

    private int mWidth, mHeight;
    private int mItemCount; //维数(多边形边数)
    private float[] mItemValue; //每一维的值
    private int mMaxValue; //每一维的最大值
    private String[] mTitles; //每一维的名字
    private int mLayers; //雷达图一共有多少层
    private float mRadius; //雷达图半径
    private int mCenterX, mCenterY; //中心位置

    private Paint mBackPaint;//背景图画笔
    private Paint mValuePaint; //雷达画笔
    private Paint mTextPaint; //  文字画笔

    private Context mContext;

    public void setItemCount(int itemCount) {
        mItemCount = itemCount;
        invalidate();
    }

    public void setItemValue(float[] itemValue) {
        mItemValue = itemValue;
        invalidate();
    }

    public void setTitles(String[] titles) {
        mTitles = titles;
        invalidate();
    }

    public void setMaxValue(int maxValue) {
        mMaxValue = maxValue;
        invalidate();
    }

    public void setLayers(int layers) {
        mLayers = layers;
        invalidate();
    }

    public RadarView(Context context) {
        super(context);
        init();
    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mCenterX = w >> 1;
        mCenterY = h >> 1;
        mRadius = (Math.min(w, h) >> 1) * 0.8f;
    }

    private void init() {
        mBackPaint = new Paint();
        mBackPaint.setColor(Color.LTGRAY);
        mBackPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        mBackPaint.setStrokeWidth(5);
        mValuePaint = new Paint();
        mValuePaint.setColor(Color.BLUE);
        mValuePaint.setStyle(Paint.Style.FILL_AND_STROKE);    // 填充模式 - 描边
        mValuePaint.setStrokeWidth(5);
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw");

        drawPolygon(canvas);
        drawLines(canvas);
        drawTitle(canvas);
        drawArea(canvas);
    }

    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float r = mRadius / mLayers;
        float angle = (float) (Math.PI * 2 / mItemCount);
        for (int i = 0; i < mLayers; i++) {
            float curR = r * (i + 1);
            path.reset();
            for (int j = 0; j < mItemCount; j++) {
                if (j == 0) {
                    path.moveTo(mCenterX + curR, mCenterY);
                } else {
                    float x = (float) (mCenterX + curR * Math.cos(angle * j));
                    float y = (float) (mCenterY + curR * Math.sin(angle * j));
                    path.lineTo(x, y);

                }
            }
            path.close();
            canvas.drawPath(path, mBackPaint);
        }
    }

    private void drawLines(Canvas canvas) {
        Path path = new Path();
        float angle = (float) (Math.PI * 2 / mItemCount);
        for (int i = 0; i < mItemCount; i++) {
            path.reset();
            path.moveTo(mCenterX, mCenterY);
            float x = (float) (mCenterX + mRadius * Math.cos(angle * i));
            float y = (float) (mCenterY + mRadius * Math.sin(angle * i));
            path.lineTo(x, y);
            canvas.drawPath(path, mBackPaint);
        }
    }

    private void drawTitle(Canvas canvas) {
        float angle = (float) (Math.PI * 2 / mItemCount);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for (int i = 0; i < mItemCount; i++) {
            float curAngle = angle * i;
            float x = (float) (mCenterX + (mRadius + fontHeight / 2) * Math.cos(curAngle));
            float y = (float) (mCenterY + (mRadius + fontHeight / 2) * Math.sin(curAngle));
            if (curAngle >= 0 && curAngle <= Math.PI / 2) {
                //第一象限, 象限按安卓坐标系看
                canvas.drawText(mTitles[i], x, y, mTextPaint);
            } else if (curAngle > Math.PI / 2 && curAngle <= Math.PI) {
                //第二象限, 后退一个文本长度
                float dis = mTextPaint.measureText(mTitles[i]);
                canvas.drawText(mTitles[i], x - dis, y, mTextPaint);
            } else if (curAngle > Math.PI && curAngle <= 3 * Math.PI / 2) {
                //第三象限, 文本后退
                float dis = mTextPaint.measureText(mTitles[i]);
                canvas.drawText(mTitles[i], x - dis, y, mTextPaint);
            } else {
                //第四象限
                canvas.drawText(mTitles[i], x, y, mTextPaint);
            }

        }
    }

    public void drawArea(Canvas canvas) {
        float angle = (float) (2 * Math.PI / mItemCount);
        Path path = new Path();
        for (int i = 0; i < mItemCount; i++) {
            mValuePaint.setStyle(Paint.Style.FILL);
            if (i == 0) {
                float x = mCenterX + mItemValue[i] / mMaxValue * mRadius;
                path.moveTo(x, mCenterY);
                canvas.drawCircle(x, mCenterY, 10, mValuePaint);
            } else {
                float x = (float) (mCenterX + mRadius * Math.cos(angle * i) * (mItemValue[i] / mMaxValue));
                float y = (float) (mCenterY + mRadius * Math.sin(angle * i) * (mItemValue[i] / mMaxValue));
                path.lineTo(x, y);
                canvas.drawCircle(x, y, 10, mValuePaint);
            }
        }
        path.close();
        mValuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mValuePaint.setAlpha(127);
        canvas.drawPath(path, mValuePaint);

    }

    //对外提供接口
    public void set(int itemCount, float[] itemValue, int maxValue, String[] itemTitle, int layers) {
        this.mItemCount = itemCount;
        this.mLayers = layers;
        this.mItemValue = itemValue;
        this.mMaxValue = maxValue;
        this.mTitles = itemTitle;
        invalidate();
    }
}
