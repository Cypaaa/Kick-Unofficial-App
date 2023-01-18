package com.cyprien.kickunofficialapp;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class KickAssetManager {
    public static String getFileContent(String filename) {
        String json = null;
        try {
            InputStream is = KickActivity.getKickActivity().getAppAssetManager().open(filename);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}