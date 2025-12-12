package com.gaba.eskukap;

import static de.robv.android.xposed.XposedBridge.log;
import android.app.AndroidAppHelper;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_LoadPackage;
import de.robv.android.xposed.XposedBridge;

public class HookEntry implements IXposedHookLoadPackage {

    private static final List<String> BLOAT = Arrays.asList(
        "com.android.dreams.basic",
        "com.android.dreams.phototable",
        "com.android.egg",
        "com.android.hotwordenrollment.okgoogle",
        "com.android.hotwordenrollment.xgoogle",
        "com.android.wallpaper.livepicker",
        "com.android.wallpapercropper",
        "com.android.vpndialogs",
        "com.google.android.syncadapters.contacts",
        "com.google.android.syncadapters.calendar",
        "com.google.android.feedback",
        "com.google.mainline.telemetry",
        "com.google.android.partnersetup",
        "com.google.android.onetimeinitializer",
        "com.google.android.ext.services",
        "com.google.android.ext.shared",
        "com.google.android.apps.restore",
        "com.google.android.captiveportallogin",
        "com.google.android.networkstack.permissionconfig",
        "com.google.android.overlay.gmsconfig.common",
        "com.google.android.overlay.gmsconfig.gsa",
        "com.google.android.overlay.gmsconfig.photos",
        "com.google.android.overlay.modules.documentsui",
        "com.google.android.overlay.modules.permissioncontroller",
        "com.google.android.overlay.modules.permissioncontroller.forframework",
        "com.samsung.android.app.dressroom",
        "com.samsung.android.app.soundpicker",
        "com.samsung.android.app.earphonetypec",
        "com.samsung.android.app.aodservice",
        "com.samsung.android.applock",
        "com.samsung.android.biometrics.app.setting",
        "com.samsung.android.bluelightfilter",
        "com.samsung.android.brightnessbackupservice",
        "com.samsung.android.honeyboard",
        "com.samsung.android.lool",
        "com.samsung.android.mapsagent",
        "com.samsung.android.livestickers",
        "com.samsung.android.callbgprovider",
        "com.hiya.star",
        "com.microsoft.appmanager",
        "com.picsart.studio",
        "com.aurora.store",
        "com.chartcross.gpstest",
        "com.eclipsim.gpsstatus2",
        "com.arlosoft.macrodroid",
        "com.jana.tube.video"
    );

    private void writeList() {
        try {
            File f = new File("/sdcard/debloater_list.txt");
            FileWriter fw = new FileWriter(f, false);
            for (String pkg : BLOAT) fw.write(pkg + "\n");
            fw.close();
        } catch (Throwable t) {
            XposedBridge.log("Debloater write error: " + t);
        }
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        if (lpparam.packageName.equals("android")) {
            XposedBridge.log("Debloater: loaded android – writing package list");
            writeList();
        }

        if (BLOAT.contains(lpparam.packageName)) {
            XposedBridge.log("Debloater BLOCK: " + lpparam.packageName);
        }
    }
}
