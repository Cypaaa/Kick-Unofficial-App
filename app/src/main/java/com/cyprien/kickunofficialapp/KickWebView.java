package com.cyprien.kickunofficialapp;

import android.annotation.SuppressLint;
import android.webkit.WebSettings;
import android.webkit.WebView;

// for future control of the webview
public class KickWebView {

    private WebView KickWebView;
    private KickWebViewClient KickWebViewClient;
    public static final String KickUrl = "https://kick.com/";

    public KickWebView(WebView webView) {
        this.KickWebView = webView;
        this.KickWebViewClient = new KickWebViewClient();
        this.KickWebView.setWebViewClient(this.KickWebViewClient);
        this.Config();
        this.KickWebView.loadUrl(com.cyprien.kickunofficialapp.KickWebView.KickUrl);
    }

    public void goBackward() {
        this.KickWebView.goBack();
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void Config() {
        WebSettings settings = KickWebView.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true); // XSS handled by the Kick.com
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
    }
}
