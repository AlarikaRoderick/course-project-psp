package com.company.service;

import org.json.simple.JSONObject;

import java.io.IOException;

public class RegisterPageService {
    public String signUpUser(JSONObject object) throws IOException, ClassNotFoundException {
        SendObjectService.sendObject(object);
        return SendObjectService.getMessage();
    }
}
