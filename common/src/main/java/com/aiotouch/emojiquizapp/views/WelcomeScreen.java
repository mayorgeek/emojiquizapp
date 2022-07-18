package com.aiotouch.emojiquizapp.views;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

import java.io.IOException;

public class WelcomeScreen extends Form {

    public WelcomeScreen() {
        this.getToolbar().hideToolbar();
        this.setLayout(BoxLayout.y());
        this.setScrollableY(false);
        this.setUIID("WelcomeScreen");
        addComponents();
    }

    private void addComponents() {
        Label welcomeImageContainer = new Label();
        Image welcomeScreenImage = Resources.getGlobalResources().getImage("welcomeScreenImage.png");
        welcomeScreenImage = welcomeScreenImage.scaledHeight(850);  // INCREASE IMAGE HEIGHT
        welcomeImageContainer.setIcon(welcomeScreenImage);
        welcomeImageContainer.setUIID("WelcomeImageContainer");

        // ADD BUTTONS TO WELCOME SCREEN
        Button startButton = new Button("Start Quiz");
        startButton.setMaterialIcon(FontImage.MATERIAL_PLAY_CIRCLE_FILLED, 8);
        startButton.setGap(48);
        startButton.setUIID("StartButton");

        Button howToPlayButton = new Button("How to play?");
        howToPlayButton.setMaterialIcon(FontImage.MATERIAL_HELP_OUTLINE, 8);
        howToPlayButton.setGap(48);
        howToPlayButton.setUIID("HowToPlayButton");

        Button shareAppButton = new Button("Share this app");
        shareAppButton.setMaterialIcon(FontImage.MATERIAL_SHARE, 6);
        shareAppButton.setGap(48);
        shareAppButton.setUIID("ShareAppButton");

        this.add(welcomeImageContainer);
        this.add(startButton);
        this.add(howToPlayButton);
        this.add(shareAppButton);
    }

}
