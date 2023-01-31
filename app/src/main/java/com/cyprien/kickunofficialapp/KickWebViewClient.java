package com.cyprien.kickunofficialapp;


import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Objects;


public class KickWebViewClient extends WebViewClient {
    private final KickWebView KickWebView;
    private KickActivity KickActivity;
    public KickWebViewClient(KickActivity kickActivity, KickWebView kickWebView) {
        this.KickActivity = kickActivity;
        this.KickWebView = kickWebView;
    }
    private final String[] AllowedUrls = new String[] {
            // kick base url
            KickEndpoint.KickBase,
            // Google login service, all its redirections
            KickEndpoint.GoogleAuth // .com .co.uk .fr .be ...
    };

    // just in case
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        this.UrlLoader(view, url);
        return true; // cancel the current loading url
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        this.UrlLoader(view, request.getUrl().toString());
        return true;
    }

    private void UrlLoader(WebView view, String url) {
        if (KickString.ArrayItemIsStartsOf(AllowedUrls, url)) {
            this.setCustomUserAgent(url);
            // stops current loading <- can cause login to bug ??
            this.KickWebView.getKickWebView().stopLoading();
            view.loadUrl(url);
        } else {
            this.KickActivity.OpenUrl(url);
        }
    }

    private void setCustomUserAgent(String url) {
        if (url.startsWith(KickEndpoint.GoogleAuth) && !Objects.equals(this.KickWebView.getUserAgent(), this.KickWebView.getChromeUserAgent()))
            this.KickWebView.setChromeUserAgent();
        else if (url.startsWith(KickEndpoint.KickBase) && !Objects.equals(this.KickWebView.getUserAgent(), this.KickWebView.getDefaultUserAgent()))
            this.KickWebView.setDefaultUserAgent();
    }
}
