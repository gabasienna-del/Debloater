package com.gaba.debloater;

import java.util.ArrayList;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XposedBridge;

public class HookEntry implements IXposedHookZygoteInit {

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        ArrayList<String> bloat = new ArrayList<>();
        
        bloat.add("com.facebook.katana");
        bloat.add("com.facebook.appmanager");
        bloat.add("com.facebook.system");

        for (String pkg : bloat) {
            XposedBridge.log("Debloater blocking: " + pkg);
        }
    }
}
