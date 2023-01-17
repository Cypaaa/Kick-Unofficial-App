package com.cyprien.kickunofficialapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static Context AppContext = null;
    private Kick KickObject; // for future devs

    public static Context getAppContext() { return MainActivity.AppContext; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.AppContext = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            WebView webView = findViewById(R.id.kickwebviewelement);
            this.KickObject = new Kick(webView);
        } catch (Exception e) {
            this.NoInternet(e);
            this.finishAffinity();
        }
    }

    public void NoInternet(Exception e) {
        Toast toast = new Toast(MainActivity.getAppContext());
        toast.setText(e.getMessage() + "\nConnect to internet to use Kick.");
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}