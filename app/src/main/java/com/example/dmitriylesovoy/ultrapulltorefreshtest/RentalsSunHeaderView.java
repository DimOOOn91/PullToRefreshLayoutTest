package com.example.dmitriylesovoy.ultrapulltorefreshtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import in.srain.cube.views.ptr.indicator.PtrTensionIndicator;
import timber.log.Timber;

/**
 * Created by dimal on 10.10.2017.
 */

public class RentalsSunHeaderView extends View implements PtrUIHandler {

    private RentalsSunDrawable mDrawable;
    private PtrFrameLayout mPtrFrameLayout;
    private PtrTensionIndicator mPtrTensionIndicator;

    public RentalsSunHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RentalsSunHeaderView(Context context) {
        super(context);
        init();
    }

    public RentalsSunHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setUp(PtrFrameLayout ptrFrameLayout) {
        mPtrFrameLayout = ptrFrameLayout;
        mPtrTensionIndicator = new PtrTensionIndicator();
        mPtrFrameLayout.setPtrIndicator(mPtrTensionIndicator);
    }

    private void init() {
        mDrawable = new RentalsSunDrawable(getContext(), this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Timber.d("onMeasure");
        int height = mDrawable.getTotalDragDistance() * 5 / 4;
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height + getPaddingTop() + getPaddingBottom(), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Timber.d("onLayout");
        int pl = getPaddingLeft();
        int pt = getPaddingTop();
        mDrawable.setBounds(pl, pt, pl + right - left, pt + bottom - top);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        Timber.d("onUIReset");
        mDrawable.resetOriginals();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Timber.d("onDraw");
        mDrawable.draw(canvas);
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        Timber.d("onUIRefreshPrepare");

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        Timber.d("onUIRefreshBegin");
        mDrawable.start();
        float percent = mPtrTensionIndicator.getOverDragPercent();
        mDrawable.offsetTopAndBottom(mPtrTensionIndicator.getCurrentPosY());
        mDrawable.setPercent(percent);
        invalidate();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        Timber.d("onUIRefreshComplete");
        float percent = mPtrTensionIndicator.getOverDragPercent();
        mDrawable.stop();
        mDrawable.offsetTopAndBottom(mPtrTensionIndicator.getCurrentPosY());
        mDrawable.setPercent(percent);
        invalidate();
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        Timber.d("onUIPositionChange");
        float percent = mPtrTensionIndicator.getOverDragPercent();
        mDrawable.offsetTopAndBottom(mPtrTensionIndicator.getCurrentPosY());
        mDrawable.setPercent(percent);
        invalidate();
    }

    @Override
    public void invalidateDrawable(Drawable dr) {
        Timber.d("invalidateDrawable");
        if (dr == mDrawable) {
            invalidate();
        } else {
            super.invalidateDrawable(dr);
        }
    }
}
