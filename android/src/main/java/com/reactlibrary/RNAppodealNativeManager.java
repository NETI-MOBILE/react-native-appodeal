package com.reactlibrary;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.NativeAd;
import com.appodeal.ads.NativeCallbacks;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;


public class RNAppodealNativeManager extends SimpleViewManager<RCTAppodealNativeView> {
    public static final String PROP_TEXT_COLOR = "textColor";
    public static final String PROP_MORE_TEXT_COLOR = "textColorMore";
    private RCTAppodealNativeView nativeAdView;

    @ReactProp(name = PROP_TEXT_COLOR)
    public void setTextColor(RCTAppodealNativeView view, String color) {
        if (color != null) {
            nativeAdView.setTextColor(color);
        } else {
            nativeAdView.setTextColor("#FFFFFF");
        }
    }

    @ReactProp(name = PROP_MORE_TEXT_COLOR)
    public void setMoreTextColor(RCTAppodealNativeView view, String color) {
        if (color != null) {
            nativeAdView.setMoreTextColor(color);
        } else {
            nativeAdView.setMoreTextColor("#009688");
        }
    }

    @Override
    public RCTAppodealNativeView createViewInstance(ThemedReactContext context) {
        RCTAppodealNativeView nativeView = new RCTAppodealNativeView(context);
        Appodeal.setNativeCallbacks(nativeView);
        nativeAdView = nativeView;
        return nativeView;
    }

    @Override
    public void onDropViewInstance(@NonNull RCTAppodealNativeView view) {
        super.onDropViewInstance(view);
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                "onBannerLoaded",
                MapBuilder.of("registrationName", "onAdLoaded"),
                "onBannerFailedToLoad",
                MapBuilder.of("registrationName", "onAdFailedToLoad"),
                "onBannerClicked",
                MapBuilder.of("registrationName", "onAdClicked"),
                "onBannerExpired",
                MapBuilder.of("registrationName", "onAdExpired")
        );
    }

    @Override
    public String getName() {
        return "RNAppodealNativeView";
    }
}

