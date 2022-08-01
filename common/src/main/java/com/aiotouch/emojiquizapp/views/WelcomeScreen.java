package com.aiotouch.emojiquizapp.views;

import com.codename1.components.ShareButton;
import com.codename1.components.ToastBar;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import org.littlemonkey.connectivity.Connectivity;

public class WelcomeScreen extends Form {
    public WelcomeScreen() {
        this.getToolbar().hideToolbar();
        this.setLayout(BoxLayout.y());
        this.setUIID("WelcomeScreen");
        addComponents();
    }

    private void addComponents() {
        /* WRAPPER FOR ALL COMPONENTS IN THIS FORM */
        Container pageWrapper = new Container(BoxLayout.y());
        pageWrapper.setUIID("PageWrapper");


        /* HERO IMAGE SECTION */
        Label welcomeImageContainer = new Label();
        Image welcomeScreenImage = Resources.getGlobalResources().getImage("welcomeScreenImage.png");
        welcomeScreenImage = welcomeScreenImage.scaledHeight(850);  // INCREASE IMAGE HEIGHT
        welcomeImageContainer.setIcon(welcomeScreenImage);
        welcomeImageContainer.setUIID("WelcomeImageContainer");

        /* ADD BUTTONS TO WELCOME SCREEN */
        Button startButton = new Button("Start Quiz");
        startButton.setMaterialIcon(FontImage.MATERIAL_PLAY_CIRCLE_FILLED, 8);
        startButton.setGap(48);
        startButton.setUIID("StartButton");
        startButton.addActionListener(e -> {
            /* CHECK USER'S CONNECTION */
            if (!Connectivity.isConnected()) {
                ToastBar.Status noInternet = ToastBar.showErrorMessage("No Internet Connection!", 5000);
                return;
            }

            QuestionScreen questionScreen = new QuestionScreen();
            questionScreen.show();
        });

        Button howToPlayButton = new Button("How to play?");
        howToPlayButton.setMaterialIcon(FontImage.MATERIAL_HELP_OUTLINE, 8);
        howToPlayButton.setGap(48);
        howToPlayButton.setUIID("HowToPlayButton");
        howToPlayButton.addActionListener(e -> {
            HowToPlayScreen howToPlayScreen = new HowToPlayScreen();
            howToPlayScreen.show();
        });

        ShareButton shareAppButton = new ShareButton();
        shareAppButton.setText("Share this app");
        shareAppButton.setTextToShare("Hey friend! \n You definitely use emojis every day. \n But do you know what they really mean? \n Find out by downloading this app!");
        shareAppButton.setMaterialIcon(FontImage.MATERIAL_SHARE, 6);
        shareAppButton.setGap(48);
        shareAppButton.setUIID("ShareAppButton");


        // ADDING COMPONENTS TO THE FORM
        pageWrapper.add(welcomeImageContainer);
        pageWrapper.add(startButton);
        pageWrapper.add(howToPlayButton);
        pageWrapper.add(shareAppButton);

        this.add(pageWrapper);  // ADD PAGE WRAPPER TO THE FORM
    }

}
