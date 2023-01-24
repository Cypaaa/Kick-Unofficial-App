package com.cyprien.kickunofficialapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.regex.Pattern;


public class KickWebView {
    private final WebView KickWebView;
    private final KickWebChromeClient KickWebChromeClient;
    private final String DefaultUserAgent;
    private final String ChromeUserAgent = "Chrome/56.0.0.0 Mobile";
    private final KickWebViewClient KickWebViewClient; // has no method for now

    public KickWebView(WebView webView) {
        this.KickWebView = webView;
        this.KickWebViewClient = new KickWebViewClient(this);
        this.KickWebView.setWebViewClient(this.KickWebViewClient);
        this.KickWebChromeClient = new KickWebChromeClient(this.KickWebView);
        this.KickWebView.setWebChromeClient(this.KickWebChromeClient);
        this.DefaultUserAgent = KickWebView.getSettings().getUserAgentString();
        this.SetupWebViewConfig();
        if (KickActivity.getKickActivity().getSavedInstanceState() == null)
            this.KickWebView.loadUrl(KickEndpoint.KickBase);
    }

    public KickWebViewClient getKickWebViewClient() {return this.KickWebViewClient; }
    public KickWebChromeClient getKickWebChromeClient() { return this.KickWebChromeClient;}
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
    public String getUrl() { return this.KickWebView.getUrl(); }
    // Returns if the url is a stream
    public boolean isUrlStream() {
        String url = this.getUrl();
        boolean stream = Pattern.matches(KickEndpoint.KickBase + "[_a-zA-Z0-9]{3,50}", url);
        return (stream
            && !url.equals(KickEndpoint.KickFollowing)
            && !url.equals(KickEndpoint.KickProfile)
            && !url.equals(KickEndpoint.KickCategories));
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
