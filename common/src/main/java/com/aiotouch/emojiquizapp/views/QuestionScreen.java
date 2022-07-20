package com.aiotouch.emojiquizapp.views;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class QuestionScreen extends Form {

    public QuestionScreen() {
        this.setLayout(BoxLayout.y());
        this.setScrollable(false);
        this.setUIID("QuestionScreen");

        this.getToolbar().hideToolbar();    // HIDE THE TOOLBAR

        addComponents();
    }

    private void addComponents() {
        /* TOP CONATINER SECTION */
        Container topContainer = new Container(new BorderLayout());
        topContainer.setScrollableX(false);
        topContainer.setUIID("TopContainer");

        // QUESTION PROGRESS COMPONENT IN TOPCONTAINER
        Label questionProgress = new Label();
        questionProgress.setText("Question 1/30");
        questionProgress.setUIID("QuestionProgress");

        // SCORE LABEL COMPONENT IN TOPCONTAINER
        Label scoreLabel = new Label();
        scoreLabel.setText("Score: 30");
        scoreLabel.setUIID("ScoreLabel");

        // ADD QUESTION PROGRESS AND SCORE LABEL TO TOPCONTAINER
        topContainer.add(BorderLayout.WEST, questionProgress);
        topContainer.add(BorderLayout.EAST, scoreLabel);


        /* EMOJI IMAGE SECTION */
        Label questionImageContainer = new Label();
        Image questionImage = Resources.getGlobalResources().getImage("face-with-tears-of-joy-emoji.png");
        questionImage = questionImage.scaled(500, 500);
        questionImageContainer.setIcon(questionImage);
        questionImageContainer.setUIID("QuestionImageContainer");

        /* OPTIONS SECTION */
        Container optionContainer = new Container(BoxLayout.y());
        optionContainer.setUIID("OptionContainer");//

        RadioButton option1 = new RadioButton("Face with tears of joy");
        option1.setUIID("Option1");

        RadioButton option2 = new RadioButton("Something here");
        option2.setUIID("Option2");

        RadioButton option3 = new RadioButton("Nothing Here");
        option3.setUIID("Option3");

        optionContainer.add(option1).add(option2).add(option3);
        new ButtonGroup(option1, option2, option3);

        /* BOTTOM CONTAINER SECTION */
        Container bottomContainer = new Container(new BorderLayout());
        bottomContainer.setUIID("BottomContainer");

        Button quitButton = new Button("Quit");
        quitButton.setUIID("QuitButton");

        Button nextButton = new Button();
        nextButton.setUIID("NextButton");
        nextButton.setMaterialIcon(FontImage.MATERIAL_ARROW_FORWARD, 5);

        bottomContainer.add(BorderLayout.WEST, quitButton);
        bottomContainer.add(BorderLayout.EAST, nextButton);


        // ADDING COMPONENTS TO THE FORM
        this.add(topContainer);
        this.add(questionImageContainer);
        this.add(optionContainer);
        this.add(bottomContainer);
    }

}
