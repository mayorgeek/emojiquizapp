package com.aiotouch.emojiquizapp;

import com.codename1.admob.AdMobManager;
import com.codename1.ui.Display;

public class AdmobInit {

    public AdMobManager admob;

    public AdmobInit() {

        String admobId = "ca-app-pub-9641594885124360/9545273136";
        if (Display.getInstance().getPlatformName().equals("ios")) {
            admobId = "ca-app-pub-9641594885124360/9545273136";
        }

        admob = new AdMobManager(admobId);

    }

}
