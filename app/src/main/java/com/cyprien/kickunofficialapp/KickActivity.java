package com.cyprien.kickunofficialapp;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Toast;

public class KickActivity extends AppCompatActivity {
    private static KickActivity KickActivity;

    public static KickActivity getKickActivity() { return KickActivity; }

    private KickWebView KickWebView; // for future devs
    private Bundle SavedInstanceState;

    public Bundle getSavedInstanceState() { return this.SavedInstanceState; }
    public Context getAppContext() { return this.getApplicationContext(); }
    public AssetManager getAppAssetManager() { return this.getAppAssetManager(); }
    public Window getAppWindow() { return this.getWindow(); }
    public void setLandscape() { this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); }
    @SuppressLint("SourceLockedOrientationActivity")
    public void setPortrait() { this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KickActivity = this;
        this.SavedInstanceState = savedInstanceState;
        setContentView(R.layout.activity_main);


        if (!KickNetworkManager.isOnline()) {
            this.NoInternet(); // write a toast
            this.finishAffinity(); // exit the app
            return;
        }

        WebView webView = findViewById(R.id.kickwebviewelement);
        this.KickWebView = new KickWebView(webView);
    }

    // will make a KickCustomToast class in future updates
    public void NoInternet() {
        KickToast.Toast(KickActivity.getAppContext(), "No Internet connection found.\nConnect to internet to use Kick.", Toast.LENGTH_LONG);
    }

    // if i press back, i want my app to show the previous page
    @Override
    public void onBackPressed() {
        this.KickWebView.goBackward();
    }

    @Override
    protected void onSaveInstanceState(Bundle newState) {
        super.onSaveInstanceState(newState);
        this.SavedInstanceState = newState;
        this.KickWebView.saveState(newState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle oldState) {
        super.onRestoreInstanceState(oldState);
        this.SavedInstanceState = oldState;
        this.KickWebView.restoreState(oldState);
    }
}