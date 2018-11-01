package com.company.service;

import org.json.simple.JSONObject;

import java.io.IOException;

public class FirstPageService {
    public String signInUser(JSONObject object) throws IOException, ClassNotFoundException {
        SendObjectService.sendObject(object);
        String request = SendObjectService.getMessage();
        return request;
    }
}
