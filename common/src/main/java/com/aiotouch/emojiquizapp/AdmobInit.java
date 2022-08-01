package com.aiotouch.emojiquizapp;

import com.codename1.admob.AdMobManager;
import com.codename1.ui.Display;

public class AdmobInit {

    public AdMobManager admob;

    public AdmobInit() {

        String admobId = "ca-app-pub-3940256099942544/1033173712";
        if (Display.getInstance().getPlatformName().equals("ios")) {
            admobId = "ca-app-pub-3940256099942544/4411468910";
        }

        admob = new AdMobManager(admobId);

    }

}
