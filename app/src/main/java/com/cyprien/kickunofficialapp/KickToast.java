package com.cyprien.kickunofficialapp;

import android.widget.Toast;

public class KickToast {
    public static void Toast(KickActivity kickActivity, String text, int duration) {
        Toast toast = new Toast(kickActivity.getApplicationContext());
        toast.setText(text);
        toast.setDuration(duration);
        toast.show();
    }
}
