package com.cyprien.kickunofficialapp;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;

public class KickWebChromeClient extends WebChromeClient {
    private WebView webView;
    private View view;

    public KickWebChromeClient(WebView webView) {
        this.webView = webView;
    }

    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
        FrameLayout frameLayout = (FrameLayout)KickActivity.getKickActivity().getAppWindow().getDecorView();
        this.webView.setVisibility(View.GONE);
        if(this.view != null)
            frameLayout.removeView(this.view);
        this.view = view;
        frameLayout.addView(this.view, new FrameLayout.LayoutParams(-1, -1));
        this.view.setVisibility(View.VISIBLE);
        KickActivity.getKickActivity().setLandscape();
    }

    @Override
    public void onHideCustomView() {
        this.webView.setVisibility(View.VISIBLE);
        this.view.setVisibility(View.GONE);
        KickActivity.getKickActivity().setPortrait();
    }
}