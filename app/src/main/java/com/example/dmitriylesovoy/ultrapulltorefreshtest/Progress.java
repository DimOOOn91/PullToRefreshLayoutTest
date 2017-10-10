package com.example.dmitriylesovoy.ultrapulltorefreshtest;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import in.srain.cube.views.ptr.indicator.PtrTensionIndicator;

public class Progress extends CircularProgressView implements PtrUIHandler {
    private PtrFrameLayout ptrFrameLayout;
    private PtrTensionIndicator ptrTensionIndicator;

    public Progress(Context context) {
        super(context);
        initialize();
    }

    public Progress(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public Progress(Context context, AttributeSet attrs, int i) {
        super(context, attrs, i);
        initialize();
    }

    private void initialize() {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(50, 50);
        setLayoutParams(layoutParams);
        setIndeterminate(true);
        setColor(ContextCompat.getColor(getContext(), android.R.color.holo_red_dark));
        setVisibility(VISIBLE);
    }

    public void setUp(PtrFrameLayout ptrFrameLayout) {
        this.ptrFrameLayout = ptrFrameLayout;
        this.ptrTensionIndicator = new PtrTensionIndicator();
        ptrFrameLayout.setPtrIndicator(ptrTensionIndicator);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int pl = getPaddingLeft();
        int pt = getPaddingTop();
        super.onLayout(changed, left + pl, top + pt, right, bottom);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        setRotation(0);
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        startAnimation();
        setVisibility(VISIBLE);

        float percent = ptrTensionIndicator.getOverDragPercent();
        offsetTopAndBottom(ptrTensionIndicator.getCurrentPosY());
        setRotation(percent);
        invalidate();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
//        setVisibility(GONE);

        float percent = ptrTensionIndicator.getOverDragPercent();
        stopAnimation();
        offsetTopAndBottom(ptrTensionIndicator.getCurrentPosY());
        setRotation(percent);
        invalidate();
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        float percent = ptrTensionIndicator.getOverDragPercent();
        offsetTopAndBottom(ptrTensionIndicator.getCurrentPosY());
        setRotation(percent);
        invalidate();
    }
}
