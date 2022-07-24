package com.aiotouch.emojiquizapp.models;

public class Question {

    public String imageName;
    public String[] options;
    public String answer;

    public Question(String imageName, String[] options, String answer) {
        this.imageName = imageName;
        this.options = options;
        this.answer = answer;
    }
}
