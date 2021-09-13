package pdf.pdftool.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.facebook.ads.InterstitialAd;
import com.google.android.gms.ads.AdRequest;
import pdf.pdftool.R;

public class AdRunner {

    public com.google.android.gms.ads.InterstitialAd mInterstitialAd;
    public InterstitialAd wInterstitialAd;
    public static AdRunner adLoader;

    public static AdRunner getAds() {

        if (adLoader == null) {
            adLoader = new AdRunner();
        }
        return adLoader;
    }

    public void loadFullAdFacebook(Context context) {
        try {
            wInterstitialAd = new InterstitialAd(context, context.getResources().getString(R.string.interstial_ad));
            wInterstitialAd.loadAd();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadFullAdmob(Context context) {
        mInterstitialAd = new com.google.android.gms.ads.InterstitialAd(context);
        mInterstitialAd.setAdUnitId(context.getString(R.string.admob_unit_id));

        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
    public boolean showFBAds(Context context) {
        boolean isLoad = false;
        try {
            if (wInterstitialAd != null && wInterstitialAd.isAdLoaded()) {
                wInterstitialAd.show();
                isLoad = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadFullAdFacebook(context);

        return isLoad;

    }
    public void showAdmob(Activity activity) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
        loadFullAdmob(activity);
    }
    public void showFBFirst(Activity context) {

        try {
            if (!showFBAds(context)) {
                showAdmob(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
