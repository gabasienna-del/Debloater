package com.laibandis.gaba.debloater;

import org.json.JSONObject;
import org.json.JSONArray;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XposedBridge;

public class Utils {

    private static List<String> block = new ArrayList<>();
    private static List<String> whitelist = new ArrayList<>();

    public static void loadConfig() {
        try {
            InputStream is = Utils.class.getClassLoader().getResourceAsStream("assets/config.json");
            if (is == null) return;

            byte[] buf = new byte[is.available()];
            is.read(buf);

            JSONObject json = new JSONObject(new String(buf));

            JSONArray b = json.getJSONArray("block_packages");
            block.clear();
            for (int i = 0; i < b.length(); i++)
                block.add(b.getString(i));

            JSONArray w = json.getJSONArray("whitelist_packages");
            whitelist.clear();
            for (int i = 0; i < w.length(); i++)
                whitelist.add(w.getString(i));

            XposedBridge.log("Debloater config loaded.");
        } catch (Exception e) {
            XposedBridge.log("Debloater config load failed: " + e);
        }
    }

    public static boolean isBlocked(String pkg) {
        return block.contains(pkg);
    }

    public static boolean isProtected(String pkg) {
        return whitelist.contains(pkg);
    }
}
