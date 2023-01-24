package com.cyprien.kickunofficialapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class KickWebView {
    private final WebView KickWebView;
    private final String DefaultUserAgent;
    private final String ChromeUserAgent = "Chrome/56.0.0.0 Mobile";
    protected final KickWebViewClient KickWebViewClient; // protected else IDE is annoying

    public KickWebView(WebView webView) {
        this.KickWebView = webView;
        this.KickWebViewClient = new KickWebViewClient(this);
        this.KickWebView.setWebViewClient(this.KickWebViewClient);
        this.KickWebView.setWebChromeClient(new KickWebChromeClient(this.KickWebView));
        this.DefaultUserAgent = KickWebView.getSettings().getUserAgentString();
        this.SetupWebViewConfig();
        if (KickActivity.getKickActivity().getSavedInstanceState() == null)
            this.KickWebView.loadUrl(KickEndpoint.KickBase);
    }

    public void setChromeUserAgent() {
        this.KickWebView.getSettings().setUserAgentString(this.ChromeUserAgent);
    }

    public void setDefaultUserAgent() {
        this.KickWebView.getSettings().setUserAgentString(this.DefaultUserAgent);
    }

    public String getUserAgent() {
        return this.KickWebView.getSettings().getUserAgentString();
    }

    public String getDefaultUserAgent() {
        return this.DefaultUserAgent;
    }

    public String getChromeUserAgent() {
        return this.ChromeUserAgent;
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
    }
}
