package com.aiotouch.emojiquizapp.repository;

import com.aiotouch.emojiquizapp.service.AuthService;
import com.codename1.components.Progress;
import com.codename1.components.ToastBar;
import com.codename1.io.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class QuestionRepository {

    public List<Map<String,String>> getQuestions () {

        List<Map<String, String>> questions = new ArrayList<>();

        ConnectionRequest request = new ConnectionRequest() {
            @Override
            protected void handleException(Exception err) {
                ToastBar.Status requestErr = ToastBar.showErrorMessage("Network Error! Please try again.", 5000);
            }
        };
        request.setUrl("https://emojiquizapp-backend.herokuapp.com/api/v1/questions/");
        request.setPost(false);
        request.setCacheMode(ConnectionRequest.CachingMode.SMART);
        request.setPriority(ConnectionRequest.PRIORITY_CRITICAL);

        String token = "";

        /* Check if token exists */
        if (Preferences.get("token", null) != null) {
            /* Confirm that expiresAt exists in preferences */
            if (Preferences.get("expiresAt", null) != null) {
                long tokenExpiresAt = Long.parseLong(Preferences.get("expiresAt", null));//

                /* Confirm that token has not expired */
                if (tokenExpiresAt > new Date().getTime()) {
                    token = Preferences.get("token", "");
                } else {
                    token =  AuthService.getApiToken();
                }
            }
        } else {
            token =  AuthService.getApiToken();
        }

        String bearerToken = "Bearer " + token;

        request.addRequestHeader("Authorization\t", bearerToken);

        Progress loadingQuestions = new Progress("Loading Questions", request);
        loadingQuestions.setAutoShow(true);
        loadingQuestions.setDisposeOnCompletion(true);

        NetworkManager.getInstance().addToQueueAndWait(request);

        /* If request was successful then save the token and get questions returned from the server */
        if (request.getResponseCode() == 200) {
            Preferences.set("token", token);
            Preferences.set("expiresAt", (new Date().getTime() + 600000));

            try {
                Map<String, Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
                questions = (List<Map<String, String>>) result.get("root");

            } catch (IOException err) {
                Log.e(err);
            }
        }


        /* If reponse was unauthorized or server error then clear stored token, get new token and retry request */
        if (request.getResponseCode() == 500 || request.getResponseCode() == 401) {
            Preferences.delete("token");
            String newToken = AuthService.getApiToken();
            String newBearerToken = "Bearer " + newToken;

            request.addRequestHeader("Authorization\t", newBearerToken);
            request.retry();
        }

        return questions;
    }

}
