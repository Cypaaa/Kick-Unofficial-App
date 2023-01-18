package com.cyprien.kickunofficialapp;

import android.content.Context;
import android.widget.Toast;

public class KickToast {
    public static void Toast(Context context, String text, int duration) {
        Toast toast = new Toast(context);
        toast.setText(text);
        toast.setDuration(duration);
        toast.show();
    }
}
