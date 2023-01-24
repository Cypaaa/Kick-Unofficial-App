package com.cyprien.kickunofficialapp;


import android.content.Intent;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Objects;


public class KickWebViewClient extends WebViewClient {
    private final KickWebView KickWebView;
    public KickWebViewClient(KickWebView kickWebView) {
        this.KickWebView = kickWebView;
    }
    private final String[] AllowedUrls = new String[] {
            // kick base url
            KickEndpoint.KickBase,
            // Google login service, all its redirections
            KickEndpoint.GoogleAuth // .com .co.uk .fr .be ...
    };

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
            if (url.startsWith(KickEndpoint.GoogleAuth) && !Objects.equals(this.KickWebView.getUserAgent(), this.KickWebView.getChromeUserAgent()))
                this.KickWebView.setChromeUserAgent();
            else if (url.startsWith(KickEndpoint.KickBase) && !Objects.equals(this.KickWebView.getUserAgent(), this.KickWebView.getDefaultUserAgent()))
                this.KickWebView.setDefaultUserAgent();
            view.loadUrl(url);
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            KickActivity.getKickActivity().getAppContext().startActivity(intent);
        }
    }
}
