package com.gaba.debloater;

import java.util.ArrayList;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XposedBridge;

public class HookEntry implements IXposedHookZygoteInit {

    @Override
    public void initZygote(StartupParam startupParam) {

        ArrayList<String> bloat = new ArrayList<>();

        // === GOOGLE ===
        bloat.add("com.android.chrome");
        bloat.add("com.google.android.hotwordenrollment.okgoogle");
        bloat.add("com.google.android.hotwordenrollment.xgoogle");
        bloat.add("com.google.android.syncadapters.calendar");
        bloat.add("com.google.android.syncadapters.contacts");
        bloat.add("com.google.android.gms.location.history");
        bloat.add("com.google.android.apps.playauto.installconfig");
        bloat.add("com.android.egg");

        // === SAMSUNG THEMES / STICKERS / AR ===
        bloat.add("com.samsung.android.themestore");
        bloat.add("com.samsung.android.theme.service");
        bloat.add("com.samsung.android.stickercenter");
        bloat.add("com.samsung.android.arzone.croco");
        bloat.add("com.samsung.android.livestickers");
        bloat.add("com.samsung.android.app.livemessage");

        // === CAMERA EXTRA (НЕ основная камера) ===
        bloat.add("com.samsung.android.app.singletake");
        bloat.add("com.samsung.android.motionphotoviewer");
        bloat.add("com.samsung.app.videoeditorlite");
        bloat.add("com.samsung.android.videotrimmer");

        // === SAMSUNG BLOATED SERVICES ===
        bloat.add("com.samsung.android.sm");
        bloat.add("com.samsung.android.sm.provider");
        bloat.add("com.hiya.star");
        bloat.add("com.samsung.android.app.spage");
        bloat.add("com.samsung.android.smartfaceservice");
        bloat.add("com.samsung.android.intelligentfpsadjuster");
        bloat.add("com.sec.android.silentlog");
        bloat.add("com.samsung.android.location");
        bloat.add("com.samsung.android.kgclient");
        bloat.add("com.samsung.android.spcmclient");
        bloat.add("com.samsung.android.easysetup");
        bloat.add("com.samsung.android.mdx.kit");

        // === WIDGETS / WEATHER / WALLPAPERS ===
        bloat.add("com.samsung.android.weather");
        bloat.add("com.samsung.android.smartwifi");
        bloat.add("com.samsung.android.wallpaper.res");
        bloat.add("com.samsung.android.aircommandmanager");
        bloat.add("com.samsung.android.app.aircommand");
        bloat.add("com.samsung.android.app.airviewdictionary");
        bloat.add("com.samsung.android.widgetapp.yahooedge.finance");
        bloat.add("com.samsung.android.widgetapp.yahooedge.sport");

        // === PHOTO EDITORS ===
        bloat.add("com.sec.android.mimage.photoretouching");
        bloat.add("com.samsung.android.photoeditor");

        // === TEST / FACTORY APPS ===
        bloat.add("com.sec.android.app.cameralyzer");
        bloat.add("com.sec.factory");
        bloat.add("com.sec.factory.camera");
        bloat.add("com.sec.factory.app.ui");
        bloat.add("com.sec.factory.health");
        bloat.add("com.sec.device.test");
        bloat.add("com.sec.android.app.factorytest");
        bloat.add("com.sec.epdgtestapp");

        // === EXTRA BLOATED ===
        bloat.add("com.samsung.android.service.peoplestripe");
        bloat.add("com.samsung.android.service.aircommand");
        bloat.add("com.samsung.android.app.routines");
        bloat.add("com.samsung.android.mateagent");
        bloat.add("com.samsung.android.visionintelligence");
        bloat.add("com.samsung.android.da.daagent");
        bloat.add("com.samsung.android.drivelink.stub");

        for (String pkg : bloat) {
            XposedBridge.log("Debloater blocking: " + pkg);
        }
    }
}
