package com.aiotouch.emojiquizapp.views;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;

public class HowToPlayScreen extends Form {

    public HowToPlayScreen() {
        this.setLayout(BoxLayout.y());
        this.setScrollable(false);
        this.setUIID("HowToPlayScreen");

        // ADD BACK COMMAND
        Command backCmd = new Command("backCommand") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new WelcomeScreen().showBack();
            }
        };

        this.getToolbar().setBackCommand(backCmd);

        // ADD TOOLBAR TITLE
        this.getToolbar().setTitle("How to play?");
        this.getToolbar().setTitleCentered(true);
        this.getToolbar().getTitleComponent().setUIID("HowToPlayScreenToolbarTitle");

        addComponents();
    }

    private void addComponents() {
        SpanLabel instruction = new SpanLabel("Choose from the options provided, the correct name (or expression) of the emoji displayed on the screen.");
        instruction.setTextUIID("Instruction");

        // ADDING COMPONENTS TO THE FORM
        this.add(instruction);
    }

}
