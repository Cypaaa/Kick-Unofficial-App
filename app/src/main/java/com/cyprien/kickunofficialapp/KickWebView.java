package com.cyprien.kickunofficialapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;


public class KickWebView {
    private final WebView KickWebView;
    protected final KickWebViewClient KickWebViewClient; // protected else IDE is annoying
    public static final String KickUrl = "https://kick.com/";

    public KickWebView(WebView webView) {
        this.KickWebView = webView;
        this.KickWebViewClient = new KickWebViewClient();
        this.KickWebView.setWebViewClient(this.KickWebViewClient);
        this.KickWebView.setWebChromeClient(new KickWebChromeClient(this.KickWebView));
        this.SetupWebViewConfig();
        if (KickActivity.getKickActivity().getSavedInstanceState() == null)
            this.KickWebView.loadUrl(com.cyprien.kickunofficialapp.KickWebView.KickUrl);
    }

    public void goBackward() {
        this.KickWebView.goBack();
    }
    public void saveState(Bundle state) { this.KickWebView.saveState(state); }
    public void restoreState(Bundle state) { this.KickWebView.restoreState(state); }

    @SuppressLint("SetJavaScriptEnabled")
    public void SetupWebViewConfig() {
        WebSettings settings = KickWebView.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true); // XSS handled by the Kick.com
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        settings.setSupportMultipleWindows(false);
        settings.setBlockNetworkImage(false);
    }
}
