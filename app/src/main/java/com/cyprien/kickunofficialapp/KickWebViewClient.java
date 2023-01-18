package com.cyprien.kickunofficialapp;


import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class KickWebViewClient extends WebViewClient {
    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading (WebView view, String url){
        if (url.startsWith(KickWebView.KickUrl))
            view.loadUrl(url);
        else {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            KickActivity.getKickActivity().getAppContext().startActivity(intent);
        }
        return true; // cancel the current loading url
    }
}
