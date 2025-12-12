package com.laibandis.gaba.debloater;

import org.json.JSONObject;
import org.json.JSONArray;

import java.util.Iterator;
import java.util.List;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookEntry implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpp) throws Throwable {
        if (!"android".equals(lpp.packageName)) return;

        XposedBridge.log("Debloater: PackageManager hook active");

        Utils.loadConfig();

        Class<?> pmService =
                XposedHelpers.findClass("com.android.server.pm.PackageManagerService", lpp.classLoader);

        XposedHelpers.findAndHookMethod(
                pmService,
                "getInstalledPackages",
                int.class,
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        List list = (List) param.getResult();
                        if (list == null) return;

                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            Object pkg = it.next();
                            String pname = (String) XposedHelpers.getObjectField(pkg, "packageName");

                            if (Utils.isProtected(pname)) continue;
                            if (Utils.isBlocked(pname)) {
                                XposedBridge.log("Debloater: hiding " + pname);
                                it.remove();
                            }
                        }
                        param.setResult(list);
                    }
                }
        );
    }
}
