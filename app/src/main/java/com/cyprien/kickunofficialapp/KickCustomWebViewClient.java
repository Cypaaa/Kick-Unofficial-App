package com.cyprien.kickunofficialapp;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class KickCustomWebViewClient extends WebViewClient {
    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading (WebView view, String url){
        if (url.startsWith(Kick.KickUrl))
            view.loadUrl(url);
        return true;
    }
}
