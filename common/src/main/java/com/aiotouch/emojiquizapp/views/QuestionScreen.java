package com.aiotouch.emojiquizapp.views;

import com.aiotouch.emojiquizapp.controllers.QuestionScreenController;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class QuestionScreen extends Form {

    public QuestionScreenController controller;
    public Label questionImageContainer;
    public Image questionImage;
    public ButtonGroup optionGroup;
    public RadioButton option1;
    public RadioButton option2;
    public RadioButton option3;
    public Button quitButton;
    public Button nextButton;
    public Label scoreLabel;
    public Label questionProgress;

    public QuestionScreen() {
        this.setLayout(BoxLayout.y());
        this.setUIID("QuestionScreen");

        this.getToolbar().hideToolbar();    // HIDE THE TOOLBAR

        addComponents();
        this.controller = new QuestionScreenController(this);
        setListeners();
    }

    private void addComponents() {
        /* TOP CONATINER SECTION */
        Container topContainer = new Container(new BorderLayout());
        topContainer.setScrollableX(false);
        topContainer.setUIID("TopContainer");

        // QUESTION PROGRESS COMPONENT IN TOPCONTAINER
        this.questionProgress = new Label();
        this.questionProgress.setUIID("QuestionProgress");

        // SCORE LABEL COMPONENT IN TOPCONTAINER
        this.scoreLabel = new Label();
        this.scoreLabel.setUIID("ScoreLabel");

        // ADD QUESTION PROGRESS AND SCORE LABEL TO TOPCONTAINER
        topContainer.add(BorderLayout.WEST, this.questionProgress);
        topContainer.add(BorderLayout.EAST, this.scoreLabel);


        /* EMOJI IMAGE SECTION */
        this.questionImageContainer = new Label();
        this.questionImage = Resources.getGlobalResources().getImage("placeholder.png");
        this.questionImage = this.questionImage.scaled(520, 520);
        questionImageContainer.setIcon(questionImage);
        questionImageContainer.setUIID("QuestionImageContainer");

        /* OPTIONS SECTION */
        Container optionContainer = new Container(BoxLayout.y());
        optionContainer.setUIID("OptionContainer");

        this.option1 = new RadioButton();
        this.option1.setUIID("Option1");

        this.option2 = new RadioButton();
        this.option2.setUIID("Option2");

        this.option3 = new RadioButton();
        this.option3.setUIID("Option3");

        optionContainer.add(option1).add(option2).add(option3);
        this.optionGroup = new ButtonGroup(option1, option2, option3);

        /* BOTTOM CONTAINER SECTION */
        Container bottomContainer = new Container(new BorderLayout());
        bottomContainer.setUIID("BottomContainer");

        this.quitButton = new Button("Quit");
        this.quitButton.setUIID("QuitButton");

        this.nextButton = new Button();
        this.nextButton.setEnabled(false);
        this.nextButton.setUIID("NextButton");
        this.nextButton.setMaterialIcon(FontImage.MATERIAL_ARROW_FORWARD, 5);

        bottomContainer.add(BorderLayout.WEST, this.quitButton);
        bottomContainer.add(BorderLayout.EAST, this.nextButton);


        // ADDING COMPONENTS TO THE FORM
        this.add(topContainer);
        this.add(questionImageContainer);
        this.add(optionContainer);
        this.add(bottomContainer);
    }

    public void setListeners() {
        this.optionGroup.addActionListener(controller);

        this.nextButton.addActionListener(controller);
        this.quitButton.addActionListener(controller);
    }

    public void exitScreen() {
        this.showBack();
    }

}
