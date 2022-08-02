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
        welcomeScreenImage = welcomeScreenImage.scaledHeight(600);  // INCREASE IMAGE HEIGHT
        welcomeImageContainer.setIcon(welcomeScreenImage);
        welcomeImageContainer.setUIID("WelcomeImageContainer");

        /* ADD BUTTONS TO WELCOME SCREEN */
        Button startButton = new Button("Start Quiz");
        startButton.setMaterialIcon(FontImage.MATERIAL_PLAY_CIRCLE_FILLED, 6);
        startButton.setGap(40);
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
        howToPlayButton.setMaterialIcon(FontImage.MATERIAL_HELP_OUTLINE, 6);
        howToPlayButton.setGap(40);
        howToPlayButton.setUIID("HowToPlayButton");
        howToPlayButton.addActionListener(e -> {
            HowToPlayScreen howToPlayScreen = new HowToPlayScreen();
            howToPlayScreen.show();
        });

        ShareButton shareAppButton = new ShareButton();
        shareAppButton.setText("Share this app");
        shareAppButton.setTextToShare("Hey friend! \n You definitely use emojis every day. \n But do you know what they really mean? \n Find out by downloading this app! \n\n https://emojiquizapp.netlify.app/");
        shareAppButton.setMaterialIcon(FontImage.MATERIAL_SHARE, 5);
        shareAppButton.setGap(40);
        shareAppButton.setUIID("ShareAppButton");


        // ADDING COMPONENTS TO THE FORM
        pageWrapper.add(welcomeImageContainer);
        pageWrapper.add(startButton);
        pageWrapper.add(howToPlayButton);
        pageWrapper.add(shareAppButton);

        this.add(pageWrapper);  // ADD PAGE WRAPPER TO THE FORM
    }

}
