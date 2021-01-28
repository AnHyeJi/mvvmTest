package com.and.mvvmtest.network;

import android.content.Context;
import android.util.Log;
import android.webkit.WebSettings;



/**
 * Created by MyCom on 2017-04-19.
 */

public class UserAgentManager {
    public static final String TAG = "UserAgentManager";

    public static final String id = "mlotte001";
    private static UserAgentManager instance = null;

    private String mDefaultUserAgent = null;
    private String mUserAgent = null;

    public static UserAgentManager getInstance() {
        if (instance == null) {
            instance = new UserAgentManager();
        }
        return instance;
    }

    public void generateUserAgent(Context context) {
        String defaultUserAgent = WebSettings.getDefaultUserAgent(context);

        mDefaultUserAgent = defaultUserAgent + getUserAgentExtra();

        mUserAgent = mDefaultUserAgent;
        Log.d(TAG, "mUserAgent : " + mUserAgent);
    }

    public String getDefaultUserAgent() {
        return mDefaultUserAgent;
    }

    public String getUserAgent() {
        return mUserAgent;
    }

    public String getUserAgentExtra() {
        return (" " + id + "/" +"");

    }
}
