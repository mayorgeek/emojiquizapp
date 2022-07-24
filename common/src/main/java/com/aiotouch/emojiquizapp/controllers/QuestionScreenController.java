package com.aiotouch.emojiquizapp.controllers;

import com.aiotouch.emojiquizapp.models.Question;
import com.aiotouch.emojiquizapp.repository.QuestionRepository;
import com.aiotouch.emojiquizapp.views.QuestionScreen;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.properties.Property;
import com.codename1.properties.PropertyBase;
import com.codename1.properties.UiBinding;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;

import java.util.ArrayList;
import java.util.List;

public class QuestionScreenController implements ActionListener<ActionEvent> {

    public QuestionScreen questionScreen;
    public String answer;
    public List<Question> questions;
    public String previousUIID;
    public int score;
    public int currentQuestion;

    public QuestionScreenController(QuestionScreen view) {
        this.questionScreen = view;
        this.questions = new QuestionRepository().getQuestions();
        initQuestion();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActualComponent().getUIID().equals("Option1") || e.getActualComponent().getUIID().equals("Option2") || e.getActualComponent().getUIID().equals("Option3"))
        {
            if (isAnswerCorrect()) {
                if (this.currentQuestion >= this.questions.size()) {
                    Dialog quizCompleteDialog = new Dialog("", new BorderLayout());
                    quizCompleteDialog.getContentPane().setUIID("DialogContentPane");


                    SpanLabel body = new SpanLabel("Congratulations! You have reached the end of the quiz...");
                    body.setUIID("DialogBody");

                    Button okButton = new Button("Ok");
                    okButton.setUIID("OkButton");
                    okButton.addActionListener(action -> this.questionScreen.showBack());

                    quizCompleteDialog.add(BorderLayout.NORTH, body).add(BorderLayout.SOUTH, okButton);

                    quizCompleteDialog.show();

//                    if (Dialog.show("", "Congratulations! You have reached the end of the quiz...", "OK", "")) {
//                        this.questionScreen.showBack();
//                    }
                }

                // SAVE RADIO BUTTON PREVIOUS STYLE
                this.previousUIID = this.questionScreen.optionGroup.getSelected().getUIID();

                // CHANGE BUTTON TO SUCCESS ANSWER LOOK
                this.questionScreen.optionGroup.getSelected().setUIID("CorrectAnswer");

                // INCREASE THE SCORE
                this.score += 2;
                this.questionScreen.scoreLabel.setText("Score " + this.score);

                // DISABLE THE BUTTONS
                this.questionScreen.option1.setEnabled(false);
                this.questionScreen.option2.setEnabled(false);
                this.questionScreen.option3.setEnabled(false);

                // ALLOW USER TO CLICK NEXT BUTTON
                this.questionScreen.nextButton.setEnabled(true);
            } else {
                // SAVE RADIO BUTTON PREVIOUS STYLE
                this.previousUIID = this.questionScreen.optionGroup.getSelected().getUIID();

                // CHANGE BUTTON TO WRONG ANSWER LOOK
                this.questionScreen.optionGroup.getSelected().setUIID("WrongAnswer");

                // DISABLE THE BUTTONS
                this.questionScreen.option1.setEnabled(false);
                this.questionScreen.option2.setEnabled(false);
                this.questionScreen.option3.setEnabled(false);

                // ALLOW USER TO CLICK NEXT BUTTON
                this.questionScreen.nextButton.setEnabled(true);
            }
        }


        if (e.getActualComponent().getUIID().equals("NextButton")) {
            if (this.currentQuestion == this.questions.size()) return;

            nextQuestion();
            this.questionScreen.nextButton.setEnabled(false);
        }


        if (e.getActualComponent().getUIID().equals("QuitButton")) {
            quit();
        }


        e.consume();
    }


    /* TO SET THE INITIAL QUESTION ON THE QUESTION SCREEN */
    public void initQuestion() {
        Question question = this.questions.get(0);

        this.setImage(question.imageName);

        this.setOptions(question.options[0], question.options[1], question.options[2]);

        // SET THE ANSWER
        this.answer = question.answer;

        // SET THE INITIAL SCORE
        this.setScore(0);

        // SET THE NUMBER OF QUESTIONS
        this.currentQuestion++;
        this.setProgress(this.currentQuestion);
    }


    public boolean isAnswerCorrect() {
        String selectedOption = this.questionScreen.optionGroup.getSelected().getText();

        if (!selectedOption.equals(this.answer)) {
            return false;
        }

        return true;
    }


    /* MOVE TO NEXT QUESTION */
    public void nextQuestion() {
        // CHANGE THE BUTTON STYLE TO PREVIOUS UIID
        this.questionScreen.optionGroup.getSelected().setUIID(this.previousUIID);

        Question question = this.questions.get(this.currentQuestion);

        // CHANGE THE IMAGE
        this.setImage(question.imageName);

        this.questionScreen.optionGroup.clearSelection();

        this.setOptions(question.options[0], question.options[1], question.options[2]);

        // SET THE ANSWER
        this.answer = question.answer;

        // INCREASE THE CURRENT QUESTION NUMBER
        this.currentQuestion++;
        this.setProgress(this.currentQuestion);

        // ENABLE THE NEXT BUTTON
        this.questionScreen.nextButton.setEnabled(false);

        // ENABLE THE BUTTONS
        this.questionScreen.option1.setEnabled(true);
        this.questionScreen.option2.setEnabled(true);
        this.questionScreen.option3.setEnabled(true);
    }


    /* QUIT GAME */
    public void quit() {
        if (this.questionScreen.optionGroup.getSelected() == null) return;

        this.questionScreen.optionGroup.getSelected().setUIID(this.previousUIID);

        this.setScore(0);
        this.setProgress(1);

        Question question = this.questions.get(0);

        this.setImage(question.imageName);

        this.questionScreen.optionGroup.clearSelection();

        this.setOptions(question.options[0], question.options[1], question.options[2]);

        this.questionScreen.showBack();
    }


    public void setScore(int score) {
        this.questionScreen.scoreLabel.setText("Score " + score);
    }

    public void setProgress(int current) {
        this.questionScreen.questionProgress.setText("Question " + current + "/" + this.questions.size());
    }

    public void setImage(String name) {
        Image image = Resources.getGlobalResources().getImage(name);
        image = image.scaled(500, 500);
        this.questionScreen.questionImageContainer.setIcon(image);
    }

    public void setOptions(String text1, String text2, String text3) {
        this.questionScreen.option1.setText(text1);
        this.questionScreen.option2.setText(text2);
        this.questionScreen.option3.setText(text3);
    }

}
