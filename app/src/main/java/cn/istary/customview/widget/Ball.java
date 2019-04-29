package cn.istary.customview.widget;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/31 12:51
 * DESCRIPTION:
 */

import android.graphics.Color;
import android.graphics.Point;

public class Ball extends Point {

    //小球半径
    private float mRadius;
    //颜色
    private int mColor;
    //速度
    private float mVX, mVY;
    //加速度
    private int mAX, mAY;

    private Ball(){
    }

/*    public Ball(int x, int y) {
        this(x, y, 10f, Color.RED, 20, 0, 0, 0);
    }

    public Ball(){
        this(0, 0, 10f, Color.RED, 20, 0, 0, 0);
    }*/

    private Ball(int x, int y, float radius, int color, float VX, float VY, int AX, int AY) {
        super(x, y);
        mRadius = radius;
        mColor = color;
        mVX = VX;
        mVY = VY;
        mAX = AX;
        mAY = AY;
    }

    public float getRadius() {
        return mRadius;
    }

    public void setRadius(float radius) {
        mRadius = radius;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public float getVX() {
        return mVX;
    }

    public void setVX(float VX) {
        mVX = VX;
    }

    public float getVY() {
        return mVY;
    }

    public void setVY(float VY) {
        mVY = VY;
    }

    public int getAX() {
        return mAX;
    }

    public void setAX(int AX) {
        mAX = AX;
    }

    public int getAY() {
        return mAY;
    }

    public void setAY(int AY) {
        mAY = AY;
    }

    public void turnX(){
        this.mVX = -mVX;
    }

    public void turnY(){
        this.mVY = -mVY;
    }


    public static final class Builder{
        private int x, y;
        //小球半径
        private float radius;
        //颜色
        private int color;
        //速度
        private float vX, vY;
        //加速度
        private int aX, aY;

        public Builder setRadius(float radius) {
            this.radius = radius;
            return this;
        }

        public Builder setColor(int color) {
            this.color = color;
            return this;
        }

        public Builder setVX(float VX) {
            this.vX = VX;
            return this;
        }

        public Builder setVY(float VY) {
            this.vY = VY;
            return this;
        }

        public Builder setAX(int AX) {
            this.aX = AX;
            return this;
        }

        public Builder setAY(int AY) {
            this.aY = AY;
            return this;
        }

        public Builder setX(int x){
            this.x = x;
            return this;
        }

        public Builder setY(int y){
            this.y = y;
            return this;
        }

        public Ball build(){
            //默认半径10
            if(this.radius == 0){
                this.radius = 10;
            }
            //默认红色
            if(this.color == 0){
                this.color = Color.rgb(255, 0, 0);
            }

            return new Ball(x, y, radius, color, vX, vY, aX, aY);
        }
    }
}
