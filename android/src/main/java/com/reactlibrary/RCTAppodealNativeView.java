package com.reactlibrary;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.BannerCallbacks;
import com.appodeal.ads.BannerView;
import com.appodeal.ads.MrecCallbacks;
import com.appodeal.ads.NativeAd;
import com.appodeal.ads.NativeAdView;
import com.appodeal.ads.NativeCallbacks;
import com.appodeal.ads.NativeIconView;
import com.appodeal.ads.NativeMediaView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.view.ReactViewGroup;

public class RCTAppodealNativeView extends LinearLayout implements NativeCallbacks {

    Context mContext;
    private NativeAdView nativeAdView;
    private NativeMediaView nativeMediaView;
    private NativeIconView nativeIconView;

    public RCTAppodealNativeView(ThemedReactContext context) {
        super(context);
        mContext = context;
        createView(context);
    }

    public void createView(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View viewRoot = layoutInflater.inflate(R.layout.native_ads, this, true);
        nativeAdView = viewRoot.findViewById(R.id.native_ad_view_app_wall);

    }

    private final Runnable measureAndLayout = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                child.measure(
                        MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY)
                );
                child.layout(0, 0, child.getMeasuredWidth(), child.getMeasuredHeight());
            }
        }
    };


    @Override
    public void requestLayout() {
        super.requestLayout();
        post(measureAndLayout);
    }

    private ReactContext getReactContext() {
        return (ReactContext)getContext();
    }

    private RCTEventEmitter getEmitter() {
        return  getReactContext().getJSModule(RCTEventEmitter.class);
    }

    private int dp2px(int dp, DisplayMetrics dm) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, dm));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public void onNativeLoaded() {

    }

    @Override
    public void onNativeFailedToLoad() {

    }

    @Override
    public void onNativeShown(NativeAd nativeAd) {

    }

    @Override
    public void onNativeShowFailed(NativeAd nativeAd) {

    }

    @Override
    public void onNativeClicked(NativeAd nativeAd) {

    }

    @Override
    public void onNativeExpired() {

    }
}
