package com.cyprien.kickunofficialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class KickActivity extends AppCompatActivity {
    private static Context AppContext = null;
    private static AssetManager AppAssetManager = null;
    private KickWebView KickWebView; // for future devs

    public static Context getAppContext() { return KickActivity.AppContext; }
    public static AssetManager getAppAssetManager() { return KickActivity.AppAssetManager; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        KickActivity.AppContext = getApplicationContext();
        KickActivity.AppAssetManager = getAppAssetManager();

        super.onCreate(savedInstanceState);
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
        Toast toast = new Toast(KickActivity.getAppContext());
        toast.setText("No Internet connection found.\nConnect to internet to use Kick.");
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }


    // if i press back, i want my app to show the previous page
    @Override
    public void onBackPressed() {
        this.KickWebView.goBackward();
    }
}