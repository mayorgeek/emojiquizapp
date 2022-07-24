package com.aiotouch.emojiquizapp.repository;

import com.aiotouch.emojiquizapp.models.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionRepository {

    public List<Question> getQuestions () {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("face-with-tears-of-joy-emoji.png", new String[] { "Face with tears of joy", "Something here", "Nothing Here" }, "Face with tears of joy"));
        questions.add(new Question("love-hearts-eyes-emoji.png", new String[] { "Something here", "Love Hearts Eyes", "Nothing Here" }, "Face with tears of joy"));
        questions.add(new Question("smiling-face-with-sunglasses-cool-emoji.png", new String[] { "Nothing Here", "Something here", "Smiling Face With Sunglasses" }, "Smiling Face With Sunglasses"));

        return questions;
    }

}
