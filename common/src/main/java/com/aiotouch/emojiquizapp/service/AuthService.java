package com.aiotouch.emojiquizapp.service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import org.json.simple.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class AuthService {

    public static String getApiToken() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "aiotouch");
        requestBody.put("password", "aiotouchapppassword2022");

        ConnectionRequest tokenRequest = new ConnectionRequest("https://emojiquizapp-backend.herokuapp.com/api/v1/auth/login/", true);
        tokenRequest.setContentType("application/json");
        tokenRequest.setRequestBody(JSONObject.toJSONString(requestBody));
        tokenRequest.setPriority(ConnectionRequest.PRIORITY_CRITICAL);

        String token = "";

        NetworkManager.getInstance().addToQueueAndWait(tokenRequest);

        try {
            Map<String, Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(tokenRequest.getResponseData()), "UTF-8"));
            token = (String) result.get("token");
        } catch (IOException err) {
            Log.e(err);
        }

        return token;
    }

}
