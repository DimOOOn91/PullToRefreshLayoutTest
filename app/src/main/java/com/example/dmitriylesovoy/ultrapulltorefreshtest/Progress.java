package com.example.dmitriylesovoy.ultrapulltorefreshtest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import in.srain.cube.views.ptr.indicator.PtrTensionIndicator;

public class Progress extends RelativeLayout implements PtrUIHandler {
    private View rootView;
    private PtrFrameLayout ptrFrameLayout;
    private PtrTensionIndicator ptrTensionIndicator;
    private CircularProgressView circularProgressView;

    public Progress(Context context) {
        super(context);
        initialize(context);
    }

    public Progress(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public Progress(Context context, AttributeSet attrs, int i) {
        super(context, attrs, i);
        initialize(context);
    }



    private void initialize(Context context) {
        rootView = inflate(context, R.layout.progress_layout, this);
        circularProgressView = (CircularProgressView) rootView.findViewById(R.id.progress);
    }

    public void setUp(PtrFrameLayout ptrFrameLayout) {
        this.ptrFrameLayout = ptrFrameLayout;
        this.ptrTensionIndicator = new PtrTensionIndicator();
        ptrFrameLayout.setPtrIndicator(ptrTensionIndicator);
    }

//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        int pl = getPaddingLeft();
//        int pt = getPaddingTop();
//        super.onLayout(changed, left + pl, top + pt, right, bottom);
//    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        circularProgressView.setVisibility(VISIBLE);
        circularProgressView.startAnimation();
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        circularProgressView.startAnimation();
        circularProgressView.setVisibility(VISIBLE);

        float percent = ptrTensionIndicator.getOverDragPercent();
//        circularProgressView.offsetTopAndBottom(ptrTensionIndicator.getCurrentPosY());
        invalidate();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
//        setVisibility(GONE);

        float percent = ptrTensionIndicator.getOverDragPercent();
//        circularProgressView.stopAnimation();
//        circularProgressView.offsetTopAndBottom(ptrTensionIndicator.getCurrentPosY());
        invalidate();
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        float percent = ptrTensionIndicator.getOverDragPercent();
//        circularProgressView.offsetTopAndBottom(ptrTensionIndicator.getCurrentPosY());
        invalidate();
    }
}
