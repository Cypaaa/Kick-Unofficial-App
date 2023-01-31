package com.cyprien.kickunofficialapp;

import android.os.AsyncTask;

class KickFetchUrlTask extends AsyncTask<String, String, String> {
    public KickFetchUrlTask() {
    }

    public void Callback(String result) {
    }

    @Override
    protected String doInBackground(String... uri) {
        String version = "";
        if (uri.length > 0)
            version = KickNetworkManager.fetchUrl(uri[0]);
        return version;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        this.Callback(result);
    }
}
