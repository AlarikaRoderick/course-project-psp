package com.company.service;

import com.company.entities.SessionEntity;
import org.json.simple.JSONObject;

import java.io.IOException;

public class AdminFilmSessionPageService {
    public String updateSession(SessionEntity session) throws IOException, ClassNotFoundException {
        JSONObject object = new JSONObject();
        object.put("action", "updateSession");
        object.put("session", session);
        SendObjectService.sendObject(object);
        return SendObjectService.getMessage();
    }

    public String deleteSession(SessionEntity session) throws IOException, ClassNotFoundException{
        JSONObject object = new JSONObject();
        object.put("action", "deleteSession");
        object.put("session", session);
        SendObjectService.sendObject(object);
        return SendObjectService.getMessage();
    }
}
