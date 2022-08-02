package com.aiotouch.emojiquizapp.views;

import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

public class HowToPlayScreen extends Form {

    public HowToPlayScreen() {
        this.setLayout(BoxLayout.y());
        this.setScrollable(false);
        this.setUIID("HowToPlayScreen");

        getToolbar().hideToolbar();
        addComponents();
    }

    private void addComponents() {
        Container toolbar = new Container(BoxLayout.x());
        toolbar.setUIID("CustomToolbar");

        Button backIcon = new Button();
        backIcon.setMaterialIcon(FontImage.MATERIAL_ARROW_BACK, 4);
        backIcon.setUIID("BackIcon");
        backIcon.addActionListener(e -> {
            new WelcomeScreen().showBack();
        });

        Label toolbarTitle = new Label("How to play?");
        toolbarTitle.setUIID("HowToPlayScreenToolbarTitle");

        toolbar.add(backIcon).add(toolbarTitle);

        SpanLabel instruction = new SpanLabel("You are to choose from the options provided, the correct meaning of the emoji displayed on the screen.");
        instruction.setTextUIID("Instruction");

        // ADDING COMPONENTS TO THE FORM
        this.add(toolbar).add(instruction);
    }

}
