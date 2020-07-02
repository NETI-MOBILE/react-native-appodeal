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
    @ReactProp(name = "adSize")
    public void setSize(RCTAppodealBannerView view, String size) { view.setAdSize(size); }

    @Override
    public RCTAppodealNativeView createViewInstance(ThemedReactContext context) {
        RCTAppodealNativeView nativeView = new RCTAppodealNativeView(context);
        Appodeal.setNativeCallbacks(nativeView);
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
        return "RCTAppodealNativeView";
    }
}

