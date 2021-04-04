package com.example.fling_billiard_ball;

public class Ball {
    public int mX;
    public int mY;
    public int mRadius;
    public double mVelocityX;
    public double mVelocityY;
    public int left, right, top, bottom;

    public Ball(int mX, int mY, int mRadius, double mVelocityX, double mVelocityY){
        this.mX = mX;
        this.mY = mY;
        this.mRadius = mRadius;
        this.mVelocityX = mVelocityX;
        this.mVelocityY = mVelocityY;
    }

    public void move(){
        mX += mVelocityX;
        mY += mVelocityY;

        //COLLISIONS - REVERSE DIRECTIONS
        if (mX < left){
            mX = left;
            mVelocityX *= -1;
        }
        else if (mX > right){
            mX = right;
            mVelocityX *= -1;
        }
        if (mY < top){
            mY = top;
            mVelocityY *= -1;
        }
        else if (mY > bottom){
            mY = bottom;
            mVelocityY *= -1;
        }

        //APPLY FRICTION TO THE VELOCITY
        mVelocityX *= .93;
        mVelocityY *= .93;
        if (Math.abs(mVelocityX) < 1) mVelocityX = 0;
        if (Math.abs(mVelocityY) < 1) mVelocityY = 0;

    }
    public void setCollisionBoundaries( int windowWidth, int windowHeight){
        left = -windowWidth / 2 + mRadius;
        right = windowWidth / 2 - mRadius;
        top = -windowHeight / 2 + mRadius;
        bottom = windowHeight / 2 - mRadius;
    }
}
