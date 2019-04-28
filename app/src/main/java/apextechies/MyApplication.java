package apextechies;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // initialize the AdMob app
       // MobileAds.initialize(this, getString(R.string.admob_app_id));


    }
}