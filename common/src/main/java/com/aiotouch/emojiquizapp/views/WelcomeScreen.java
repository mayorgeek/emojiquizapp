package com.aiotouch.emojiquizapp.views;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

public class WelcomeScreen extends Form {

    public WelcomeScreen() {
        this.setTitle("Welcome Screen");
        this.setLayout(BoxLayout.y());
        addComponents();
    }

    private void addComponents() {
        Button helloButton = new Button("Hello Homepage");
        this.add(helloButton);

        helloButton.addActionListener(e -> hello());

        this.getToolbar().addMaterialCommandToSideMenu("Hello Command",
                FontImage.MATERIAL_CHECK, 4, e -> hello());
    }

    private void hello() {
        Dialog.show("Hello Codename One", "Welcome to Codename One", "OK", null);
    }

}
