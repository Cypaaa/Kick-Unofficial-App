package com.cyprien.kickunofficialapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Toast;

public class KickActivity extends AppCompatActivity {
    private static KickActivity KickActivity;
    private KickWebView KickWebView;
    private Bundle SavedInstanceState;

    public static KickActivity getKickActivity() { return KickActivity; }

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


        // check if we have access to internet
        if (!KickNetworkManager.isOnline()) {
            this.NoInternet(); // write a toast
            this.finishAffinity(); // exit the app
            return;
        }

        this.IsFirstStart();


        // checking if the app is outdated
        new KickRequestGithubVersionTask().execute(KickEndpoint.GithubAppVersion);


        WebView webView = findViewById(R.id.kickwebviewelement);
        this.KickWebView = new KickWebView(webView);
    }

    // will make a KickCustomToast class in future updates
    public void NoInternet() {
        KickToast.Toast(KickActivity.getAppContext(), KickStaticText.NoInternet, Toast.LENGTH_LONG);
    }

    public void IsFirstStart() {
        final String key = "firststart";
        SharedPreferences settings = this.getSharedPreferences(this.getPackageName(), MODE_PRIVATE);
        if(settings.getBoolean(key, true)) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(key, false);
            editor.apply();
            KickToast.Toast(this, KickStaticText.FirstStart, 0);
        }
    }

    // if i press back, i want my app to show the previous page
    @Override
    public void onBackPressed() {
        this.KickWebView.goBackward();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle newState) {
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