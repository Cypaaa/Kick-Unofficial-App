package com.cyprien.kickunofficialapp;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class KickNetworkManager {

    public static boolean hasInternet(KickActivity kickActivity) {
        boolean connected = false;
        try {
            Context appContext = kickActivity.getApplicationContext();
            ConnectivityManager connectivityManager = (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connected;
    }

    public static String fetchUrl(String url) {
        StringBuilder content = new StringBuilder();
        try {
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                content.append(inputLine);
            in.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //BuildConfig.VERSION_NAME
        return content.toString();
    }
}
