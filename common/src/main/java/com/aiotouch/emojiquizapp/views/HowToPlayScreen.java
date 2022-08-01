package com.aiotouch.emojiquizapp.views;

import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

public class HowToPlayScreen extends Form {

    public HowToPlayScreen() {
        this.setLayout(BoxLayout.y());
        this.setScrollable(false);
        this.setUIID("HowToPlayScreen");

        // ADD BACK COMMAND
//        Command backCmd = new Command("backCommand") {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                new WelcomeScreen().showBack();
//            }
//        };
//
//        this.getToolbar().setBackCommand(backCmd);
//
//        // ADD TOOLBAR TITLE
//        this.getToolbar().setTitle("How to play?");
//        this.getToolbar().setTitleCentered(true);
//        this.getToolbar().getTitleComponent().setUIID("HowToPlayScreenToolbarTitle");
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

        SpanLabel instruction = new SpanLabel("Choose from the options provided, the correct name (or expression) of the emoji displayed on the screen.");
        instruction.setTextUIID("Instruction");

        // ADDING COMPONENTS TO THE FORM
        this.add(toolbar).add(instruction);
    }

}
