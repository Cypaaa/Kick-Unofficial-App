package com.cyprien.kickunofficialapp;

import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

// for future control of the webview
public class Kick {

    private WebView KickWebView;
    private KickCustomWebViewClient KickCustomWebViewClient;
    public static final String KickUrl = "https://kick.com/";

    public Kick(WebView webView) throws Exception {
        if (!KickNetworkManager.isOnline())
            throw new Exception("No Internet connection found.");

        this.KickWebView = webView;
        this.KickCustomWebViewClient = new KickCustomWebViewClient();
        this.KickWebView.setWebViewClient(this.KickCustomWebViewClient);
        this.Config();
        this.KickWebView.loadUrl(Kick.KickUrl);
    }

    public void Config() {
        WebSettings settings = KickWebView.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
    }
}
