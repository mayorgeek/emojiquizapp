package com.aiotouch.emojiquizapp.controllers;

import com.aiotouch.emojiquizapp.AdmobInit;
import com.aiotouch.emojiquizapp.repository.QuestionRepository;
import com.aiotouch.emojiquizapp.views.QuestionScreen;
import com.aiotouch.emojiquizapp.views.WelcomeScreen;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;

import java.util.List;
import java.util.Map;

public class QuestionScreenController implements ActionListener<ActionEvent> {

    public QuestionScreen questionScreen;
    public String answer;
    public List<Map<String, String>> questions;
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
                    // CHANGE BUTTON TO SUCCESS ANSWER LOOK
                    this.questionScreen.optionGroup.getSelected().setUIID("CorrectAnswer");

                    // INCREASE THE SCORE
                    this.score += 2;
                    this.setScore(this.score);

                    // DISABLE THE BUTTONS
                    this.questionScreen.option1.setEnabled(false);
                    this.questionScreen.option2.setEnabled(false);
                    this.questionScreen.option3.setEnabled(false);

                    Dialog quizCompleteDialog = new Dialog("", new BorderLayout());
                    quizCompleteDialog.getContentPane().setUIID("DialogContentPane");

                    Container cnt = new Container();

                    SpanLabel body = new SpanLabel("Congratulations! You have reached the end of the quiz...");
                    body.setUIID("DialogBody");

                    Label showScore = new Label("Your Score: " + this.score);
                    showScore.setUIID("ShowScore");

                    Button okButton = new Button("Ok");
                    okButton.setUIID("OkButton");
                    okButton.addActionListener(action -> this.questionScreen.showBack());

                    cnt.add(body).add(showScore);

                    quizCompleteDialog.add(BorderLayout.NORTH, cnt)
                                        .add(BorderLayout.SOUTH, okButton);

                    quizCompleteDialog.show();
                }

                // SAVE RADIO BUTTON PREVIOUS STYLE
                this.previousUIID = this.questionScreen.optionGroup.getSelected().getUIID();

                // CHANGE BUTTON TO SUCCESS ANSWER LOOK
                this.questionScreen.optionGroup.getSelected().setUIID("CorrectAnswer");

                // INCREASE THE SCORE
                this.score += 2;
                this.setScore(this.score);

                // DISABLE THE BUTTONS

                // ALLOW USER TO CLICK NEXT BUTTON
            } else {
                // SAVE RADIO BUTTON PREVIOUS STYLE
                this.previousUIID = this.questionScreen.optionGroup.getSelected().getUIID();

                // CHANGE BUTTON TO WRONG ANSWER LOOK
                this.questionScreen.optionGroup.getSelected().setUIID("WrongAnswer");

                // DISABLE THE BUTTONS

                // ALLOW USER TO CLICK NEXT BUTTON
            }
            this.questionScreen.option1.setEnabled(false);
            this.questionScreen.option2.setEnabled(false);
            this.questionScreen.option3.setEnabled(false);
            this.questionScreen.nextButton.setEnabled(true);
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
        Map<String, String> question = this.questions.get(0);

        this.setImage(question.get("imagePath"));

        this.setOptions(question.get("option1"), question.get("option2"), question.get("option3"));

        // SET THE ANSWER
        this.answer = question.get("answer");

        // SET THE INITIAL SCORE
        this.setScore(0);

        // SET THE NUMBER OF QUESTIONS
        this.currentQuestion++;
        this.setProgress(this.currentQuestion);
    }


    public boolean isAnswerCorrect() {
        String selectedOption = this.questionScreen.optionGroup.getSelected().getText();

        return selectedOption.equals(this.answer);
    }


    /* MOVE TO NEXT QUESTION */
    public void nextQuestion() {
        // SHOW ADS BEFORE MOVING TO NEXT QUESTION
        this.showFullscreenAds();

        // CHANGE THE BUTTON STYLE TO PREVIOUS UIID
        this.questionScreen.optionGroup.getSelected().setUIID(this.previousUIID);

        Map<String, String> question = this.questions.get(this.currentQuestion);

        // CHANGE THE IMAGE
        this.setImage(question.get("imagePath"));

        this.questionScreen.optionGroup.clearSelection();

        this.setOptions(question.get("option1"), question.get("option2"), question.get("option3"));

        // SET THE ANSWER
        this.answer = question.get("answer");

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
        new WelcomeScreen().showBack();
    }


    public void setScore(int score) {
        this.questionScreen.scoreLabel.setText("Score " + score);
    }

    public void setProgress(int current) {
        this.questionScreen.questionProgress.setText("Question " + current + "/" + this.questions.size());
    }

    public void setImage(String url) {
        Image img = URLImage.createCachedImage("QuestionImage", url, this.questionScreen.questionImage, URLImage.FLAG_RESIZE_SCALE_TO_FILL);
        this.questionScreen.questionImageContainer.setIcon(img);
    }

    public void setOptions(String text1, String text2, String text3) {
        this.questionScreen.option1.setText(text1);
        this.questionScreen.option2.setText(text2);
        this.questionScreen.option3.setText(text3);
    }

    public void showFullscreenAds() {
        if ((this.currentQuestion % 5) == 0) {
            new AdmobInit().admob.loadAndShow();
        }
    }

}
