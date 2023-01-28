package com.cyprien.kickunofficialapp;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

class KickRequestGithubVersionTask extends AsyncTask<String, String, String> {
    @SuppressLint("StaticFieldLeak")
    private final KickActivity KickActivity;

    public KickRequestGithubVersionTask(KickActivity kickActivity) {
        this.KickActivity = kickActivity;
    }

    @Override
    protected String doInBackground(String... uri) {
        String version = "";
        if (uri.length > 0)
            version = KickNetworkManager.getGithubVersion(uri[0]);
        return version;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try {
            int githubVersion = Integer.parseInt(result);
            if (BuildConfig.VERSION_CODE < githubVersion) {
                KickToast.Toast(this.KickActivity, KickStaticText.Outdated, 1);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
