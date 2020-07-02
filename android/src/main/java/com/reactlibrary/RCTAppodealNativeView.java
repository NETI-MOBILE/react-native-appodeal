package com.reactlibrary;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.BannerCallbacks;
import com.appodeal.ads.BannerView;
import com.appodeal.ads.MrecCallbacks;
import com.appodeal.ads.NativeAd;
import com.appodeal.ads.NativeAdView;
import com.appodeal.ads.NativeCallbacks;
import com.appodeal.ads.NativeIconView;
import com.appodeal.ads.NativeMediaView;
import com.appodeal.ads.native_ad.views.NativeAdViewAppWall;
import com.appodeal.ads.native_ad.views.NativeAdViewContentStream;
import com.appodeal.ads.native_ad.views.NativeAdViewNewsFeed;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.view.ReactViewGroup;

import java.util.List;

public class RCTAppodealNativeView extends LinearLayout implements NativeCallbacks {

    Context mContext;
    private NativeAdView nativeAdView;
    private NativeAdViewContentStream nativeAdViewContentStream;
    private int textColor = Color.parseColor("#FFFFFF");

    public RCTAppodealNativeView(ThemedReactContext context) {
        super(context);
        mContext = context;
        createView(context);
    }

    public void createView(Context context) {
        nativeAdView = (NativeAdView) LayoutInflater.from(this.getContext()).inflate(R.layout.native_ads,
                this, false);
        nativeAdViewContentStream = (NativeAdViewContentStream) nativeAdView.findViewById(R.id.native_ad_view_news_feed);
        showBannerView();
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        requestLayout();
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


    private int dp2px(int dp, DisplayMetrics dm) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, dm));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    private void showBannerView() {
        Resources r = getReactContext().getResources();
//        DisplayMetrics dm = r.getDisplayMetrics();
        List<NativeAd> loadedNativeAds = Appodeal.getNativeAds(5);
        if (loadedNativeAds.isEmpty()){
            return;
        }
//        int pxW = r.getDisplayMetrics().widthPixels;
//        int pxH = dp2px(300, dm);
        NativeAd nativeAd = loadedNativeAds.get(0);
        NativeAdView nativeAdV = fillNativeAd(nativeAd);
        addView(nativeAdV);
        Appodeal.show(getReactContext().getCurrentActivity(), Appodeal.NATIVE);

    }

    @Override
    public void onNativeLoaded() {
        Log.d("NativeAD", "success");
    }

    @Override
    public void onNativeFailedToLoad() {
        Log.d("NativeAD", "failed");

    }

    @Override
    public void onNativeShown(NativeAd nativeAd) {
        Log.d("NativeAD", "shown");

    }

    @Override
    public void onNativeShowFailed(NativeAd nativeAd) {
        Log.d("NativeAD", "showfailed");

    }

    @Override
    public void onNativeClicked(NativeAd nativeAd) {
        Log.d("NativeAD", "clicked");

    }

    @Override
    public void onNativeExpired() {
        Log.d("NativeAD", "expired");

    }

    public void setTextColor(String color) {
        this.textColor = Color.parseColor(color);
        if (nativeAdViewContentStream != null) {
            nativeAdViewContentStream.getDescriptionView().setTextColor(textColor);
            nativeAdViewContentStream.getTitleView().setTextColor(textColor);
        }
    }

    public void setMoreTextColor(String color) {
        if (nativeAdViewContentStream != null) {
            nativeAdViewContentStream.getCallToActionView().setTextColor(Color.parseColor(color));
        }
    }

    private NativeAdView fillNativeAd(NativeAd nativeAd) {
        nativeAdViewContentStream.setNativeAd(nativeAd);
        nativeAdViewContentStream.getDescriptionView().setTextColor(textColor);
        nativeAdViewContentStream.getTitleView().setTextColor(textColor);
        nativeAdViewContentStream.getCallToActionView().setTextColor(Color.parseColor("#009688"));
        nativeAdView.setVisibility(View.VISIBLE);
        return nativeAdView;
    }
}
