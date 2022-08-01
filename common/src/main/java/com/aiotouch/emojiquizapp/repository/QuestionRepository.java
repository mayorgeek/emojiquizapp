package com.aiotouch.emojiquizapp.repository;

import com.codename1.components.Progress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuestionRepository {

    public List<Map<String,String>> getQuestions () {
        List<Map<String, String>> questions = new ArrayList<>();

        try {
            ConnectionRequest request = new ConnectionRequest("https://emojiquizapp-backend.herokuapp.com/api/v1/questions/", false);

            Progress loadingQuestions = new Progress("Loading Questions", request);
            loadingQuestions.setAutoShow(true);
            loadingQuestions.setDisposeOnCompletion(true);

            NetworkManager.getInstance().addToQueueAndWait(request);

            Map<String, Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            questions = (List<Map<String, String>>) result.get("root");
        } catch (IOException err) {
            Log.e(err);
        }

        return questions;
    }

}
