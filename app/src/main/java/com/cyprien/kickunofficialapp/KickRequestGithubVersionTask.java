package com.cyprien.kickunofficialapp;

import android.os.AsyncTask;

class KickRequestGithubVersionTask extends AsyncTask<String, String, String> {

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
            double githubVersion = Double.parseDouble(result);
            double appVersion = Double.parseDouble(BuildConfig.VERSION_NAME);
            if (appVersion < githubVersion) {
                KickToast.Toast(KickActivity.getKickActivity(), KickStaticText.Outdated, 1);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
