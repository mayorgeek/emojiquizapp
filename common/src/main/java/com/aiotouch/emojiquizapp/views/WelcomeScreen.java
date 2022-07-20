package com.aiotouch.emojiquizapp.views;

import com.codename1.ui.*;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class WelcomeScreen extends Form {

    public WelcomeScreen() {
        this.getToolbar().hideToolbar();
        this.setLayout(BoxLayout.y());
        this.setScrollable(false);
        this.setUIID("WelcomeScreen");
        addComponents();
    }

    private void addComponents() {
        /* WRAPPER FOR ALL COMPONENTS IN THIS FORM */
        Container pageWrapper = new Container(BoxLayout.y());
        // SET PAGE WRAPPER SIZE TO FILL THE WHOLE FORM SPACE
//        pageWrapper.setSize(new Dimension(pageWrapper.getParent().getWidth(), pageWrapper.getParent().getHeight()));
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

        Button shareAppButton = new Button("Share this app");
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
