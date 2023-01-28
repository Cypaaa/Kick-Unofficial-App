package com.cyprien.kickunofficialapp;


import android.annotation.SuppressLint;
import android.app.PictureInPictureParams;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Rational;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

// start foreground service to keep the sound on when the screen is sleeping

public class KickActivity extends AppCompatActivity {
    private KickWebView KickWebView;
    private Bundle SavedInstanceState;

    public Bundle getSavedInstanceState() { return this.SavedInstanceState; }
    public void setLandscape() { this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); }
    @SuppressLint("SourceLockedOrientationActivity")
    public void setPortrait() { this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.SavedInstanceState = savedInstanceState;
        setContentView(R.layout.activity_main);

        // prevent screen from going to sleep
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // check if we have access to internet
        this.CheckInternet();
        // Checking if it's the first time the user opens the app
        this.IsFirstStart();

        // configure the WebView
        WebView webView = findViewById(R.id.kickwebviewelement);
        this.KickWebView = new KickWebView(this, webView);

        // checking if the app is outdated
        new KickRequestGithubVersionTask(this).execute(KickEndpoint.GithubAppVersion);

    }

    // will make a KickCustomToast class in future updates
    public boolean CheckInternet() {
        if (!KickNetworkManager.isOnline(this)) {
            KickToast.Toast(this, KickStaticText.NoInternet, Toast.LENGTH_LONG);
            this.finishAffinity(); // exit the app
            return true;
        }
        return false;
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

    public void OpenUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.getApplicationContext().startActivity(intent);
    }

    /*
    public void Test() {
        FrameLayout frameLayout = (FrameLayout)this.getWindow().getDecorView();
        View view = this.getWindow().getDecorView().getRootView();
        WebView webView = this.KickWebView.getKickWebView();
        webView.setVisibility(View.GONE);
        //if(view != null)
        //    frameLayout.removeView(view);
        //frameLayout.addView(view, new FrameLayout.LayoutParams(-1, -1));
        view.setVisibility(View.VISIBLE);
        this.EnterPiP(16, 9);
    }
    */

    private void EnterPiP(int width, int height) {
        PictureInPictureParams pipParams = new PictureInPictureParams.Builder()
                .setAspectRatio(new Rational(width, height))
                .build();
        enterPictureInPictureMode(pipParams);
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

    @Override
    public void onUserLeaveHint() {
        // if Fullscreen -> we already are looking at a stream
        KickWebChromeClient kickWebChromeClient = this.KickWebView.getKickWebChromeClient();
        if (kickWebChromeClient.getFullscreen())
            this.EnterPiP(kickWebChromeClient.getWidth(), kickWebChromeClient.getHeight());
        else if (this.KickWebView.isUrlStream()) {
            //this.Test();
            KickToast.Toast(this, KickStaticText.PiP, 1);
        }
    }
}