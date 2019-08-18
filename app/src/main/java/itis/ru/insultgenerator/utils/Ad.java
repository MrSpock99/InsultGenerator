package itis.ru.insultgenerator.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.*;
import itis.ru.insultgenerator.R;

public abstract class Ad {
    private static final String APP_PREFERENCES = "app_preferences";
    private static final String KEY = "count";


    public static void showBannerAd(Activity rootView) {
        AdView adView = null;

        adView = rootView.findViewById(R.id.main_adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        final AdView finalAdView = adView;

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.d("MyLog","AAAAAAAAAAAA");
                finalAdView.setVisibility(View.VISIBLE);
            }
        });

    }

    public static InterstitialAd getInterstitial(Context context) {
        InterstitialAd interstitialAd = new InterstitialAd(context);

        interstitialAd.setAdUnitId("ca-app-pub-2471262936820592/7908163815");

        interstitialAd.loadAd(new AdRequest.Builder().build());

        return interstitialAd;
    }

    public static void inc(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        long count = System.currentTimeMillis();

        Log.d("Log", "Inc count = " + count);

        editor.putLong(KEY, count);
        editor.apply();

    }

    public static boolean hasConnection(Activity activity) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
